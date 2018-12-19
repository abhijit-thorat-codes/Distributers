package com.example.dummy.aman;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Panel extends AppCompatActivity {
    static String customName,customAddress,shopName,note,customOrder,orderAmount,masterDetails,customEr=" 00/-",customCaf="00",customSimex="00";
    TextView name,shop,address;
    Button erBtn,cafBtn,voucher,remark,submit;
    final String to[]={"abhijitthorat143@gmail.com"};
    final String store[]={"","","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        name=(TextView)findViewById(R.id.text_name);
        shop=(TextView)findViewById(R.id.text_shop);
        address=(TextView)findViewById(R.id.text_add);

        erBtn=(Button)findViewById(R.id.buttonER);
        cafBtn=(Button)findViewById(R.id.buttonCAF);
        voucher=(Button)findViewById(R.id.buttonVoucher);
        remark=(Button)findViewById(R.id.buttonRemark);
        submit=(Button)findViewById(R.id.buttonSubmit);

        Intent getData=getIntent();
        customName=getData.getStringExtra("Name");
        shopName=getData.getStringExtra("Shop");
        customAddress=getData.getStringExtra("Address");
        customOrder=getData.getStringExtra("Order");
        orderAmount=getData.getStringExtra("Amount");

        name.setText(customName);
        shop.setText(shopName);
        address.setText(customAddress);


        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send=new Intent(Panel.this,Items.class);
                send.putExtra("Aname",customName);
                send.putExtra("Ashop",shopName);
                send.putExtra("Aaddress",customAddress);
                startActivity(send);
            }
        });

        erBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater = Panel.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                final EditText edt = (EditText) dialogView.findViewById(R.id.getInput);
                edt.setText(store[0]);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setIcon(R.drawable.aman_logo);
                dialogBuilder.setTitle("Enter ER Voucher Amount !");
                dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if(android.text.TextUtils.isDigitsOnly(edt.getText().toString()))
                        {
                            customEr = edt.getText().toString();
                            store[0]=customEr;
                                                    }
                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });

        cafBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater = Panel.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog2, null);
                final EditText caf = (EditText) dialogView.findViewById(R.id.getNumber);
                final EditText simex = (EditText) dialogView.findViewById(R.id.simexNum);
                caf.setText(store[1]);
                simex.setText(store[2]);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setIcon(R.drawable.aman_logo);
                dialogBuilder.setTitle("Enter Number of CAF !");
                dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        customCaf = caf.getText().toString();
                        customSimex = simex.getText().toString();
                        store[1]=customCaf;     store[2]=customSimex;
                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });

        remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater = Panel.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog3, null);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setIcon(R.drawable.aman_logo);
                final EditText edt = (EditText) dialogView.findViewById(R.id.rema);
                edt.setText(store[3]);
                dialogBuilder.setTitle("Give us your suggestion!");
                dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        note = edt.getText().toString();
                        store[3]=note;

                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        note="";
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            masterDetails="Customer Information :";
            masterDetails=masterDetails+"\n\nName :  "+customName;
            masterDetails=masterDetails+"\nShop  :  "+shopName;
            masterDetails=masterDetails+"\nAddress :  "+customAddress;
            masterDetails=masterDetails+"\n\nVoucher Order :\n"+customOrder;
            masterDetails=masterDetails+"\n\nElectronic Recharge Order :  "+"₹ "+customEr+"/-";
            masterDetails=masterDetails+"\n\nCustomer Application Forms :  "+customCaf;
            masterDetails=masterDetails+"\n\nSim Exchange :  "+customCaf;
            masterDetails=masterDetails+"\n\nRemark :  "+note;
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, to);
             email.putExtra(Intent.EXTRA_SUBJECT, "Voucher Orders !");
            email.putExtra(Intent.EXTRA_TEXT, masterDetails);
            email.setType("message/rfc822");
            email.setPackage("com.google.android.gm");
            //startActivity(Intent.createChooser(email, "Choose an Email client :"));
            startActivity(email);
        }
    });
    }
}