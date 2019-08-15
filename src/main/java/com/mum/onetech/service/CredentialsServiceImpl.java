package com.mum.onetech.service;

import com.mum.onetech.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public boolean isEmailInUse(String email){
        return credentialsRepository.findByEmail(email) != null;
    }
}
