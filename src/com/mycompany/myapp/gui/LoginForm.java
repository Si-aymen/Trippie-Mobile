/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import static com.codename1.ui.BrowserComponent.onError;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Chauffeur;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.entities.Locateur;
import com.mycompany.myapp.services.ServiceChauffeur;
import com.mycompany.myapp.services.ServiceClient;
import com.mycompany.myapp.services.ServiceLocateur;

/**
 *
 * @author aymen
 */
public class LoginForm extends BaseForm {

    public LoginForm(Form previous, Resources res) {

        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        // add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        TextField Email = new TextField("", "Email", 20, TextField.ANY);
        TextField Password = new TextField("", "Password", 20, TextField.PASSWORD);
        ComboBox<String> userTypeComboBox = new ComboBox<>("Customer", "Landlord", "Driver");
        Email.setSingleLineTextArea(false);
        Password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");

        //mp oublié
        Button mp = new Button("oublier mot de passe?", "CenterLabel");

        signUp.addActionListener(e -> new AddUtilisateurForm().show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(Email),
                createLineSeparator(),
                new FloatingHint(Password),
                createLineSeparator(),
                userTypeComboBox, // Ajouter la ComboBox au conteneur
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                mp
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        // Ajout des écouteurs d'événements pour le bouton de connexion
        signIn.addActionListener(e -> {
            String username = Email.getText();
            String password = Password.getText();
            String userType = userTypeComboBox.getSelectedItem();
            if (userTypeComboBox.getSelectedItem().equals("Customer")) {
                Client c = new Client(username, password);
                ServiceClient.getInstance().login(c);

                ProfilClForm clientForm = new ProfilClForm(res, previous);
                clientForm.show();
            } else if (userTypeComboBox.getSelectedItem().equals("Landlord")) {
                Locateur loc = new Locateur(username, password);
                Locateur loc2 =ServiceLocateur.getInstance().login(loc);

                ProfilLocForm locForm = new ProfilLocForm(res, previous);
                locForm.show();
            } else if (userTypeComboBox.getSelectedItem().equals("Driver")) {
                Chauffeur ch = new Chauffeur(username, password);
                ServiceChauffeur.getInstance().login(ch);

                ProfilChForm chForm = new ProfilChForm(res, previous);
                chForm.show();
            }
        });
        mp.addActionListener(e -> {
            ChoixResetRoleForm choix =new ChoixResetRoleForm(res, previous);
            choix.show();

        });

    }

}
