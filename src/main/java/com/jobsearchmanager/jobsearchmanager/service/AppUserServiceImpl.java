package com.jobsearchmanager.jobsearchmanager.service;

import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.ArrayList;

import static java.util.Objects.isNull;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser edit(AppUser appUserToEdit) throws NoResultException {
        this.appUserRepository.findById(appUserToEdit.getId()).orElseThrow(NoResultException::new);

        return this.appUserRepository.save(appUserToEdit);
    }

    @Override
    public AppUser getAppUserByUsername(String username) throws NoResultException {
        return this.appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser save(AppUser appUser) {
        appUser.setPassword(this.passwordEncoder.encode(appUser.getPassword()));
        return this.appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByUsername(username);

        if (isNull(appUser)) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
    }


}
