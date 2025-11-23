package com.github.jorgemaicon.service;

import com.github.jorgemaicon.data.dto.BookDTO;
import com.github.jorgemaicon.exception.RequiredObjectIsNullException;
import com.github.jorgemaicon.model.Book;
import com.github.jorgemaicon.repository.BookRepository;
import com.github.jorgemaicon.unitetests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var book1 = books.get(1);
        var book12 = books.get(12);

        assertEquals("Title Test 1", book1.getTitle());
        assertEquals("Author Test 1", book1.getAuthor());
        assertEquals(30.3, book1.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0), book1.getLaunch_date());

        assertEquals("Title Test 12", book12.getTitle());
        assertEquals("Author Test 12", book12.getAuthor());
        assertEquals(20.1, book12.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0), book12.getLaunch_date());

        assertTrue(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertTrue(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertTrue(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertTrue(book12.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/12")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(book12.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(book12.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertTrue(book12.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertTrue(book12.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/12")
                        && link.getType().equals("DELETE")
                )
        );
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setID(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        var result = service.findById(1L);

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Title Test 1", book.getTitle());
        assertEquals("Author Test 1", book.getAuthor());
        assertEquals(30.3, book.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0), book.getLaunch_date());
    }

    @Test
    void create() {
        Book book = input.mockEntity(1);
        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(book);
        var result = service.create(dto);

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Title Test 1", book.getTitle());
        assertEquals("Author Test 1", book.getAuthor());
        assertEquals(30.3, book.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0), book.getLaunch_date());
    }

    @Test
    void createWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {service.create(null);
                }
        );

        String expectedMessage = "It is no allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        book.setID(1L);
        Book persisted = input.mockEntity(1);
        persisted.setID(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);
        var result = service.update(dto);

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Title Test 1", book.getTitle());
        assertEquals("Author Test 1", book.getAuthor());
        assertEquals(30.3, book.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 23, 10, 0, 0, 0), book.getLaunch_date());
    }

    @Test
    void updateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {service.update(null);
                }
        );

        String expectedMessage = "It is no allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

//    @Test
//    void delete() {
//        Book book = input.mockEntity(1);
//        book.setID(1L);
//
//        when(repository.findById(1L)).thenReturn(Optional.of(book));
//        service.delete(1L);
//
//        verify(repository, times(1)).findById(1L);
//        verify(repository, times(1)).delete(book);
//        verifyNoMoreInteractions(repository);
//    }
}