/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form {

    public AddReclamationForm(Form previous) {
        setTitle("Add Reclamation");
        setLayout(BoxLayout.y());

        TextField tftype = new TextField("", "type");
        TextField tfcommentaire = new TextField("", "commentaire");
        TextField tfid_user = new TextField("", "user id");
        TextField tfdate_creation = new TextField("", "dare creation");
        TextField tfimage = new TextField("", "picture");

        Button btnValider = new Button("Add task");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftype.getText().length() == 0)) {
                    Dialog.show("Alert", "Registration number is empty", new Command("OK"));
                } else {

                    String type = tftype.getText().toString();
                    String commentaire = tfcommentaire.getText().toString();
                    String date_creation = tfdate_creation.getText().toString();
                    String image = tfimage.getText().toString();

//                   int id_user = Integer.parseInt(tfid_user.getText());

                    Reclamation r = new Reclamation(type,commentaire,"non reservé",image);

                    if (ServiceReclamation.getInstance().addTask(r)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tftype, tfcommentaire, tfimage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
