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

public class ModifierAbonnementLocateur extends Form {

    private Form previous;
    private ComboBox<String> combo1;
    private ComboBox<String> combo2;
  

    public ModifierAbonnementLocateur(Form previous, Abonnement v) {

        setTitle("update car");
        setLayout(BoxLayout.y());

        this.previous = previous;
       combo1 = new ComboBox<>("Gold", "Platinium", "Bronze");
       

       TextField tfprix = new TextField("", "Price per day");
       
             
       

        Button btnModifier = new Button("Edit");
        Button btnAnnuler = new Button("Cancel");

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                v.setPrix(tfprix.getText());

                String selectedType = (String) combo1.getSelectedItem();
                v.setType(selectedType);
                
                try {
                    if (ServiceAbonnement.getInstance().modifier(v)) {
                        new ListAbonnementform(previous).show();
                    } else {
                        Dialog.show("Error", "Unable to update ", new Command("OK"));
                    }
                } catch (Exception e) {
                    Dialog.show("Error", "Unable to update  " + e.getMessage(), new Command("OK"));
                }

            }

        });

        btnAnnuler.addActionListener(e -> {
            new ListAbonnementform(previous).show();
        });

        addAll( combo1,tfprix, btnAnnuler, btnModifier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}