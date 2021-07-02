package com.example.demo.service;

import com.example.demo.dto.BookCreateRequest;
import com.example.demo.entity.BookEntity;
import com.example.demo.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    public BookEntity saveToDB(BookCreateRequest bookCreateRequest) {
        return bookMapper.bookCreateToBookEntity(bookCreateRequest);
    }

}
