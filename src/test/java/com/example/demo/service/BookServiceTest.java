package com.example.demo.service;

import com.example.demo.dto.BookCreateRequest;
import com.example.demo.dto.BookType;
import com.example.demo.entity.BookEntity;
import com.example.demo.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {

    @Test
    public void shouldSaveToDB() {
        BookService bookService = new BookService(Mappers.getMapper(BookMapper.class));

        BookCreateRequest bookCreateRequest = new BookCreateRequest("Book A", "Author A", BookType.NOVEL);

        BookEntity bookEntity = bookService.saveToDB(bookCreateRequest);

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo( "Book A" );
        assertThat( bookEntity.getAuthor() ).isEqualTo( "Author A" );
        assertThat( bookEntity.getBookType() ).isEqualTo( "NOVEL" );
    }
}
