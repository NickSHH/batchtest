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
            log.info(item.toString());
        } else {
            log.info("book is null");
        }
        return item;
    }
}
