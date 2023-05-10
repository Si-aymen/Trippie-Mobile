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
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;

/**
 *
 * @author bhk
 */
public class addabonnement extends Form {

    private ComboBox<String> combo1;

    public addabonnement(Form previous) {
        setTitle("Buy new membership");
        setLayout(BoxLayout.y());
        setUIID("Activate");
        combo1 = new ComboBox<>("Gold", "Platinium", "Bronze");

        TextField tfprix = new TextField("", "Membership price");

        Button btnValider = new Button("Add Membership");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                double prix = Double.parseDouble(tfprix.getText());
                String input = tfprix.getText();
                if (prix < 0) {

                    Dialog.show("Alert", "price shoud be positive", new Command("OK"));
                } else if (tfprix.getText().length() == 0) {
                    Dialog.show("Alert", "price should be positive", new Command("OK"));
                } else if (tfprix.getText().length() > 2000) {
                    Dialog.show("Alert", "length should inf 2000", new Command("OK"));
                } else {
                    String tftype = combo1.getSelectedItem();
                    Abonnement v = new Abonnement();
                    v.setType(tftype); // set type based on selected item
                    v.setPrix(tftype); // set price based on selected item
                    if (ServiceAbonnement.getInstance().addTask(v)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        addAll(combo1, tfprix, btnValider);

    }

}
