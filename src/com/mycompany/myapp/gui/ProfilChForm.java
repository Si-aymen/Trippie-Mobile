/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceChauffeur;

/**
 *
 * @author aymen
 */
public class ProfilChForm extends BaseForm {

    public ProfilChForm(Resources res, Form previous) {
        super("Profil", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");

        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        /* Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);*/

        Button modiff = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");

        /*Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));*/
 /*        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);*/
 /* sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);*/
        //Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        //Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        // facebook.setTextPosition(BOTTOM);
        //twitter.setTextPosition(BOTTOM);
        /*  add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));*/
        TextField num_permis = new TextField(SessionManagerChauffeur.getNum_permis(), "Driving License", 20, TextField.ANY);
        num_permis.setUIID("TextFieldBlack");
        addStringValue("Driving License", num_permis);
        System.out.println(num_permis);

        TextField Gsm = new TextField(String.valueOf(SessionManagerChauffeur.getGsm()), "Gsm", 20, TextField.ANY);
        Gsm.setUIID("TextFieldBlack");
        addStringValue("Gsm", Gsm);

        TextField Email = new TextField(SessionManagerChauffeur.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        Email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", Email);

        TextField Password = new TextField(SessionManagerChauffeur.getPassowrd(), "Password", 20, TextField.PASSWORD);
        Password.setUIID("TextFieldBlack");
        addStringValue("Password", Password);

        Supprimer.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("", Supprimer);
        addStringValue("", modiff);

        modiff.addActionListener((ActionEvent edit) -> {
            InfiniteProgress ip = new InfiniteProgress();
            //final Dialog ipDlg = ip.showInifinieteBlooking();
            ServiceChauffeur.EditCh(SessionManagerChauffeur.getId(), Integer.parseInt(Gsm.getText()), num_permis.getText(), Email.getText(), Password.getText());
            SessionManagerChauffeur.setGsm(Integer.parseInt(Gsm.getText()));
            SessionManagerChauffeur.setNum_permis(num_permis.getText());
            SessionManagerChauffeur.setPassowrd(Password.getText());
            SessionManagerChauffeur.setEmail(Email.getText());
            Dialog.show("Succes", "Modifications des coordonnees avec succes", "OK", null);
            // ipDlg.dispose();
            refreshTheme();

        });

        Supprimer.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Suppression");

            if (dig.show("Suppression", "Vous voulez supprimer Votre Compte ?", "Annuler", "Oui")) {
                dig.dispose();
            } else {
                dig.dispose();
            }

            if (ServiceChauffeur.getInstance().deleteCh(SessionManagerChauffeur.getId())) {
                LoginForm loginForm = new LoginForm(previous, res);
                loginForm.show();
            }

        });

        /*   CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));

        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));*/
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));

    }

}
