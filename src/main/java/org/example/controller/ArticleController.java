package org.example.controller;

import org.example.service.ArticleService;
import org.example.dto.Article;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    private final ArticleService articleService;
    private final Scanner sc;
    private final Connection conn;

    public ArticleController(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
        this.articleService = new ArticleService(conn);
    }

    public void write() {
        System.out.println("== 게시글 작성 ==");
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        int id = articleService.write(title, body);
        System.out.printf("%d번 글이 작성되었습니다.\n", id);
    }

    public void list() {
        System.out.println("== 게시글 목록 ==");
        List<Article> articles = articleService.findAll();

        if (articles.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.println("번호 / 제목");
        for (Article article : articles) {
            System.out.printf("%d / %s\n", article.getId(), article.getTitle());
        }
    }

    public void detail(int id) {
        Article article = articleService.findById(id);

        if (article == null) {
            System.out.println("해당 글이 존재하지 않습니다.");
            return;
        }

        System.out.println("== 게시글 상세 ==");
        System.out.printf("번호 : %d\n", article.getId());
        System.out.printf("작성일 : %s\n", article.getRegDate());
        System.out.printf("수정일 : %s\n", article.getUpdateDate());
        System.out.printf("제목 : %s\n", article.getTitle());
        System.out.printf("내용 : %s\n", article.getBody());
    }

    public void delete(int id) {
        boolean deleted = articleService.delete(id);
        if (deleted) {
            System.out.println(id + "번 글이 삭제되었습니다.");
        } else {
            System.out.println("해당 글이 존재하지 않습니다.");
        }
    }

    public void modify(int id) {
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.println("해당 글이 존재하지 않습니다.");
            return;
        }

        System.out.print("새 제목 : ");
        String newTitle = sc.nextLine().trim();
        System.out.print("새 내용 : ");
        String newBody = sc.nextLine().trim();

        articleService.modify(id, newTitle, newBody);
        System.out.println(id + "번 글이 수정되었습니다.");
    }
}
