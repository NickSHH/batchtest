package com.example.batchtest;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component(value = "bookFieldSetMapper")
public class BookFieldSetMapper implements FieldSetMapper<Book> {
    @Override
    public Book mapFieldSet(FieldSet fieldSet){
        Book book = new Book();
        book.setName(fieldSet.readString(0));
        book.setAuthor(fieldSet.readString(1));
        book.setPaperback(fieldSet.readInt(2));
        book.setPublisher(fieldSet.readString(3));
        book.setPrice(fieldSet.readDouble(4));
        book.setDescription(fieldSet.readString(5));
        book.setIsbn13(fieldSet.readString(6));
        return book;
    }
}
