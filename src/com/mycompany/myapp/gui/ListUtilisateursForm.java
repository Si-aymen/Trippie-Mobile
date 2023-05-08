/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhk
 */
public class ListUtilisateursForm extends Form {

    public ListUtilisateursForm(Form previous) {
        setTitle("List of users");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Utilisateur> tasks = ServiceUtilisateur.getInstance().getAllUsers();
        for (Utilisateur t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    

    public void addElement(Utilisateur user) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Label cin = new Label(user.getCin());
        Label nom = new Label(user.getNom());
        Label prenom = new Label(user.getPrenom());
       
            add(cin);
            add(nom);
            add(prenom);
        
    }

}
