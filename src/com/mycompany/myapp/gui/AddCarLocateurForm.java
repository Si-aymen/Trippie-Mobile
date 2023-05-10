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
import com.mycompany.myapp.entities.Locateur;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceVoiture;

/**
 *
 * @author bhk
 */
public class AddCarLocateurForm extends Form {

    private ComboBox<String> combo1;
    private ComboBox<String> combo2;
    private ComboBox<String> combo3;

    public AddCarLocateurForm(Form previous, Locateur l) {
        setTitle("Add a new car");
        setLayout(BoxLayout.y());
        setUIID("Activate");
        TextField tfmatricule = new TextField("", "Registration Number");
        combo1 = new ComboBox<>("BMW", "Mercedes", "Audi", "clio", "porshe", "peugeot", "hamer");
        combo2 = new ComboBox<>("5ch", "6ch", "7ch", "8ch", "9ch", "10ch", "11ch", "12ch", "13ch");
        combo3 = new ComboBox<>("essence", "gazoil", "gpl");

        TextField tfpuissance = new TextField("", "Power");
        TextField tfprix_jours = new TextField("", "Price per day");
        TextField tfenergie = new TextField("", "Energie");
        TextField tfetat = new TextField("", "Status");

        Button btnValider = new Button("Add Car");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                double prix = Double.parseDouble(tfprix_jours.getText());
                String input = tfprix_jours.getText();
                if (prix < 0) {

                    Dialog.show("Alert", "^price shoud positive", new Command("OK"));
                } else if ((tfmatricule.getText().length() == 0)) {
                    Dialog.show("Alert", "Registration number is empty", new Command("OK"));
                } else if (tfprix_jours.getText().length() == 0) {
                    Dialog.show("Alert", "price should be positive", new Command("OK"));
                } else if (tfprix_jours.getText().length() > 12) {
                    Dialog.show("Alert", "length should inf 12", new Command("OK"));
                } else {

                    String nom = tfmatricule.getText().toString();
                    String tfmarque = combo1.getSelectedItem();
                    String Puissance = combo2.getSelectedItem();
                    String energie = combo3.getSelectedItem();
                    String telephone = tfprix_jours.getText();
                    String picture = "4.jpg";
                    String etat = "non reservé";
                    // System.out.println("page voitures"+loc);

                    Voiture v = new Voiture(nom, tfmarque, Puissance, telephone,
                            picture, energie, etat);
                    System.out.println(l);

                    if (ServiceVoiture.getInstance().addTask(v)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfmatricule, combo1, combo2, tfprix_jours, combo3, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
