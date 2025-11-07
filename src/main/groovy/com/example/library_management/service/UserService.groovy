package com.example.library_management.service

import com.example.library_management.model.User
import com.example.library_management.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class)

    @Autowired
    UserRepository userRepository

    User getOrCreateUser(OAuth2User oauth2User) {
        def email = oauth2User.getAttribute("email")

        // First, try to get the user from the database
        def user = userRepository.findByEmail(email).orElse(null)

        if (!user) {
            // If user doesn't exist, create a new one
            log.info("Creating new user with email: {}", email)

            // Safely get attributes, providing defaults if they are missing
            def name = oauth2User.getAttribute("name") ?: "Unknown User"
            def avatarUrl = oauth2User.getAttribute("picture") ?: "/images/default-avatar.png"

            user = new User(
                    email: email,
                    name: name,
                    avatarUrl: avatarUrl
            )
            user = userRepository.save(user)
        } else {
            // If user exists, just log that we found them
            log.info("Found existing user with email: {}", email)
        }

        return user
    }
}