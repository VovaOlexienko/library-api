package com.github.vova.olexienko.library.api.repository;

import com.github.vova.olexienko.library.api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
