/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceRole;

/**
 *
 * @author aymen
 */
public class AddRoleForm extends BaseForm {

    public AddRoleForm(Form previous, Utilisateur u) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        //Form previous = Display.getInstance().getCurrent();
        //tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        setTitle("Choose your role");
        // setLayout(BoxLayout.y());
        ComboBox<String> libelle = new ComboBox<>("Client", "Chauffeur", "Locateur");

        Button btnValider = new Button("Add");
       Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                libelle,
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
                if (libelle.getSelectedItem() == null) {
                    Dialog.show("Alert", "Please select a role", new Command("OK"));
                } else {

                    Role r = new Role(libelle.getSelectedItem());
                    Role addedRole = ServiceRole.getInstance().addRoleJSON(r, u);
                    if (addedRole != null) {
                        Dialog.show("Success", "Role added", new Command("OK"));

                        if (libelle.getSelectedItem().equals("Client")) {
                            AddClientForm clientForm = new AddClientForm(previous, addedRole);
                            clientForm.show();
                        } else if (libelle.getSelectedItem().equals("Chauffeur")) {
                            AddChauffeurForm chauffeurForm = new AddChauffeurForm(previous, addedRole);
                            chauffeurForm.show();
                        } else if (libelle.getSelectedItem().equals("Locateur")) {
                            AddLocateurForm locateurForm = new AddLocateurForm(previous, addedRole);
                            locateurForm.show();
                        }
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
