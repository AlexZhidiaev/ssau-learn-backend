package ssau.learn.service;

import ssau.learn.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks(String name);

    BookDto save(BookDto dto);

    BookDto getBook(Long id);

    void delete(Long id);

    List<BookDto> select();
}
