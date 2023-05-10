/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    public HomeForm() {

        setTitle("Memberships");
        setLayout(BoxLayout.y());

        Label l1 = new Label("Buy new membership");
        l1.setAlignment(Component.CENTER);
        FontImage plusIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, "MyIcon", 10);
        Button addButton = new Button();
        addButton.setIcon(plusIcon);
        addButton.addActionListener(e -> new addabonnement  (this).show());

        Label l2 = new Label("Membership list");
        l2.setAlignment(Component.CENTER);
        FontImage listIcon = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_CAR, "MyIcon", 10);
        Button btnListTasks = new Button();
        btnListTasks.setIcon(listIcon);
        btnListTasks.addActionListener(e -> new ListAbonnementform(this).show());
        addAll(addButton, l1, btnListTasks, l2);

       
       
        
    }

}