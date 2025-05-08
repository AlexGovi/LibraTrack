package com.lilbrary.library_mananagement.service;

import com.lilbrary.library_mananagement.entity.Book;
import com.lilbrary.library_mananagement.entity.IssueRecord;
import com.lilbrary.library_mananagement.entity.User;
import com.lilbrary.library_mananagement.repository.BookRepository;
import com.lilbrary.library_mananagement.repository.IssueRecordRepository;
import com.lilbrary.library_mananagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueRecord issueTheBook(Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not Found!!!"));

        if(book.getQuantity()<=0 || !book.getIsAvailable()){
            throw new RuntimeException("Book not Available!!");
        }

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(userName).orElseThrow(()->new RuntimeException("User Not Found!!"));

        IssueRecord issueRecord=new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0){
            book.setIsAvailable(false);
        }

        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);


    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord=issueRecordRepository.findById(issueRecordId).orElseThrow(()->new RuntimeException("Issue Record Not Found!!"));

        if (issueRecord.getIsReturned()){
            throw new RuntimeException("Book is already Returned!!");
        }

        Book book=issueRecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setIsAvailable(true);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);

        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);


    }
}
