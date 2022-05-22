package com.jobsearchmanager.jobsearchmanager.repository;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    void shouldReturnByUsername() {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("testUsername");

        this.appUserRepository.save(user);

        AppUser testUsername = this.appUserRepository.findByUsername("testUsername");

        assertEquals(user.getUsername(),testUsername.getUsername());
    }
}