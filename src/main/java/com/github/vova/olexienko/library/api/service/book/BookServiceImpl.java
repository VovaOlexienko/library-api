package com.github.vova.olexienko.library.api.service.book;

import com.github.vova.olexienko.library.api.dto.book.BookDto;
import com.github.vova.olexienko.library.api.dto.book.BooksPageDto;
import com.github.vova.olexienko.library.api.dto.book.CrudBookDto;
import com.github.vova.olexienko.library.api.dto.book.GetBooksDto;
import com.github.vova.olexienko.library.api.entity.Author;
import com.github.vova.olexienko.library.api.entity.Book;
import com.github.vova.olexienko.library.api.entity.Genre;
import com.github.vova.olexienko.library.api.mapper.BookMapper;
import com.github.vova.olexienko.library.api.repository.AuthorRepository;
import com.github.vova.olexienko.library.api.repository.BookRepository;
import com.github.vova.olexienko.library.api.repository.GenreRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public BooksPageDto getBooks(GetBooksDto dto) {
        PageRequest pageRequest = toPagination(dto.getPage());
        Page<Book> booksPage = getBooksPage(dto, pageRequest);
        return BooksPageDto.builder()
                .books(convertToBookDtos(booksPage))
                .pagesNumber(booksPage.getTotalPages())
                .build();
    }

    @Override
    @Transactional
    public void createBook(CrudBookDto dto) {
        bookRepository.save(convertToBook(dto));
    }

    @Override
    @Transactional
    public void updateBook(Long id, CrudBookDto dto) {
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new ValidationException(String.format("Book with id = [%s] is absent", id)));
        Book newBook = convertToBook(dto);
        newBook.setId(oldBook.getId());
        bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private Page<Book> getBooksPage(GetBooksDto dto, PageRequest pageRequest) {
        if (Objects.nonNull(dto.getGenreId())) {
            return bookRepository.findByGenresId(dto.getGenreId(), pageRequest);
        }
        if (StringUtils.isNotBlank(dto.getSearchOption())) {
            return bookRepository.findByNameContainingIgnoreCaseOrAuthorsNameContainingIgnoreCase(dto.getSearchOption(), dto.getSearchOption(), pageRequest);
        }
        return bookRepository.findAll(pageRequest);
    }

    private PageRequest toPagination(int page) {
        return PageRequest.of(page, 20, Sort.by(Sort.Direction.ASC, "name"));
    }

    private List<BookDto> convertToBookDtos(Page<Book> booksPage) {
        return booksPage.map(book -> {
            BookDto bookDto = bookMapper.toDto(book);
            bookDto.setGenres(book.getGenres().stream().map(Genre::getId).toList());
            bookDto.setAuthors(book.getAuthors().stream().map(Author::getId).toList());
            return bookDto;
        }).toList();
    }

    private Book convertToBook(CrudBookDto dto) {
        Book book = bookMapper.toEntity(dto);
        book.setAuthors(authorRepository.findAllById(dto.getAuthors()));
        book.setGenres(genreRepository.findAllById(dto.getGenres()));
        return book;
    }
}
