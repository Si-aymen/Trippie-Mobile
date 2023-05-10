package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mycompany.myapp.entities.Cov;
import com.mycompany.myapp.services.ServiceCov;
import java.util.*;

public class ListCo_voiturageform extends Form {

    Form previous;

    public static Cov currentV = null;
    Button addBtn;

    public ListCo_voiturageform(Form previous) {
        setUIID("Activate");
        this.previous = previous;
        setTitle("list car Pool");
        setLayout(BoxLayout.y());

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        super.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> new AddCarChauffeurForm(previous).show());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {
        setUIID("Activate");
        ArrayList<Cov> listVoitures = ServiceCov.getInstance().getAll();
        if (listVoitures.size() > 0) {
            for (Cov v : listVoitures) {
                Component model = makeVoitureModel(v);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    Label depart, destination, nmbr_place, sep;

    private Container makeModelWithoutButtons(Cov v) {
        setUIID("Activate");
        Container voitureModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        voitureModel.setUIID("containerRounded");

        depart = new Label("depart: " + v.getDepart());
        depart.setUIID("labelDefault");

        destination = new Label("destination : " + v.getDestination());
        destination.setUIID("labelDefault");

        nmbr_place = new Label("number of seats  : " + v.getNbre_place());
        nmbr_place.setUIID("labelDefault");

        sep = new Label("---------------------------------------- ");
        sep.setUIID("labelDefault");

        voitureModel.addAll(
                depart, destination, nmbr_place, sep
        );

        return voitureModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeVoitureModel(Cov v) {
        setUIID("Activate");
        Container voitureModel = makeModelWithoutButtons(v);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("update");
        editBtn.setUIID("buttonWhiteCenter");

        editBtn.addActionListener(action -> {
            currentV = v;
            new ModifierCo_voiturageChauffeur(previous, v).show();

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

                ServiceCov.getInstance().delete(v.getId());
                new ListCo_voiturageform(previous).show();
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
