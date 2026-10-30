package com.ccsw.tutorial1.category;

import com.ccsw.tutorial1.category.model.Category;
import com.ccsw.tutorial1.category.model.CategoryDto;

import java.util.List;


public interface CategoryService {

    Category get(Long id);

    List<CategoryDto> findAll();


    void save(Long id, CategoryDto dto);


    void delete(Long id) throws Exception;

}
