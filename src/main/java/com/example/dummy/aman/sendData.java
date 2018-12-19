package com.example.dummy.aman;

import android.content.Context;

import java.text.NumberFormat;

/**
 * Created by Dummy on 22/03/2017.
 */

public class sendData {

    String name,shop,address;
    String orderList,details;
    double amount;
    int erVoucher,cafNumber;
    NumberFormat nf=NumberFormat.getInstance();
    String amt=nf.format(amount),remark;

    public String collectData()
    {
            //info=userInfo.split(";");
            details = "Customer Details :\n";
            details = details+"\n\nName :"+name;
            details = details+"\nShop :"+shop;
            details = details+"\nAddress :"+address;
            details = details+"\n\nVoucher Order :"+orderList;
            details = details+"\n\nAmount:"+amt;
            details = details+"\n\nElectronic Recharge :"+erVoucher;
            details = details+"\n\nCustomer Application Forms :"+cafNumber;
            details = details+"\n\nRemark :"+remark;
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        System.out.println("Name in sendData-"+name);

        return details;
    }
}
