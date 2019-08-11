package com.mum.onetech.service;

import com.mum.onetech.domain.Category;
import com.mum.onetech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category finById(Long cid) {
        return categoryRepository.findById(cid).orElse(null);
    }
}
