package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "BookReadListener")
@Slf4j
public class BookReadListener implements ItemReadListener<Book> {
    @Override
    public void beforeRead() {
        log.info("before read...");
    }

    @Override
    public void afterRead(Book item) {
        log.info("after read..." + Optional.ofNullable(item));
    }

    @Override
    public void onReadError(Exception ex) {
        log.info("read error...");
    }
}
