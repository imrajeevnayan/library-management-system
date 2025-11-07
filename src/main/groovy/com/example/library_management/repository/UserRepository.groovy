package com.example.library_management.repository

import com.example.library_management.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email)
}