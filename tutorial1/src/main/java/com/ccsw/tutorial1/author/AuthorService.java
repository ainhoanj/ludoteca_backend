package com.ccsw.tutorial1.author;

import com.ccsw.tutorial1.author.model.Author;
import com.ccsw.tutorial1.author.model.AuthorDto;
import com.ccsw.tutorial1.author.model.AuthorSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    Author get(Long id);

    List<Author> findAll();

    Page<Author> findPage(AuthorSearchDto dto);


    void save(Long id, AuthorDto dto);


    void delete(Long id) throws Exception;

}
