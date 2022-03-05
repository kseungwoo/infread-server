package com.planters.parsley.service;

import com.planters.parsley.component.AccountRepositoryDecorator;
import com.planters.parsley.domain.Account;
import com.planters.parsley.vo.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final AccountRepositoryDecorator accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Account account = accountRepository.findByEmail(email);

        return UserPrincipal.createBy(account.toVo());
    }
}