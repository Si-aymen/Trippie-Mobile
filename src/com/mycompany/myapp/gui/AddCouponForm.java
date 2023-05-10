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
import java.util.Random;

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
 setUIID("Activate");
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
        Button btnGenerateCode = new Button("Generate Code");

        btnGenerateCode.addActionListener(e -> {
            String generatedCode = generateRandomCouponCode();
            tfCodeCoupon.setText(generatedCode);
        });

        btnAddCoupon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addNewCoupon();
            }
        });

        addAll(lblDateDebut, tfDateDebut, lblDateExpiration, tfDateExpiration, lblTaux, tfTaux,
                lblCodeCoupon, tfCodeCoupon, lblNbrUtilisation, tfNbrUtilisation, lblType, tfType,
                btnGenerateCode, btnAddCoupon);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addNewCoupon() {
        String dateDebutString = tfDateDebut.getText();
        String dateExpirationString = tfDateExpiration.getText();
        int taux = Integer.parseInt(tfTaux.getText().trim());
        String codeCoupon = tfCodeCoupon.getText().trim();
        int nbrUtilisation = Integer.parseInt(tfNbrUtilisation.getText().trim());
        String type = tfType.getText().trim();

        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = "";
        String dateExpiration = "";

        if (codeCoupon.isEmpty()) {
        Dialog.show("Error", "Please enter a code coupon", "OK", null);
        return;
    }
         // Validate taux
    try {
        taux = Integer.parseInt(tfTaux.getText().trim());
        if (taux <= 0) {
            Dialog.show("Error", "Taux must be a positive integer", "OK", null);
            return;
        }
    } catch (NumberFormatException e) {
        Dialog.show("Error", "Invalid value for Taux", "OK", null);
        return;
    }

     // Validate nbrUtilisation
    try {
        nbrUtilisation = Integer.parseInt(tfNbrUtilisation.getText().trim());
        if (nbrUtilisation < 0) {
            Dialog.show("Error", "Nombre d'utilisation must be a non-negative integer", "OK", null);
            return;
        }
    } catch (NumberFormatException e) {
        Dialog.show("Error", "Invalid value for Nombre d'utilisation", "OK", null);
        return;
    }
    
         // Validate type
    if (!type.equalsIgnoreCase("vip") && !type.equalsIgnoreCase("simple")) {
        Dialog.show("Error", "Type must be either 'VIP' or 'Simple'", "OK", null);
        return;
    }
        
        Coupon coupon = new Coupon(dateDebut, dateExpiration, taux, codeCoupon, nbrUtilisation, type);

        ServiceCoupon.getInstance().ajouterCoupon(coupon);

        Dialog.show("Success", "Coupon added successfully", new Command("OK"));
    }

   private String generateRandomCouponCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int codeLength = 6;

    for (int i = 0; i < codeLength; i++) {
        int randomIndex = random.nextInt(characters.length());
        char randomChar = characters.charAt(randomIndex);
        sb.append(randomChar);
    }

    return sb.toString();
}}
