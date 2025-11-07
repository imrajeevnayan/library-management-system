package com.example.library_management.model

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String title
    String author
    String isbn
    boolean available = true

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user
}