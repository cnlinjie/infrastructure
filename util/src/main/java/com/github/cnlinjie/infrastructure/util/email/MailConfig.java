package com.github.cnlinjie.infrastructure.util.email;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-10-25
 */
public class MailConfig {
    private  String ENCODEING = "UTF-8";

    private String host; // 服务器地址
    private String sender; // 发件人的邮箱
    private String name; // 发件人昵称
    private String username; // 账号
    private String password; // 密码
    private int port;
    private boolean isSSL = false;


    public MailConfig (String host, String sender, String name, String username, String password, int port,boolean isSSL) {
        this.host = host;
        this.sender = sender;
        this.name = name;
        this.username = username;
        this.password = password;
        this.port = port;
        this.isSSL = isSSL;
    }

    public String getENCODEING () {
        return ENCODEING;
    }

    public MailConfig setENCODEING (String ENCODEING) {
        this.ENCODEING = ENCODEING;
        return this;
    }

    public String getHost () {
        return host;
    }

    public MailConfig setHost (String host) {
        this.host = host;
        return this;
    }

    public String getSender () {
        return sender;
    }

    public MailConfig setSender (String sender) {
        this.sender = sender;
        return this;
    }

    public String getName () {
        return name;
    }

    public MailConfig setName (String name) {
        this.name = name;
        return this;
    }

    public String getUsername () {
        return username;
    }

    public MailConfig setUsername (String username) {
        this.username = username;
        return this;
    }

    public String getPassword () {
        return password;
    }

    public MailConfig setPassword (String password) {
        this.password = password;
        return this;
    }

    public int getPort () {
        return port;
    }

    public MailConfig setPort (int port) {
        this.port = port;
        return this;
    }

    public boolean isSSL () {
        return isSSL;
    }

    public MailConfig setSSL (boolean SSL) {
        isSSL = SSL;
        return this;
    }
}
