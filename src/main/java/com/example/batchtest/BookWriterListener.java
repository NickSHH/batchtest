package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "BookWriterListener")
@Slf4j
public class BookWriterListener implements ItemWriteListener {
    @Override
    public void beforeWrite(List items) {
      log.info("before write...");
    }

    @Override
    public void afterWrite(List items) {
        log.info("writer write...");
    }

    @Override
    public void onWriteError(Exception exception, List items) {
        log.info("write error...");
    }
}
