/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author bhk
 */
public class AddUtilisateurForm extends BaseForm {

    public AddUtilisateurForm(Form previous) {
         super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        //Form previous = Display.getInstance().getCurrent();
        //tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        TextField tfCin = new TextField("", "Cin");
        TextField tfName = new TextField("", "Firstname");
        TextField tfLname = new TextField("", "Lastname");
        Button btnValider = new Button("Add User");
      
        
        tfCin.setSingleLineTextArea(false);
        tfName.setSingleLineTextArea(false);
        tfLname.setSingleLineTextArea(false);
       
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tfCin),
                createLineSeparator(),
                new FloatingHint(tfName),
                createLineSeparator(),
                new FloatingHint(tfLname),
                createLineSeparator()       
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
        FlowLayout.encloseCenter(btnValider)
        ));

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {

                    Utilisateur u = new Utilisateur(tfCin.getText().toString(), tfName.getText().toString(), tfLname.getText().toString());
                    Utilisateur addedUser = ServiceUtilisateur.getInstance().addUtilisateurJSON(u);
                    if (addedUser != null) {
                        Dialog.show("Success", "User added", new Command("OK"));
                        AddRoleForm roleForm = new AddRoleForm(previous, addedUser);
                        roleForm.show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

     

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
