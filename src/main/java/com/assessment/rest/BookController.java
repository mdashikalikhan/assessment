package com.assessment.rest;

import com.assessment.dto.BookDto;
import com.assessment.service.BookService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<BookDto> bookPage = bookService.getAllBooks(pageable);


        PagedModel<BookDto> pagedModel = new PagedModel<>(bookPage);

        return ResponseEntity.ok(bookPage);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PagedModel<BookDto>> getBookById(@NotNull @Positive @PathVariable Long id,
                                                           @PageableDefault(size = 1) Pageable pageable) {
        BookDto bookDto =  bookService.getBookById(id);
        Page<BookDto> bookPage = new PageImpl<>(List.of(bookDto));
        PagedModel<BookDto> pagedModel = new PagedModel<>(bookPage);
        return ResponseEntity.ok(pagedModel);

    }

    @GetMapping("/title/{title}")
    public ResponseEntity<PagedModel<BookDto>> getBookByTitle(@NotNull  @PathVariable String title,
                                                           @PageableDefault(size = 1) Pageable pageable) {
        Page<BookDto> bookPage  =  bookService.getBooksByTitle(title, pageable);
        PagedModel<BookDto> pagedModel = new PagedModel<>(bookPage);
        return ResponseEntity.ok(pagedModel);


    }
}
