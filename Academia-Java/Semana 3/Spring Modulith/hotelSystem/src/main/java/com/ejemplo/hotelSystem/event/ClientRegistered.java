package com.ejemplo.hotelSystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

public class ClientRegistered {
    private final String name;
    private final LocalDateTime entryTime;

    public ClientRegistered(String name, LocalDateTime entryTime) {
        this.name = name;
        this.entryTime = entryTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClientRegistered that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(entryTime, that.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, entryTime);
    }

    @Override
    public String toString() {
        return "ClientRegistered{" +
                "name='" + name + '\'' +
                ", entryTime=" + entryTime +
                '}';
    }
}
