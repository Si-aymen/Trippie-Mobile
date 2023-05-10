/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    public HomeForm() {

        setTitle("Home");
        setLayout(BoxLayout.y());
        Button btnAddTask = new Button("Add new Reclamation");
        Button btnListTasks = new Button("List Reclamation");

        btnAddTask.addActionListener(e -> new AddReclamationForm(this).show());
        btnListTasks.addActionListener(e -> new ListReclamationform(this).show());
        addAll(btnAddTask,btnListTasks);

    }

}
