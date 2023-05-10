package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mycompany.myapp.entities.Locateur;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.services.ServiceVoiture;
import java.util.*;

public class ListVoitureform extends Form {

    Form previous;

    public static Voiture currentV = null;
    Button addBtn;
    Locateur l;

    public ListVoitureform(Form previous) {
        setUIID("Activate");
        this.previous = previous;
        setTitle("list car");
        setLayout(BoxLayout.y());

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        super.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> new AddCarLocateurForm(previous, l).show());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {
        setUIID("Activate");
        ArrayList<Voiture> listVoitures = ServiceVoiture.getInstance().getAll();
        if (listVoitures.size() > 0) {
            for (Voiture v : listVoitures) {
                Component model = makeVoitureModel(v);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    Label matricule, marque, puissance;

    private Container makeModelWithoutButtons(Voiture v) {
        setUIID("Activate");
        Container voitureModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        voitureModel.setUIID("containerRounded");

        matricule = new Label("Registration number : " + v.getMatricule());
        matricule.setUIID("labelDefault");

        marque = new Label("brand : " + v.getMarque());
        marque.setUIID("labelDefault");

        puissance = new Label("power : " + v.getPuissance());
        puissance.setUIID("labelDefault");

        voitureModel.addAll(
                matricule, marque, puissance
        );

        return voitureModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeVoitureModel(Voiture v) {
        setUIID("Activate");
        Container voitureModel = makeModelWithoutButtons(v);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("update");
        editBtn.setUIID("buttonWhiteCenter");

        editBtn.addActionListener(action -> {
            currentV = v;
            new ModifierVoitureLocateur(previous, v).show();

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

                ServiceVoiture.getInstance().delete(v.getId());
                new ListVoitureform(previous).show();
            });

            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        voitureModel.add(btnsContainer);

        return voitureModel;
    }

}
