/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author HP
 */
public class FormLogin extends Form{
    public FormLogin(){
    Toolbar tb = getToolbar();
        tb.setUIID("aaa");
        setUIID("taswiraacc");
        setLayout(new FlowLayout(CENTER, CENTER));
        Button btnLogin = new Button("Bienvenue");
        //sbtnLogin.setUIID("LoginButton");
        Container cn = new Container(BoxLayout.y());
        btnLogin.addActionListener(e->{
            new HomeForm(this).show();
        });
        cn.addAll(btnLogin);
        add(cn);
    
    }
}
