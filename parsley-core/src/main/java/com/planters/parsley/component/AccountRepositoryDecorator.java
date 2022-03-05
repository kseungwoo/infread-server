package com.planters.parsley.component;

import com.planters.parsley.constant.ResponseCode;
import com.planters.parsley.domain.Account;
import com.planters.parsley.exception.CommonException;
import com.planters.parsley.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class AccountRepositoryDecorator {

    private final AccountRepository accountRepository;

    public Account findById(final Long id) {

        final Optional<Account> accountOptional = accountRepository.findById(id);

        final Account account =
                accountOptional.orElseThrow(() -> new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));

        return account;
    }

    public Account findByEmail(final String email) {

        final Optional<Account> accountOptional = accountRepository.findByEmail(email);

        final Account account =
                accountOptional.orElseThrow(() -> new CommonException(ResponseCode.ACCOUNT_NOT_EXISTED));

        return account;
    }

    public void checkIfEmailDuplicated(final String email) {

        final Optional<Account> accountOptional = accountRepository.findByEmail(email);

        if (accountOptional.isPresent()) {
            throw new CommonException(ResponseCode.ACCOUNT_EMAIL_DUPLICATED);
        }
    }

    public Account save(final Account account) {

        return accountRepository.save(account);
    }
}
