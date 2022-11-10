package com.example.unicorn.dao;

import com.example.unicorn.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>{
}
