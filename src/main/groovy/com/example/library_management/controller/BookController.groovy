package com.example.library_management.controller

import com.example.library_management.model.Book
import com.example.library_management.service.BookService
import com.example.library_management.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class BookController {

    @Autowired
    BookService bookService

    @Autowired
    UserService userService

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("featuredBooks", bookService.findFeaturedBooks(6))
        "home"
    }

    @GetMapping("/dashboard")
    String dashboard(Model model, @AuthenticationPrincipal OAuth2User oauth2User) {
        def user = userService.getOrCreateUser(oauth2User)
        model.addAttribute("user", user)
        model.addAttribute("allBooks", bookService.getAllBooks())
        model.addAttribute("borrowedBooks", bookService.findBorrowedByUser(user))
        model.addAttribute("availableBooksCount", bookService.countAvailableBooks())
        model.addAttribute("totalBooksCount", bookService.countAllBooks())
        "dashboard"
    }

    @GetMapping("/books/new")
    String newBookForm(Model model) {
        model.addAttribute("book", new Book())
        "new-book"
    }

    @PostMapping("/books")
    String createBook(@ModelAttribute Book book) {
        bookService.saveBook(book)
        "redirect:/dashboard"
    }

    @PostMapping("/books/{id}/borrow")
    String borrowBook(@PathVariable Long id, @AuthenticationPrincipal OAuth2User oauth2User) {
        def user = userService.getOrCreateUser(oauth2User)
        bookService.borrowBook(id, user)
        "redirect:/dashboard"
    }

    @PostMapping("/books/{id}/return")
    String returnBook(@PathVariable Long id) {
        bookService.returnBook(id)
        "redirect:/dashboard"
    }
}