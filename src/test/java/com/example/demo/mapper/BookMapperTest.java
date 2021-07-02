package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMapperTest {

    @Test
    public void shouldMapBookCreateRequestToEntity() {

        BookCreateRequest bookCreateRequest = new BookCreateRequest("Book A", "Author A", BookType.NOVEL);

        BookEntity bookEntity = Mappers.getMapper( BookMapper.class ).bookCreateToBookEntity(bookCreateRequest);

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo( "Book A" );
        assertThat( bookEntity.getAuthor() ).isEqualTo( "Author A" );
        assertThat( bookEntity.getBookType() ).isEqualTo( "NOVEL" );
    }

    @Test
    public void shouldMapToEntityWithMultipleSource() {

        BookCreateRequest bookCreateRequest = new BookCreateRequest("Book A", "Author A", BookType.NOVEL);
        BookDisplayNameRequest bookDisplayNameRequest = new BookDisplayNameRequest("CustomBookName");

        BookEntity bookEntity = Mappers.getMapper( BookMapper.class ).bookCreateAndDisplayNameToBookEntity(bookCreateRequest, bookDisplayNameRequest);

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo( "CustomBookName" );
        assertThat( bookEntity.getAuthor() ).isEqualTo( "Author A" );
        assertThat( bookEntity.getBookType() ).isEqualTo( "NOVEL" );
    }

    @Test
    public void shouldMapBookDisplayNameToEntity() {

        BookDisplayNameRequest bookDisplayNameRequest = new BookDisplayNameRequest("CustomBook;BySomeAuthor");

        BookEntity bookEntity = Mappers.getMapper( BookMapper.class ).bookDisplayNameToBookEntity(bookDisplayNameRequest);

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo( "CustomBook" );
        assertThat( bookEntity.getAuthor() ).isEqualTo( "BySomeAuthor" );
        assertThat( bookEntity.getBookType() ).isEqualTo( "CUSTOM" );
    }

    @Test
    public void shouldMapOrderRequestToBookEntity() {

        BookOrder bookOrder = new BookOrder("Book A", "Author A", BookType.NOVEL);

        BookEntity bookEntity = Mappers.getMapper( BookMapper.class ).orderToBookEntityList(new OrderRequest(new NestedOrder(bookOrder)));

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo( "Book A" );
        assertThat( bookEntity.getAuthor() ).isEqualTo( "Author A" );
        assertThat( bookEntity.getBookType() ).isEqualTo( "NOVEL" );
    }

    @Test
    public void shouldUpdateBookEntityName() {

        BookDisplayNameRequest bookDisplayNameRequest = new BookDisplayNameRequest("CustomBookName");

        BookEntity bookEntity = new BookEntity("Book A", "Author A", "NOVEL");

        Mappers.getMapper( BookMapper.class ).updateBookEntityNameFromBookDisplayNameRequest(bookDisplayNameRequest, bookEntity);

        assertThat( bookEntity ).isNotNull();
        assertThat( bookEntity.getName() ).isEqualTo("CustomBookName");
        assertThat( bookEntity.getAuthor() ).isEqualTo( "Author A");
        assertThat( bookEntity.getBookType() ).isEqualTo( "NOVEL");
    }
}
