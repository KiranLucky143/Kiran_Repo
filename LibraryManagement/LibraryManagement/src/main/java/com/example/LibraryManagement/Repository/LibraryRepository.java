package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface LibraryRepository extends JpaRepository<Library,Integer> {

    Library findByBookName(String book_name);

    Library findByBookCost(int book_cost);

    }
