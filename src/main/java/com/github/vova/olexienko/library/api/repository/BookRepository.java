package com.github.vova.olexienko.library.api.repository;

import com.github.vova.olexienko.library.api.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByGenresId(Long genreId, PageRequest pageRequest);

    Page<Book> findByNameContainingIgnoreCaseOrAuthorsNameContainingIgnoreCase(String name, String authorName, PageRequest pageRequest);
}
