package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.services.ServiceCoupon;
import java.util.ArrayList;

public class listcoupon extends Form {
   public listcoupon(Form previeus){
        getAllStyles().setBgColor(0xebfff8);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        setTitle("Nos coupons");
        //UIBuilder uib1 =new UIBuilder();
        //setLayout(new FlowLayout(CENTER, CENTER));
        setLayout(BoxLayout.y());
//        add(new Label("ma liste de station"));
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);

        Button btnValider = new Button("Ajouter un coupon");
        //btnValider.setUIID("LoginButton");
        btnValider.addActionListener(e -> {
            new AddCouponForm(this).show();
        });
        
        addAll(btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<Coupon> tasks = ServiceCoupon.getInstance().getAllStations();
        for (Coupon t : tasks) {
            addElement(t);
            add(new Label(" "));
        }  
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previeus.showBack());
    
    }
    public void addElement(Coupon task) {
        
        MultiButton sp = new MultiButton(task.getType());
        sp.setTextLine2("code : "+task.getCode_coupon());
        
        sp.addActionListener(e->{
            new FormDetailscoupon(this,task.getId()).show();
        });
        //setUIID("CompletedTasks");
        add(sp);
        add(new Label("salem"+task.getDate_debut()));
        add(new Label("salem"+task.getDate_expiratio()));
        add(new Label("le "+task.getNbr_utilisation()));
        add(new Label(""+task.getTaux()));
        add(new Label("   "));
   
   }
    
}
