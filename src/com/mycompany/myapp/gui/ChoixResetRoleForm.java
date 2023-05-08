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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author aymen
 */
public class ChoixResetRoleForm extends BaseForm {

    public ChoixResetRoleForm(Resources res,Form previous) {

        ComboBox<String> libelle = new ComboBox<>("Client", "Chauffeur", "Locateur");
        // addComponent(BorderLayout.CENTER, libelle);
        Button btnValider = new Button("Next");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (libelle.getSelectedItem() == null) {
                    Dialog.show("Alert", "Please select a role", new Command("OK"));
                } else {

                    if (libelle.getSelectedItem().equals("Client")) {
                        ForgetForm forge = new ForgetForm(res, previous);
                        forge.show();
                    }
                    /*else if (libelle.getSelectedItem().equals("Chauffeur")) {
                        AddChauffeurForm chauffeurForm = new AddChauffeurForm(previous, addedRole);
                        chauffeurForm.show();
                    } else if (libelle.getSelectedItem().equals("Locateur")) {
                        AddLocateurForm locateurForm = new AddLocateurForm(previous, addedRole);
                        locateurForm.show();
                    }*/

                }
            }
        });
        addAll(libelle, btnValider);
    }
}
