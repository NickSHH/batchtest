package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component(value = "BookReadListener")
@Slf4j
public class BookReadListener implements ItemReadListener {
    @Override
    public void beforeRead() {
        log.info("before read...");
    }

    @Override
    public void afterRead(Object item) {
        log.info("after read...");
    }

    @Override
    public void onReadError(Exception ex) {
        log.info("read error...");
    }
}
