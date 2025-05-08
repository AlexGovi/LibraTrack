package com.lilbrary.library_mananagement.service;

import com.lilbrary.library_mananagement.dto.BookDTO;
import com.lilbrary.library_mananagement.entity.Book;
import com.lilbrary.library_mananagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book Not Found!!"));
        return book;
    }

    public Book addBook(BookDTO bookDTO) {
        Book book=new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setIsAvailable(bookDTO.getIsAvailable());
        book.setQuantity(bookDTO.getQuantity());

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book=bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not Found!!"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setIsAvailable(bookDTO.getIsAvailable());
        book.setQuantity(bookDTO.getQuantity());

        return bookRepository.save(book);

    }

    public void deleteBookById(Long id) {

        bookRepository.deleteById(id);
    }
}
