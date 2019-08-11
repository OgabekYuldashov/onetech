package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Credentials;
import com.mum.onetech.repository.CredentialRepository;
import com.mum.onetech.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public List<Credentials> findUnverifiedSellers() {
        return credentialRepository.findByVerifiedFalse();
    }
}
