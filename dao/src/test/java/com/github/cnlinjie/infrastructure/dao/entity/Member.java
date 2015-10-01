package com.github.cnlinjie.infrastructure.dao.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * member 实体类
 * Tue Jan 20 17:50:03 CST 2015 linjie
 */


@Table(name = "member")
@Entity
public class Member {
    @Id
    @Column(name = "pk_id")
    private Long pkId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    private String username;



    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getPkId() {
        return pkId;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

