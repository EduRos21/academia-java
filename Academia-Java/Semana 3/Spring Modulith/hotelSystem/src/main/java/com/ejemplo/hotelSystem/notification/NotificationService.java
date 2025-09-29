package com.ejemplo.hotelSystem.notification;

import com.ejemplo.hotelSystem.event.ClientExited;
import com.ejemplo.hotelSystem.event.ClientRegistered;
import org.springframework.context.event.EventListener;

public class NotificationService {
    @EventListener
    public void notifyOnClientEntry(ClientRegistered event) {
        // Logic to send notification to the user
        System.out.println("📩 Notificación: Cliente " + event.getName() +
                " registrado a " + event.getEntryTime() + ". Bienvenido!");
    }

    @EventListener
    public void notifyOnClientExit(ClientExited event) {
        // Logic to send notification to the user
        System.out.println("📩 Notificación: Cliente " + event.name() + " ha salido. Gracias por su visita!");
    }
}
