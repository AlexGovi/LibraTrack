package com.lilbrary.library_mananagement.controller;

import com.lilbrary.library_mananagement.dto.BookDTO;
import com.lilbrary.library_mananagement.entity.Book;
import com.lilbrary.library_mananagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getallbooks")
    public ResponseEntity<List<Book>> getAllBooks(){

        return ResponseEntity.ok(bookService.getAllBooks());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){

        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO){

        return ResponseEntity.ok(bookService.addBook(bookDTO));

    }

    @PutMapping("/updatebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO){

        return ResponseEntity.ok(bookService.updateBook(id,bookDTO));

    }

    @PutMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
         bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }


}
