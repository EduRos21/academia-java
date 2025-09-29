package com.ejemplo.hotelSystem.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired private ExitService exitService;

    @PostMapping("/registro")
    public ResponseEntity<String> entry(@RequestParam String name) {
        bookService.clientEntry(name);
        return ResponseEntity.ok("Cliente registrado: " + name);
    }

    @PostMapping("/salda")
    public ResponseEntity<String> exit(@RequestParam String name) {
        exitService.clientExit(name);
        return ResponseEntity.ok("Cliente salio: " + name);
    }
}
