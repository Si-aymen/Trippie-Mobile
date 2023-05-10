package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.*;

public class ListReclamationform extends Form {

    Form previous;

    public static Reclamation currentV = null;
    Button addBtn;

    public ListReclamationform(Form previous) {
        setUIID("Activate");
        this.previous = previous;
        setTitle("list car");
        setLayout(BoxLayout.y());

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        super.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> new AddReclamationForm(previous).show());

    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {
        setUIID("Activate");
        ArrayList<Reclamation> listReclamations = ServiceReclamation.getInstance().getAll();
        if (listReclamations.size() > 0) {
            for (Reclamation r : listReclamations) {
                Component model = makeVoitureModel(r);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    Label etat, commentaire, type;

    private Container makeModelWithoutButtons(Reclamation r) {
        setUIID("Activate");
        Container voitureModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        voitureModel.setUIID("containerRounded");

        type = new Label("type : " + r.getType());
        type.setUIID("labelDefault");

        commentaire = new Label("commentaire : " + r.getCommentaire());
        commentaire.setUIID("labelDefault");

        etat = new Label("etat : " + r.getEtat());
        etat.setUIID("labelDefault");

        voitureModel.addAll(
                type, commentaire, etat
        );

        return voitureModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeVoitureModel(Reclamation r) {
        setUIID("Activate");
        Container voitureModel = makeModelWithoutButtons(r);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

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

                ServiceReclamation.getInstance().delete(r.getId());
                new ListReclamationform(previous).show();
            });

            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        voitureModel.add(btnsContainer);

        return voitureModel;
    }

}
