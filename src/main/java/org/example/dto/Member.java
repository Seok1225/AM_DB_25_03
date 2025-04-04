package org.example.dto;

import java.util.Map;

public class Member {
    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String loginPw;
    private String name;

    public Member(String regDate, int id, String updateDate, String loginId, String loginPw, String name) {
        this.regDate = regDate;
        this.id = id;
        this.updateDate = updateDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public Member(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.regDate = (String) row.get("regDate");
        this.updateDate = (String) row.get("updateDate");
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");
        this.name = (String) row.get("name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
