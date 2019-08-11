package com.mum.onetech.repository;

import com.mum.onetech.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
//     List<Credentials> findByCredentials_RoleAndCredentialsVerified(Role role,Integer integer);

}
