package com.mum.onetech.service;

import com.mum.onetech.domain.Category;

import java.time.LocalDate;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category finById(Long cid);
}
