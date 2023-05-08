//package com.mycompany.myapp.gui;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.ComboBox;
//import com.codename1.ui.Command;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Cov;
//import com.mycompany.myapp.services.ServiceCov;
//
//public class ModifierCo_voiturageChauffeur extends Form {
//
//    private Form previous;
//    private ComboBox<String> combo1;
//    private ComboBox<String> combo2;
//    private ComboBox<String> combo3;
//
//    public ModifierCo_voiturageChauffeur(Form previous, Cov v) {
//
//       setTitle("update car");
//        setLayout(BoxLayout.y());
//
//        this.previous = previous;
//        combo1 = new ComboBox<>("", "Aryanah", "Bizerte", "Beja", "Tunis", "Sfax", "Kairouan", "Jandouba", "Ben Arous", "Manouba", "Nabeul", "Zaghouan");
//
//        combo2 = new ComboBox<>("", "Aryanah", "Bizerte", "Beja", "Tunis", "Sfax", "Kairouan", "Jandouba", "Ben Arous", "Manouba", "Nabeul", "Zaghouan");
//
//        TextField depart  = new TextField(v.getDepart(), " depart");
//        TextField destination = new TextField(v.getDestination(), "destination");
//        //TextField nmbr_place = new TextField(v.getNbre_place() , "number of seats ");
//
//
//        Button btnModifier = new Button("Edit");
//        Button btnAnnuler = new Button("Cancel");
//
//        btnModifier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                v.setDepart(depart.getText());
//
//                String selectedDepart = (String) combo1.getSelectedItem();
//                v.setDepart(selectedDepart);
//                String selectDestination = (String) combo2.getSelectedItem();
//                v.setDestination(selectDestination);
//
//
//                try {
//                    if (ServiceCov.getInstance().modifier(v)) {
//                        new ListVoitureform(previous).show();
//                    } else {
//                        Dialog.show("Error", "Unable to update transaction", new Command("OK"));
//                    }
//                } catch (Exception e) {
//                    Dialog.show("Error", "Unable to update transaction: " + e.getMessage(), new Command("OK"));
//                }
//
//            }
//
//        });
//
//        btnAnnuler.addActionListener(e -> {
//            new ListVoitureform(previous).show();
//        });
//
//        addAll(matricule, combo1, combo2, prix_jours, combo3, etat, id_locateur, picture, btnAnnuler, btnModifier);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//
//    }
//}
