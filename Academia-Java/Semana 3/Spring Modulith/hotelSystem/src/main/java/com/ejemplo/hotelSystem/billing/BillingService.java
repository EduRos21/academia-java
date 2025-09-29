package com.ejemplo.hotelSystem.billing;

import com.ejemplo.hotelSystem.event.ClientExited;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BillingService {


    private final BillingRecordRepository billingRecordRepository;

    public BillingService(BillingRecordRepository billingRecordRepository) {
        this.billingRecordRepository = billingRecordRepository;
    }

    @EventListener
    public void handleClientExit(ClientExited event) {

        Duration duration = Duration.between(event.entryTime(), event.exitTime());
        double amount = Math.max(800, (duration.toMinutes() / 60.0) * 100);

        BillingRecord record = new BillingRecord(null, event.name(), amount, event.exitTime());
        billingRecordRepository.save(record);

        System.out.println("✅ Cuenta $" + amount + " para el cliente " + event.name() +
                " desde " + event.entryTime() + " hasta " + event.exitTime());
    }


}
