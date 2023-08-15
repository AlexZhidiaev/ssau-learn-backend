package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Book;

@Data
public class BookDto {
    private Long id;
    private String name;

    public Book toBook() {
        Book book = new Book();
        BeanUtils.copyProperties(this, book);
        return book;
    }

    public static BookDto fromBook(Book book) {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }
}
