package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.helper.eventHelper.EmailGeneratingEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EmailService {
    private static int count = 0;
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEmailGenerating(EmailGeneratingEvent event){

        System.out.println("Email has beeen Sent: "+count++);
    }
}
