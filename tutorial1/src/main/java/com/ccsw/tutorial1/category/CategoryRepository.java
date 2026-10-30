package com.ccsw.tutorial1.category;

import com.ccsw.tutorial1.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
