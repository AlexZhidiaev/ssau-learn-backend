package ssau.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.BookDto;
import ssau.learn.service.BookService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getBooks(@RequestParam(required = false) String name) {
        return bookService.getBooks(name);
    }

    @GetMapping(path = "/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto create(@RequestBody BookDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return bookService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto dto) {
        dto.setId(id);
        return bookService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
