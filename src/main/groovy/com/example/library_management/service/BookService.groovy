package com.example.library_management.service

import com.example.library_management.model.Book
import com.example.library_management.model.User
import com.example.library_management.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService {

    @Autowired
    BookRepository bookRepository

    List<Book> findFeaturedBooks(int limit) {
        bookRepository.findByAvailableTrueOrderByTitleAsc().take(limit)
    }

    List<Book> findBorrowedByUser(User user) {
        bookRepository.findByUser(user)
    }

    long countAvailableBooks() {
        bookRepository.countByAvailableTrue()
    }

    long countAllBooks() {
        bookRepository.count()
    }

    List<Book> getAllAvailableBooks() {
        bookRepository.findByAvailable(true)
    }

    List<Book> getAllBooks() {
        bookRepository.findAll()
    }

    Book saveBook(Book book) {
        bookRepository.save(book)
    }

    Book borrowBook(Long bookId, User user) {
        def book = bookRepository.findById(bookId).orElse(null)
        if (book && book.available) {
            book.available = false
            book.user = user
            return bookRepository.save(book)
        }
        return null
    }

    Book returnBook(Long bookId) {
        def book = bookRepository.findById(bookId).orElse(null)
        if (book && !book.available) {
            book.available = true
            book.user = null
            return bookRepository.save(book)
        }
        return null
    }
}