package org.example.service;

import org.example.dao.ArticleDao;
import org.example.dto.Article;

import java.sql.Connection;
import java.util.List;

public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(Connection conn) {
        this.articleDao = new ArticleDao(conn);
    }

    public int write(String title, String body) {
        return articleDao.insert(title, body);
    }

    public List<Article> findAll() {
        return articleDao.findAll();
    }

    public Article findById(int id) {
        return articleDao.findById(id);
    }

    public boolean delete(int id) {
        return articleDao.delete(id);
    }

    public void modify(int id, String newTitle, String newBody) {
        articleDao.update(id, newTitle, newBody);
    }
}
