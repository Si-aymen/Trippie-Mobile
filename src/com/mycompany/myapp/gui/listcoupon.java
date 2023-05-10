package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.services.ServiceCoupon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listcoupon extends BaseForm {

    public listcoupon(Form previeus) {
        setUIID("Activate");
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        setTitle("our Copons");
        //UIBuilder uib1 =new UIBuilder();
        //setLayout(new FlowLayout(CENTER, CENTER));
        setLayout(BoxLayout.y());
//        add(new Label("ma liste de station"));
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);

        TextField searchField = new TextField("", "coupon Code");
        Button searchButton = new Button("Search");
        add(searchField);
        add(searchButton);
        searchButton.addActionListener(e -> {
            setUIID("Activate");
            String searchText = searchField.getText();
            ArrayList<Coupon> filteredEvents = new ArrayList<>();
            ArrayList<Coupon> tasks1 = ServiceCoupon.getInstance().getAllStations();
            for (Coupon ev : tasks1) {
                if (ev.getCode_coupon().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredEvents.add(ev);
                }
            }

            // Create a new form to display the search results
            Form searchResultsForm = new Form("search result", new BoxLayout(BoxLayout.Y_AXIS));
            searchResultsForm.setUIID("Activate");
            if (filteredEvents.isEmpty()) {
                searchResultsForm.add(new Label("no coupons"));
            } else {
                for (Coupon ev : filteredEvents) {
                    setUIID("Activate");
                    // Add the event details to the form
                    Label nameLabel = new Label("copon code : " + ev.getCode_coupon());
                    Label locationLabel = new Label("type : " + ev.getType());
                    Label availablePlacesLabel = new Label("number : " + ev.getNbr_utilisation());

                    // Add the labels to the form
                    searchResultsForm.add(nameLabel);
                    searchResultsForm.add(locationLabel);
                    searchResultsForm.add(availablePlacesLabel);
                }

            }

            // Show the search results form
            searchResultsForm.show();
        });

        Button trierButton = new Button("Trier");
        add(trierButton);

        trierButton.addActionListener(e -> {
            setUIID("Activate");
            ArrayList<Coupon> tasks2 = ServiceCoupon.getInstance().getAllStations();
            // Trier les événements par ordre alphabétique croissant en fonction de leur nom d'événement
            Collections.sort(tasks2, new Comparator<Coupon>() {
                @Override
                public int compare(Coupon event1, Coupon event2) {
                    return event1.getCode_coupon().compareToIgnoreCase(event2.getCode_coupon());
                }
            });

            // Afficher la liste triée des événements
            Form triResultsForm = new Form("sort ", new BoxLayout(BoxLayout.Y_AXIS));
            for (Coupon za : tasks2) {
                setUIID("Activate");
                Label eventLabel = new Label(za.getCode_coupon());
                Label eventLabel1 = new Label(za.getType());
                Label availablePlacesLabel = new Label("number of users : " + za.getNbr_utilisation());

                triResultsForm.add(eventLabel);
                triResultsForm.add(eventLabel1);
                triResultsForm.add(availablePlacesLabel);
            }
            triResultsForm.show();
        });

        Button btnValider = new Button("coupon Added");
        //btnValider.setUIID("LoginButton");
        btnValider.addActionListener(e -> {
            new AddCouponForm(this).show();
        });

        addAll(btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<Coupon> tasks = ServiceCoupon.getInstance().getAllStations();
        for (Coupon t : tasks) {
            setUIID("Activate");
            addElement(t);
            add(new Label(" "));

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previeus.showBack());

    }

    public void addElement(Coupon task) {
        setUIID("Activate");
        MultiButton sp = new MultiButton(task.getType());
        sp.setTextLine2("code : " + task.getCode_coupon());

        sp.addActionListener(e -> {
            new FormDetailscoupon(this, task.getId()).show();
        });
        //setUIID("CompletedTasks");
        add(sp);
        add(new Label("" + task.getDate_debut()));
        add(new Label("" + task.getDate_expiratio()));
        add(new Label("the number of user is " + task.getNbr_utilisation()));
        add(new Label("" + task.getTaux()));
        add(new Label("   "));

    }

}
