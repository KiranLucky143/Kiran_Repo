package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entity.Library;
import com.example.LibraryManagement.Repository.LibraryRepository;
import com.example.LibraryManagement.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    //using Service
  @Autowired
  LibraryService libraryService;



    @PostMapping("/save")
    public Library SaveBooks(@RequestBody Library library){

        return libraryService.saveBook(library);
    }

    @GetMapping("/getByBookName")
    public Library getByBookName(@RequestParam String bookName){

        return libraryService.findBooksWithName(bookName);
    }

    @GetMapping("/findAllBooks")
    public List<Library> getAllBooks(){

        return libraryService.fetchBooks();
    }

    @GetMapping("price/{cost}")
    public Library getByCost(@PathVariable Integer cost){

        return libraryService.findBooksWithCost(cost);
    }

    @DeleteMapping("/BookID/{bookId}")
    public String DeleteBook(@PathVariable Integer bookId){

        libraryService.DeleteBookByID(bookId);

        return "";
    }

    @DeleteMapping("/deleteAll")
    public String DeleteAll(){

        libraryService.CleanUpLibrary();

        return "";
    }



}

