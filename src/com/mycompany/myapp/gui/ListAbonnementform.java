package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
import java.util.*;

public class ListAbonnementform extends Form {

    Form previous;

    public static Abonnement currentV = null;
    Button addBtn;

    public ListAbonnementform(Form previous) {
        setUIID("Activate");
        this.previous = previous;
        setTitle("list Abonnement");
        setLayout(BoxLayout.y());
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        super.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> new addabonnement(previous).show());

        addGUIs();

    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {

        ArrayList<Abonnement> listAbonnements = ServiceAbonnement.getInstance().getAll();
        if (listAbonnements.size() > 0) {
            for (Abonnement v : listAbonnements) {
                Component model = makeAbonnementModel(v);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucun donnée"));
        }
    }

    Label type, prix;

    private Container makeModelWithoutButtons(Abonnement v) {
        Container AbonnementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AbonnementModel.setUIID("containerRounded");

        type = new Label("Type : " + v.getType());
        type.setUIID("labelDefault");

        prix = new Label("Price : " + v.getPrix());
        prix.setUIID("labelDefault");

        AbonnementModel.addAll(
                type, prix
        );

        return AbonnementModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeAbonnementModel(Abonnement v) {

        Container AbonnementModel = makeModelWithoutButtons(v);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("update");
        editBtn.setUIID("buttonWhiteCenter");

        editBtn.addActionListener(action -> {
            currentV = v;
            new ModifierAbonnementLocateur(previous, v).show();

        });

        deleteBtn = new Button("Delete");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce Transaction ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");

            btnConfirm.addActionListener(actionConf -> {

                ServiceAbonnement.getInstance().delete(v.getIdA());
                new ListAbonnementform(previous).show();
            });

            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        AbonnementModel.add(btnsContainer);

        return AbonnementModel;
    }

}
