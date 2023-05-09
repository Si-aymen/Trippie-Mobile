/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * author YourName
 */
public class HomeForm extends Form {

    public HomeForm(Form previeus) {
        setUIID("taswiraacc");
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddCoupon = new Button("Add Coupon");
        Button btnListCoupons = new Button("List Coupons");

        btnAddCoupon.addActionListener(e -> new AddCouponForm(this).show());
        btnListCoupons.addActionListener(e -> new listcoupon(this).show());

        addAll(btnAddCoupon, btnListCoupons);
    }
}
