package com.lilbrary.library_mananagement.repository;

import com.lilbrary.library_mananagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
