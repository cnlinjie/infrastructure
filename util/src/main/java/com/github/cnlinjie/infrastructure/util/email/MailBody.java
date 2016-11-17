package com.github.cnlinjie.infrastructure.util.email;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-10-25
 */
public class MailBody {

    private MailConfig mailConfig;
    private String receiver; // 收件人的邮箱
    private String message;
    private String subject;
    private boolean isHtml= false;


    public MailBody () {
    }

    public MailBody (MailConfig mailConfig, String receiver, String message, String subject) {
        this.mailConfig = mailConfig;
        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
    }

    public MailBody (MailConfig mailConfig, String receiver, String message, String subject, boolean isHtml) {
        this.mailConfig = mailConfig;
        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
        this.isHtml = isHtml;
    }


    public String getSubject () {
        return subject;
    }

    public MailBody setSubject (String subject) {
        this.subject = subject;
        return this;
    }

    public MailConfig getMailConfig () {
        return mailConfig;
    }

    public MailBody setMailConfig (MailConfig mailConfig) {
        this.mailConfig = mailConfig;
        return this;
    }

    public String getReceiver () {
        return receiver;
    }

    public MailBody setReceiver (String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getMessage () {
        return message;
    }

    public MailBody setMessage (String message) {
        this.message = message;
        return this;
    }

    public boolean isHtml () {
        return isHtml;
    }

    public MailBody setHtml (boolean html) {
        isHtml = html;
        return this;
    }

}
