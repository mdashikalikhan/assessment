package com.assessment.service;

import com.assessment.dao.BookDao;
import com.assessment.dto.BookDto;
import com.assessment.entity.Book;
import com.assessment.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookDao.findAll(pageable)
                .map(this::convertToDto)
                ;
    }

    public BookDto getBookById(Long id) {
        return bookDao.findById(id).map(
                this::convertToDto
        ).orElseThrow(()->new BookNotFoundException(id));
    }

    public Page<BookDto> getBooksByTitle(String title, Pageable pageable) {
        return bookDao.findByTitleContainingIgnoreCase(title, pageable)
                .map(this::convertToDto);
    }

    private BookDto convertToDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(),book.getAuthor(),
                book.getIsbn());
    }


}
