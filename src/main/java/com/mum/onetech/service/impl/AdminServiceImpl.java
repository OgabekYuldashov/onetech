package com.mum.onetech.service.impl;

import com.mum.onetech.repository.AdminRepository;
import com.mum.onetech.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

}
