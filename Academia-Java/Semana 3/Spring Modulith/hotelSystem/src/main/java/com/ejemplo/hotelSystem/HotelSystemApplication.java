package com.ejemplo.hotelSystem;

import com.ejemplo.hotelSystem.allocation.Room;
import com.ejemplo.hotelSystem.allocation.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelSystemApplication.class, args);
	}

    @Bean
    CommandLineRunner initSlots(RoomRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Room(null, "A1", true, null));
                repo.save(new Room(null, "A2", true, null));
                repo.save(new Room(null, "B1", true, null));
                repo.save(new Room(null, "C1", true, null));
                repo.save(new Room(null, "D1", true, null));
            }
        };
    }
}
