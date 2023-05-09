/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.services.ServiceCoupon;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FormDetailscoupon extends Form{
    public FormDetailscoupon(Form previeus,int a){
    
    getAllStyles().setBgColor(0xebfff8);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        setTitle("mon coupons");
        //UIBuilder uib1 =new UIBuilder();
        //setLayout(new FlowLayout(CENTER, CENTER));
        setLayout(BoxLayout.y());
//        add(new Label("ma liste de station"));
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);

   
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<Coupon> tasks = ServiceCoupon.getInstance().getAllStations();
        for (Coupon t : tasks) {
            if(t.getId()==a){
            addElement(t);
            add(new Label(" "));
            Button btn = new Button( "modifier");
            Button btn1 = new Button( "supprimer");
             
            btn.addActionListener(e ->{ new FormModifierCoupon(this,t).show() ;});
            btn1.addActionListener(e ->{  ServiceCoupon.getInstance().supprimer(t);
            Dialog.show("Salut", "coupon supprimer avec succes", "OK", null);
            previeus.showBack();
             });
                add(btn);
                add(btn1);
            
        } }
        
        
        
        
        
        
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previeus.showBack());
    }
    public void addElement(Coupon task) {
       
        
       add(new Label(""+task.getType()));
        add(new Label(""+task.getCode_coupon()));
        add(new Label(""+task.getDate_debut()));
        add(new Label(""+task.getDate_expiratio()));
        add(new Label(" "+task.getNbr_utilisation()));
        add(new Label(""+task.getTaux()));
        add(new Label("   "));
   
   }
}
