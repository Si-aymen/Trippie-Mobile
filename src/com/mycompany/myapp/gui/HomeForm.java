/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Client;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
     Resources res;
      Client c;
    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddRole = new Button("Add Role");
        Button btnAddTask = new Button("Sign up");
        Button btnListTasks = new Button("List of users");
        Button login = new Button("Login");
        btnAddTask.addActionListener(e-> new AddUtilisateurForm(this).show());
        btnListTasks.addActionListener(e-> new ListUtilisateursForm(this).show());
        login.addActionListener(e-> new LoginForm(this,res).show());
        addAll(btnAddTask,btnListTasks,btnAddRole,login);
        
        
    }
    
    
}
