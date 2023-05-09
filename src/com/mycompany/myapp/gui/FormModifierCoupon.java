/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.services.ServiceCoupon;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class FormModifierCoupon extends Form {
    public  FormModifierCoupon(Form previeus,Coupon a){
        
        setTitle(" Modidfier Reservation ");

    setTitle(" Modidfier Reservation ");
       TextField dd = new TextField(a.getDate_debut(), "entrer votre date debut");
       TextField df = new TextField(a.getDate_expiratio(), "Nombre Adulte");
      TextField taux = new TextField(String.valueOf(a.getTaux()), "Entrer votre email pour recevoir les détails de paiement");
       TextField code = new TextField(a.getCode_coupon(), "Entrer votre email pour receverer les details de paiement");
       TextField nbr= new TextField(String.valueOf(a.getNbr_utilisation()), "Entrer votre email pour recevoir les détails de paiement");

       TextField type = new TextField(a.getType(), "Entrer votre type pour receverer les details de paiement");
       addAll(dd,df,taux,code,nbr);
        
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateDebut= "";
    String dateExpiration="";
    
    
    
       Button btnMod = new Button("MODIFIER");
 
       Button btnret = new Button("RETOUR");
        addAll(btnMod,btnret);
        
       btnMod.addActionListener(e->{ 
           String dateDebutString = dd.getText();
    String dateExpirationString = df.getText();
    int taux1 = Integer.parseInt(taux.getText().trim());
    String codeCoupon = code.getText().trim();
    int nbrUtilisation = Integer.parseInt(nbr.getText().trim());
    String type1 = type.getText().trim();

     a.setDate_debut(dateDebutString);
    a.setDate_expiratio(dateExpirationString);
    a.setTaux(taux1);
    a.setCode_coupon(codeCoupon);
    a.setNbr_utilisation(nbrUtilisation);
    a.setType(type1);
           ServiceCoupon.getInstance().modifierReservationExcursion(a);
           Dialog.show("Salut", "coupon modifier avec succes", "OK", null);
       });
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previeus.showBack());
           btnret.addActionListener(e->{
               
               previeus.showBack();
               
           });
    }
    
 
}
