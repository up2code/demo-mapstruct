package com.example.demo.mapper;

import com.example.demo.dto.BookCreateRequest;
import com.example.demo.dto.BookDisplayNameRequest;
import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "coverName", target = "name")
    BookEntity bookCreateToBookEntity(BookCreateRequest bookCreateRequest);

    @Mapping(source = "bookDisplayNameRequest.displayName", target = "name")
    BookEntity bookCreateAndDisplayNameToBookEntity(BookCreateRequest bookCreateRequest, BookDisplayNameRequest bookDisplayNameRequest);

    @Mapping(source = "order.nestedOrder.bookOrder.coverName", target = "name")
    @Mapping(source = "order.nestedOrder.bookOrder", target = ".")
    BookEntity orderToBookEntityList(OrderRequest order);

    @Mapping(source = "displayName", target = "name")
    void updateBookEntityNameFromBookDisplayNameRequest(BookDisplayNameRequest bookDisplayNameRequest, @MappingTarget BookEntity bookEntity);

    default BookEntity bookDisplayNameToBookEntity(BookDisplayNameRequest bookDisplayNameRequest) {
        BookEntity entity = new BookEntity();

        String[] values = bookDisplayNameRequest.getDisplayName().split(";");

        entity.setName(values[0]);
        entity.setAuthor(values[1]);
        entity.setBookType("CUSTOM");

        return entity;
    }
}
