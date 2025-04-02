package org.example.dao;

import org.example.dto.Article;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {
    private Connection conn;

    public ArticleDao(Connection conn) {
        this.conn = conn;
    }

    public int insert(String title, String body) {
        SecSql sql = new SecSql();
        sql.append("INSERT INTO article");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("title = ?,", title);
        sql.append("`body` = ?", body);
        return DBUtil.insert(conn, sql);
    }

    public List<Article> findAll() {
        SecSql sql = new SecSql();
        sql.append("SELECT * FROM article ORDER BY id DESC");

        List<Map<String, Object>> rows = DBUtil.selectRows(conn, sql);
        List<Article> articles = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            articles.add(new Article(row));
        }
        return articles;
    }

    public Article findById(int id) {
        SecSql sql = new SecSql();
        sql.append("SELECT * FROM article WHERE id = ?", id);
        Map<String, Object> row = DBUtil.selectRow(conn, sql);

        if (row.isEmpty()) return null;
        return new Article(row);
    }

    public boolean delete(int id) {
        SecSql sql = new SecSql();
        sql.append("DELETE FROM article WHERE id = ?", id);
        return DBUtil.delete(conn, sql) > 0;
    }

    public void update(int id, String title, String body) {
        SecSql sql = new SecSql();
        sql.append("UPDATE article SET updateDate = NOW()");
        if (!title.isEmpty()) sql.append(", title = ?", title);
        if (!body.isEmpty()) sql.append(", `body` = ?", body);
        sql.append("WHERE id = ?", id);
        DBUtil.update(conn, sql);
    }
}