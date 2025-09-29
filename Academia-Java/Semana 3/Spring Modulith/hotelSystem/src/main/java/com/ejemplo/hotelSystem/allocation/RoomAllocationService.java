package com.ejemplo.hotelSystem.allocation;

import com.ejemplo.hotelSystem.event.ClientExited;
import com.ejemplo.hotelSystem.event.ClientRegistered;
import org.springframework.context.event.EventListener;

public class RoomAllocationService {
    private final RoomRepository roomRepository;

    public RoomAllocationService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @EventListener
    public void handleClientEntry(ClientRegistered event) {

        Room room = roomRepository.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException("🚫 No hay habitaciones disponibles!"));
        room.setAvailable(false);
        room.setClientNumber(event.getName());
        roomRepository.save(room); //UPDATE

        System.out.println("🅿️ Allocated Slot " + room.getRoomCode() + " to vehicle " + event.getName());
    }

    @EventListener
    public void handleClientExit(ClientExited event) {
        roomRepository.findByClientName(event.name()
                )
                .ifPresentOrElse(room -> {
                    room.setAvailable(true); // free the slot
                    room.setClientNumber(null);
                    roomRepository.save(room);
                    System.out.println("🅿️ Habitacion libre " + room.getRoomCode() + " del cliente " + event.name());
                }, () -> {
                    throw new RuntimeException("🚫 No hay habitaciones para el cliente " + event.name());
                });
    }
}
