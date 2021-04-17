package com.example.security;

import com.example.dbmodel.UserAccount;
import com.example.repository.UserAccountRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserAccountRepository userAccountRepository;

    public UserDetailsServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountRepository getUserAccountRepository() {
        return userAccountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserAccount ua = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException());
        return new UserDetailsImpl(ua.getId(), ua.getUsername(), ua.getPassword(),
                ua.isAdmin(), ua.getStudent() == null
                ? null
                : ua.getStudent().getId());
    }

}
