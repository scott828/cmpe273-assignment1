package edu.sjsu.cmpe.library.model;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.sjsu.cmpe.library.domain.Book;


public final class LibraryModel {
    private static Map<Long, Book> bookRepository = new LinkedHashMap<Long, Book>();
   
    private LibraryModel() {} 

    
    public static Map<Long, Book> getBookRepository(){
        return bookRepository;
      }
}