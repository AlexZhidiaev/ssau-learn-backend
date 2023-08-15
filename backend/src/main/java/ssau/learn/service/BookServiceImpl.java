package ssau.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ssau.learn.dao.BookRepository;
import ssau.learn.dto.BookDto;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final DataSource dataSource;

    @Override
    public List<BookDto> getBooks(String name) {
        return (name == null ? bookRepository.findAll() : bookRepository.findAllByNameContaining(name)).
                stream().map(BookDto::fromBook).collect(Collectors.toList());
    }

    @Override
    public BookDto save(BookDto dto) {
        return BookDto.fromBook(bookRepository.save(dto.toBook()));
    }

    @Override
    public BookDto getBook(Long id) {
        return BookDto.fromBook(bookRepository.getById(id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> select() {
        return new NamedParameterJdbcTemplate(dataSource).query("select id, name from books", Collections.emptyMap(), (rs, rowNum) -> {
            BookDto book = new BookDto();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            return book;
        });
    }
}
