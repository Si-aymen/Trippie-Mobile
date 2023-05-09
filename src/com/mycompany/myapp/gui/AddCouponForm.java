package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.services.ServiceCoupon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCouponForm extends Form {

    private TextField tfDateDebut;
    private TextField tfDateExpiration;
    private TextField tfTaux;
    private TextField tfCodeCoupon;
    private TextField tfNbrUtilisation;
    private TextField tfType;

    public AddCouponForm(Form previous) {
        setTitle("Add a new coupon");
        setLayout(BoxLayout.y());

        Label lblDateDebut = new Label("Date Debut:");
        Label lblDateExpiration = new Label("Date Expiration:");
        Label lblTaux = new Label("Taux:");
        Label lblCodeCoupon = new Label("Code Coupon:");
        Label lblNbrUtilisation = new Label("Nombre d'utilisation:");
        Label lblType = new Label("Type:");

        tfDateDebut = new TextField("", "yyyy-mm-dd");
        tfDateExpiration = new TextField("", "yyyy-mm-dd");
        tfTaux = new TextField("", "Taux");
        tfCodeCoupon = new TextField("", "Code Coupon");
        tfNbrUtilisation = new TextField("", "Nombre d'utilisation");
        tfType = new TextField("", "Type");

        Button btnAddCoupon = new Button("Add Coupon");

        btnAddCoupon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addNewCoupon();
            }
        });

        addAll(lblDateDebut, tfDateDebut, lblDateExpiration, tfDateExpiration, lblTaux, tfTaux,
                lblCodeCoupon, tfCodeCoupon, lblNbrUtilisation, tfNbrUtilisation, lblType, tfType, btnAddCoupon);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

   private void addNewCoupon() {
    String dateDebutString = tfDateDebut.getText();
    String dateExpirationString = tfDateExpiration.getText();
    int taux = Integer.parseInt(tfTaux.getText().trim());
    String codeCoupon = tfCodeCoupon.getText().trim();
    int nbrUtilisation = Integer.parseInt(tfNbrUtilisation.getText().trim());
    String type = tfType.getText().trim();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateDebut= "";
    String dateExpiration="";
    
//    try {
//        dateDebut = dateFormat.parse(dateDebutString);
//        dateExpiration = dateFormat.parse(dateExpirationString);
//    } catch (ParseException e) {
//        // Handle the parsing exception
//        e.printStackTrace();
//        return;
//    }

    Coupon coupon = new Coupon(dateDebut, dateExpiration, taux, codeCoupon, nbrUtilisation, type);

    ServiceCoupon.getInstance().ajouterCoupon(coupon);

    Dialog.show("Success", "Coupon added successfully", new Command("OK"));
}
}
