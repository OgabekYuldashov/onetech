package com.mum.onetech.repository;

import com.mum.onetech.domain.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

    Credentials findByEmail(String email);
}
