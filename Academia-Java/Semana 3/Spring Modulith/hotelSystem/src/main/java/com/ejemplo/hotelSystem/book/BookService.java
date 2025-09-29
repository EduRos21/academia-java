package com.ejemplo.hotelSystem.book;

import com.ejemplo.hotelSystem.event.ClientRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

public class BookService {
    private final RoomBookRepository repository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public BookService(RoomBookRepository repository,
                        ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }


    public void clientEntry(String name) {
        RoomEntry roomEntry = new RoomEntry(null, name, LocalDateTime.now(), null, true);
        repository.save(roomEntry);
        // publish an event
        publisher.publishEvent(new ClientRegistered(name, roomEntry.getEntryTime()));

    }
}
