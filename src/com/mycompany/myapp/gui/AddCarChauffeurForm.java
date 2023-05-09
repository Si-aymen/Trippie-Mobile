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
import com.mycompany.myapp.entities.Cov;
import com.mycompany.myapp.services.ServiceCov;

/**
 *
 * @author bhk
 */
public class AddCarChauffeurForm extends Form {
    
    static int  id_chauff; 

    private ComboBox<String> combo1;
    private ComboBox<String> combo2;

    public AddCarChauffeurForm(Form previous) {
        setTitle("Add a new car");
        setLayout(BoxLayout.y());

        combo1 = new ComboBox<>("", "Aryanah", "Bizerte", "Beja", "Tunis", "Sfax", "Kairouan", "Jandouba", "Ben Arous", "Manouba", "Nabeul", "Zaghouan");

        combo2 = new ComboBox<>("", "Aryanah", "Bizerte", "Beja", "Tunis", "Sfax", "Kairouan", "Jandouba", "Ben Arous", "Manouba", "Nabeul", "Zaghouan");

        TextField tfnmbreplace = new TextField("", "nbmre de palce");

        Button btnValider = new Button("Add task");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String tfmarque = combo1.getSelectedItem();
                String tfPuissance = combo2.getSelectedItem();
                String nmbre_place = tfnmbreplace.getText().toString();
                int nombrePlace = Integer.parseInt(nmbre_place);

                Cov v = new Cov(tfmarque, tfPuissance, nombrePlace);

                if (ServiceCov.getInstance().addTask(v)) {
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }

            }
        });

        addAll(tfnmbreplace, combo1, combo2, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
