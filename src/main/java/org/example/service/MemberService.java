package org.example.service;

import org.example.dao.MemberDao;

import java.sql.Connection;

public class MemberService {
    private MemberDao memberDao;

    public MemberService(Connection conn) {
        this.memberDao = new MemberDao(conn);
    }

    public boolean isLoginIdDup(String loginId) {
        return memberDao.isLoginIdDup(loginId);
    }

    public int join(String loginId, String loginPw, String name) {
        return memberDao.insert(loginId, loginPw, name);
    }
}
