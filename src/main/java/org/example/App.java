package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public void run() {
        System.out.println("==프로그램 시작==");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();

            Connection conn = null;

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            try {
                conn = DriverManager.getConnection(url, "root", "");

                int actionResult = doAction(conn, sc, cmd);

                if (actionResult == -1) {
                    System.out.println("==프로그램 종료==");
                    sc.close();
                    break;
                }

            } catch (SQLException e) {
                System.out.println("에러 1 : " + e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int doAction(Connection conn, Scanner sc, String cmd) {

        if (cmd.equals("exit")) {
            return -1;
        }

        if (cmd.equals("member join")) {
            new MemberController(conn, sc).doJoin();
            return 0;
        }

        if (cmd.equals("article write")) {
            new ArticleController(conn, sc).write();
            return 0;
        } else if (cmd.equals("article list")) {
            new ArticleController(conn, sc).list();
            return 0;
        } else if (cmd.startsWith("article detail")) {
            String[] cmdBits = cmd.split(" ");
            if (cmdBits.length < 3) {
                System.out.println("번호를 입력해주세요.");
                return 0;
            }
            int id;
            try {
                id = Integer.parseInt(cmdBits[2]);
            } catch (NumberFormatException e) {
                System.out.println("번호는 정수로 입력해주세요.");
                return 0;
            }
            new ArticleController(conn, sc).detail(id);
            return 0;
        } else if (cmd.startsWith("article modify")) {
            String[] cmdBits = cmd.split(" ");
            if (cmdBits.length < 3) {
                System.out.println("번호를 입력해주세요.");
                return 0;
            }
            int id;
            try {
                id = Integer.parseInt(cmdBits[2]);
            } catch (NumberFormatException e) {
                System.out.println("번호는 정수로 입력해주세요.");
                return 0;
            }
            new ArticleController(conn, sc).modify(id);
            return 0;
        } else if (cmd.startsWith("article delete")) {
            String[] cmdBits = cmd.split(" ");
            if (cmdBits.length < 3) {
                System.out.println("번호를 입력해주세요.");
                return 0;
            }
            int id;
            try {
                id = Integer.parseInt(cmdBits[2]);
            } catch (NumberFormatException e) {
                System.out.println("번호는 정수로 입력해주세요.");
                return 0;
            }
            new ArticleController(conn, sc).delete(id);
            return 0;
        }
        System.out.println("알 수 없는 명령어입니다. 다시 입력해주세요.");
        return 0;
    }
}

