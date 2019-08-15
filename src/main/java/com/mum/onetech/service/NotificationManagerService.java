package com.mum.onetech.service;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Notification;
import com.mum.onetech.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationManagerService {

    @Autowired
    BuyerService buyerService;

    public boolean sendNotificaiton(Seller sender, String email, String message){

        Buyer buyer = buyerService.findByEmail(email);
        if (buyer == null) return false;

        buyer.addNotification(new Notification(message, buyer, sender));
        buyerService.save(buyer);

        return true;
    }
}
