package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component(value = "BookWriterListener")
@Slf4j
public class BookWriterListener implements ItemWriteListener<Book> {
    @Override
    public void beforeWrite(List<? extends Book> items) {
        log.info("before write..." + Optional.ofNullable(items));
    }

    @Override
    public void afterWrite(List<? extends Book> items) {
        log.info("after write..." + Optional.ofNullable(items));
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Book> items) {
        log.info("write error...");
    }
}
