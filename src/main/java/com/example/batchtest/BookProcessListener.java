package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "BookProcessListener")
@Slf4j
public class BookProcessListener implements ItemProcessListener<Book, Book> {
    @Override
    public void beforeProcess(Book item) {
       log.info("before process..." + Optional.ofNullable(item));
    }

    @Override
    public void afterProcess(Book item, Book result) {
        log.info("after process...");
        log.info("================item:" + Optional.ofNullable(item));
        log.info("================result:" + Optional.ofNullable(result));
    }

    @Override
    public void onProcessError(Book item, Exception e) {
        log.info("process error...");
    }
}
