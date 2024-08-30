package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entity.Library;
import com.example.LibraryManagement.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LibraryService {

    //using repository
    @Autowired
    LibraryRepository libraryRepository;

    public Library saveBook(Library lib){

        return libraryRepository.save(lib);
    }

    public List<Library> fetchBooks(){

        return libraryRepository.findAll();

    }

    public String DeleteBookByID(int bookId){

        libraryRepository.deleteById(bookId);

        return "Book removed from library Successfully!";
    }


    public String CleanUpLibrary(){

        libraryRepository.deleteAll();

        return "All books deleted successfully";
    }


    public Library findBooksWithCost(int cost){

       return libraryRepository.findByBookCost(cost);

    }

    public Library findBooksWithName(String Book_Name){

        return libraryRepository.findByBookName(Book_Name);

    }





}
