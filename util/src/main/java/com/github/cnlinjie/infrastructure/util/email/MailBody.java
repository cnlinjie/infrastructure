package com.github.cnlinjie.infrastructure.util.email;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-10-25
 */
public class MailBody {

    private MailConfig mailConfig;
//    private String receiver; // 收件人的邮箱
    private String message;
    private String subject;
    private boolean isHtml= false;

    private List<String> receivers = Lists.newArrayList();

    public MailBody () {
    }

    public MailBody (MailConfig mailConfig, String receiver, String message, String subject) {
        this.mailConfig = mailConfig;
//        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
        receivers.add(receiver);
    }

    public MailBody (MailConfig mailConfig, String receiver, String message, String subject, boolean isHtml) {
        this.mailConfig = mailConfig;
//        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
        this.isHtml = isHtml;
        receivers.add(receiver);
    }


    public MailBody (MailConfig mailConfig, List<String> receivers, String message, String subject) {
        this.mailConfig = mailConfig;
//        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
        this.receivers.addAll(receivers);
    }

    public MailBody (MailConfig mailConfig, List<String> receivers, String message, String subject, boolean isHtml) {
        this.mailConfig = mailConfig;
//        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
        this.isHtml = isHtml;
        this.receivers.addAll(receivers);
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

    public List<String> getReceivers () {
        return receivers;
    }

//    public MailBody setReceiver (String receiver) {
//        this.receiver = receiver;
//        return this;
//    }

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

    public void addTo(String receiver) {
        receivers.add(receiver);
    }

    public void addTo(List<String> receivers) {
        this.receivers.addAll(receivers);
    }

}
