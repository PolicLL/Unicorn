package com.example.unicorn.controller;

import com.example.unicorn.dao.ArticleRepository;
import com.example.unicorn.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @PostMapping
    public void saveArticle(@RequestBody ArticleEntity articleEntity) {

        articleRepository.save(articleEntity);
    }

    @GetMapping
    public Iterable<ArticleEntity> getArticles() {

        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ArticleEntity getArticle(@PathVariable(value = "id") Long id) {

        Optional<ArticleEntity> tempArticle = articleRepository.findById(id);

        return tempArticle.orElseGet(ArticleEntity::new);

    }

    @GetMapping("/update")
    public void updateArticle(@RequestBody ArticleEntity articleEntity){
        articleRepository.save(articleEntity);
    }

    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable(value = "id") Long id){
        articleRepository.deleteById(id);
    }

}
