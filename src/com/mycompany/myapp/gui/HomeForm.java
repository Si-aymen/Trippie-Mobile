/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    public HomeForm() {

        setTitle("Car location");
        setLayout(BoxLayout.y());
        Button btnAddTask = new Button("Add new car");
        Button btnListTasks = new Button("List cars");
        btnAddTask.addActionListener(e -> new AddCarChauffeurForm(this).show());
        btnListTasks.addActionListener(e -> new ListCo_voiturageform(this).show());
        addAll(btnAddTask, btnListTasks);
        Toolbar tb = getToolbar();
tb.addMaterialCommandToSideMenu("Car location", FontImage.MATERIAL_HOME, e -> {
           new HomeForm().show();
});
tb.addMaterialCommandToSideMenu("Menu Item 2", FontImage.MATERIAL_SETTINGS, e -> {
    // do something when Menu Item 2 is selected
});
tb.addMaterialCommandToSideMenu("Menu Item 3", FontImage.MATERIAL_INFO, e -> {
    // do something when Menu Item 3 is selected
});




    }

}
