package com.mum.onetech.repository;

import com.mum.onetech.domain.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends CrudRepository<Credentials,Long> {

    List<Credentials> findByVerifiedFalse();
}
