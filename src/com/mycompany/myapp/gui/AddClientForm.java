package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceClient;

public class AddClientForm extends BaseForm {

    public AddClientForm(Form previous, Role r) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
         setUIID("Activate");
        // getTitleArea().setUIID("Container");
        //Form previous = Display.getInstance().getCurrent();
        //tb.setBackCommand("", e -> previous.showBack());
       
        TextField tfgsm = new TextField("", "gsm");
        TextField img = new TextField("", "img");
        TextField tfemail = new TextField("", "email");
        TextField tfpass = new TextField("", "password");
      
        Button btnValider = new Button("Add");

        tfgsm.setSingleLineTextArea(false);
        // img.setSingleLineTextArea(false);
        tfemail.setSingleLineTextArea(false);

        tfpass.setSingleLineTextArea(false);

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(img),
                createLineSeparator(),
                new FloatingHint(tfgsm),
                createLineSeparator(),
                new FloatingHint(tfemail),
                createLineSeparator(),
                new FloatingHint(tfpass),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                FlowLayout.encloseCenter(btnValider)
        ));
       

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfgsm.getText().length() == 0) {
                    Dialog.show("Alert", "Please select a role", new Command("OK"));
                } else {
                    // Use tfgsm.getText() to get the file path
                    Client c = new Client(Integer.parseInt(tfgsm.getText()), tfemail.getText().toString(), tfpass.getText().toString(), img.getText().toString());
                    boolean success = ServiceClient.getInstance().addClientJSON(r, c);

                    if (success) {
                        Dialog.show("Success", "Client added", new Command("OK"));
                        // AddClientForm roleForm = new AddClientForm(previous, addedRole);
                        // roleForm.show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        //  addAll(tfgsm, tfemail, tfpass, btnValider, chooseImgBtn,img);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
