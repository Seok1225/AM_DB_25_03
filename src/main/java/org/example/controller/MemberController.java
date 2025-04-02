package org.example.controller;

import org.example.service.MemberService;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController {
    private MemberService memberService;
    private Scanner sc;
    private Connection conn;

    public MemberController(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
        this.memberService = new MemberService(conn);
    }

    public void doJoin() {
        System.out.println("== 회원가입 ==");

        String loginId;
        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();
            if (loginId.isEmpty()) {
                System.out.println("아이디를 입력해주세요.");
                continue;
            }
            if (memberService.isLoginIdDup(loginId)) {
                System.out.println("이미 사용 중인 아이디입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        String loginPw, loginPwConfirm;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();
            if (loginPw.isEmpty()) {
                System.out.println("비밀번호를 입력해주세요.");
                continue;
            }
            System.out.print("비밀번호 확인 : ");
            loginPwConfirm = sc.nextLine().trim();
            if (!loginPw.equals(loginPwConfirm)) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
            break;
        }

        String name;
        while (true) {
            System.out.print("이름 : ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("이름을 입력해주세요.");
                continue;
            }
            break;
        }

        int id = memberService.join(loginId, loginPw, name);
        System.out.printf("%d번 회원으로 가입되었습니다.\n", id);
    }
}