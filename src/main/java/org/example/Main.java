package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
        String userName = "root";
        String password = "";
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {

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
                    case "article modify":
                        modifyArticle(sc, conn);
                        break;
                    case "article delete":
                        deleteArticle(sc, conn);
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

        sc.close();
    }

    // 글 작성
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

    // 글 목록 출력
    private static void listArticles(Connection conn) {
        String sql = "SELECT * FROM jdbc ORDER BY id DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
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

    // 글 수정
    private static void modifyArticle(Scanner sc, Connection conn) {
        try {
            System.out.print("수정할 글의 ID를 입력해주세요: ");
            int id = Integer.parseInt(sc.nextLine());

            // 글이 존재하는지 확인
            String checkSql = "SELECT * FROM jdbc WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id);  // <-- 이거 빠져있었음!
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("해당 ID의 글이 존재하지 않습니다.");
                return;
            }

            // 새 제목, 내용 입력받기
            System.out.print("새 제목을 입력하세요: ");
            String newTitle = sc.nextLine();
            System.out.print("새 내용을 입력하세요: ");
            String newBody = sc.nextLine();

            // UPDATE 쿼리 실행
            String updateSql = "UPDATE jdbc SET title = ?, body = ? WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, newTitle);
            updateStmt.setString(2, newBody);
            updateStmt.setInt(3, id);

            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                System.out.println("글이 성공적으로 수정되었습니다.");
            }

        } catch (SQLException e) {
            System.out.println("글 수정 중에 오류가 있었습니다.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자로 입력해주세요.");
        }
    }
    private static void deleteArticle(Scanner sc, Connection conn) {
        try {
            System.out.print("삭제할 글의 ID를 입력해주세요: ");
            int id = Integer.parseInt(sc.nextLine());

            // 글이 존재하는지 확인
            String checkSql = "SELECT * FROM jdbc WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("해당 ID의 글이 존재하지 않습니다.");
                return;
            }

            // 삭제 실행
            String deleteSql = "DELETE FROM jdbc WHERE id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);
            int rows = deleteStmt.executeUpdate();

            if (rows > 0) {
                System.out.println("글이 성공적으로 삭제되었습니다.");
            }

        } catch (SQLException e) {
            System.out.println("글 삭제 중에 오류가 있었습니다.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자로 입력해주세요.");
        }
    }
}
