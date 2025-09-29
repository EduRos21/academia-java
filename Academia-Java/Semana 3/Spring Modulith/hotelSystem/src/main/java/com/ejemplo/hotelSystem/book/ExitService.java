package com.ejemplo.hotelSystem.book;

import com.ejemplo.hotelSystem.event.ClientExited;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

public class ExitService {
    private final RoomBookRepository repository;
    private final ApplicationEventPublisher publisher;

    public ExitService(RoomBookRepository repository,
                       ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }


    public void clientExit(String name) {
        RoomEntry entry = repository.findByNameAndActiveTrue(name)
                .orElseThrow(() -> new RuntimeException("🚫 No hay habitaciones activas para el cliente " + name));

        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);
        repository.save(entry);
        publisher.publishEvent(new ClientExited(name, entry.getEntryTime(), entry.getExitTime()));
    }
}
