/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceClient;

/**
 *
 * @author aymen
 */
public class ForgetForm extends BaseForm {

    public ForgetForm(Resources res,Form previous) {
        super(new BorderLayout());
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        TextField gsm = new TextField();
        Button btnValider = new Button("Valider");
        gsm.setSingleLineTextArea(false);
        gsm.setGrowByContent(true);
        Container content = BoxLayout.encloseY(
                new FloatingHint(gsm),
                createLineSeparator(),
                btnValider
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);

        btnValider.requestFocus();

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceClient.getInstance().sms(gsm, res);
                LoginForm loginForm = new LoginForm(previous, res);
                loginForm.show();
            }
        });

    }

}
