package com.assessment;

import com.assessment.dao.BookDao;
import com.assessment.dto.BookDto;
import com.assessment.entity.Book;
import com.assessment.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private  BookDao bookDao;

    @InjectMocks private BookService bookService;

    @Test
    void testBookByTitle() {
        Pageable pageable = Pageable.ofSize(10);


        List<Book> books =
         List.of(new Book(1L, "Java Basic", "ASHIK", "I190"),
                 new Book (100L, "Java ADVANCED", "RAHIM", "ISBN1234")) ;

        Page<Book> bookPages
                = new PageImpl<>(books, pageable, books.size());

        String title = "Java";

        Mockito.when(bookDao.findByTitleContainingIgnoreCase(
                title, pageable
        )).thenReturn(bookPages);

        Page<BookDto> booksByTitle = bookService.getBooksByTitle("Java", pageable);



        Assertions.assertEquals(2, booksByTitle.getContent().size());

        Assertions.assertEquals("Java ADVANCED", booksByTitle.getContent().get(1).getTitle());

        Mockito.verify(bookDao)
                .findByTitleContainingIgnoreCase(title, pageable);

    }
}
