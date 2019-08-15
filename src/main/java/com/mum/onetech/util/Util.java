package com.mum.onetech.util;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Notification;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class Util {

    public static boolean isPositiveInteger(String strNum) {
        if (strNum == null) return false;
        return strNum.matches("^([1-9])+([0-9])*$");
    }


    public static DecimalFormat df2 = new DecimalFormat("#.##");


    public static String randomUUID() {
        // Creating a random UUID (Universally unique identifier)
        return UUID.randomUUID().toString();
    }

    public static boolean addNotificationforFollower(List<Buyer> buyers , String message){
        for(Buyer buyer:buyers){
            Notification notification =new Notification();
            notification.setMessage(message);
            buyer.addNotification(notification);        }
        return true;
    }


}
