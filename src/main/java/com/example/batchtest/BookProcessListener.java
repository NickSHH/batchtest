package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component(value = "BookProcessListener")
@Slf4j
public class BookProcessListener implements ItemProcessListener {
    @Override
    public void beforeProcess(Object item) {
       log.info("before process...");
    }

    @Override
    public void afterProcess(Object item, Object result) {
        log.info("after process...");
    }

    @Override
    public void onProcessError(Object item, Exception e) {
        log.info("process error...");
    }
}
