package com.example.batchtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

@EnableBatchProcessing
@Configuration
@Slf4j
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final BookJpa bookJpa;

    @Autowired
    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, BookJpa bookJpa) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.bookJpa = bookJpa;
    }

    @Bean
    public Job bookImportJop(@Qualifier("bookJobListener") JobExecutionListener jobListener
            , @Qualifier("step1") Step step1) {
        return jobBuilderFactory.get("bookImportJob")
                .listener(jobListener)
                .start(step1).build();
    }

    @Bean
    protected Step step1(@Qualifier("itemReader") ItemReader<Book> reader,
                         @Qualifier("itemProcessor") ItemProcessor<Book, Book> processor,
                         @Qualifier("itemWriter") ItemWriter<Book> writer,
                         @Qualifier("BookReadListener") ItemReadListener<Book> readListener,
                         @Qualifier("BookProcessListener") ItemProcessListener<Book, Book> processListener,
                         @Qualifier("BookWriterListener") ItemWriteListener<Book> writeListener) {
        return stepBuilderFactory.get("step1")
                .<Book, Book>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readListener)
                .listener(processListener)
                .listener(writeListener)
                .build();
    }

//    @Bean
//    protected Step step2(Tasklet tasklet) {
//        return stepBuilderFactory.get("step2")
//                .tasklet(tasklet)
//                .build();
//    }

    @Bean(value = "itemReader")
    public FlatFileItemReader<Book> bookItemRead(@Qualifier("bookFieldSetMapper") FieldSetMapper<Book> fieldSetMapper) {
        return new FlatFileItemReaderBuilder<Book>()
                .name("bookItemReader")
                .resource(new ClassPathResource("data.txt"))
                .lineTokenizer(new DelimitedLineTokenizer())
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean(value = "itemWriter")
    public ItemWriter<Book> writer() {
        log.info("begin writer.......");
        return list -> {
            log.info("write dataList : " + list);
            bookJpa.saveAll(list);
        };
    }
}


