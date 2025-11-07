package com.example.library_management.model

import com.example.library_management.model.Book
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String email
    String name
    String avatarUrl

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Book> borrowedBooks = []
}