package com.example.library_management.repository

import com.example.library_management.model.Book
import com.example.library_management.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailable(boolean available)
    List<Book> findByAvailableTrueOrderByTitleAsc()
    List<Book> findByUser(User user)
    long countByAvailableTrue()
}