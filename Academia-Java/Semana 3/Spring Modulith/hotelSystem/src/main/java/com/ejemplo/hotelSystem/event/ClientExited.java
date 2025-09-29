package com.ejemplo.hotelSystem.event;

import java.time.LocalDateTime;


    public record ClientExited(String name,
                                     LocalDateTime entryTime, LocalDateTime exitTime) {
    }

