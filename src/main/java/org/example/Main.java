package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
        String userName = "root";
        String password = "";
        Scanner sc = new Scanner(System.in);

        try (
                Connection conn = DriverManager.getConnection(url, userName, password)
        ) {
            while (true) {
                System.out.print("명령어 > ");
                String command = sc.nextLine().trim();

                switch (command) {
                    case "article write":
                        writeArticle(sc, conn);
                        break;

                    case "article list":
                        listArticles(conn);
                        break;

                    case "exit":
                        System.out.println("프로그램을 종료합니다.");
                        return;

                    default:
                        System.out.println("알 수 없는 명령어입니다.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 실패!");
            e.printStackTrace();
        }
    }

    private static void writeArticle(Scanner sc, Connection conn) {
        try {
            System.out.print("제목을 입력하세요: ");
            String title = sc.nextLine();

            System.out.print("내용을 입력하세요: ");
            String body = sc.nextLine();

            String sql = "INSERT INTO jdbc (title, body) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, body);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("글이 성공적으로 저장되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("글 저장 중 오류 발생!");
            e.printStackTrace();
        }
    }

    // 글 목록 조회
    public static void listArticles(Connection conn) {
        String sql = "SELECT * FROM jdbc ORDER BY id DESC";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            System.out.println("=== 게시글 목록 ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");

                System.out.printf("번호: %d | 제목: %s | 내용: %s%n", id, title, body);
            }
        } catch (SQLException e) {
            System.out.println("글 목록 조회 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
