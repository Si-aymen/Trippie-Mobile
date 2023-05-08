package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Locateur;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.services.ServiceLocateur;

public class AddLocateurForm extends BaseForm {

    public AddLocateurForm(Form previous, Role r) {
        setLayout(BoxLayout.y());
        TextField tfagence = new TextField("", "Agencey name");
        TextField tfimg = new TextField("", "Img");
        TextField tfgsm = new TextField("", "gsm");
        TextField tfemail = new TextField("", "email");
        TextField tfpass = new TextField("", "password");

        Button btnValider = new Button("Add");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfimg.getText().length() == 0) {
                    Dialog.show("Alert", "Please select a role", new Command("OK"));
                } else {
                    Locateur c = new Locateur(Integer.parseInt(tfgsm.getText()),tfagence.getText().toString(), tfemail.getText().toString(), tfpass.getText().toString(), tfimg.getText().toString());
                    boolean success = ServiceLocateur.getInstance().addLocateurJSON(r, c);

                    if (success) {
                        Dialog.show("Success", "Locateur added", new Command("OK"));
                        // AddLocateurForm roleForm = new AddLocateurForm(previous, addedRole);
                        // roleForm.show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfimg,tfagence, tfgsm, tfemail, tfpass, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
