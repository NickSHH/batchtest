package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component(value = "itemProcessor")
@Slf4j
public class BookItemProcessor implements ItemProcessor<Book, Book> {

    @Override
    public Book process(Book item){
        if (null != item) {
            item.setDescription("already pass through the process");
        } else {
            log.info("book is null");
        }
        return item;
    }
}
