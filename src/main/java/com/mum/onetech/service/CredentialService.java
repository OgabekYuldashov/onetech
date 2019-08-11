package com.mum.onetech.service;

import com.mum.onetech.domain.Credentials;

import java.util.List;


public interface CredentialService {
    List<Credentials> findUnverifiedSellers();


}
