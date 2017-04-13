package com.github.cnlinjie.infrastructure.util.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 邮箱发送工具
 *
 * @author linjie
 * @version 0.0.1
 * @date 16-6-30
 */
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    public static boolean sendHtml (MailInfo mail) {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHtmlMsg(mail.getMessage());
            send(mail, email);
            logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
                    + " 失败");
            return false;
        }
    }

    public static  boolean send(MailBody body) {
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            if (body.isHtml()) {
                htmlEmail.setHtmlMsg(body.getMessage());
            } else {
                htmlEmail.setTextMsg(body.getMessage());
            }
            MailConfig config = body.getMailConfig();
            if (config.isSSL()) {
                htmlEmail.setSSLOnConnect(true);
                htmlEmail.setSSLCheckServerIdentity(true);
                htmlEmail.setSslSmtpPort(config.getPort()+"");
            }
            // 发送email
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            htmlEmail.setHostName(config.getHost());
            // 字符编码集的设置
            htmlEmail.setCharset(MailInfo.ENCODEING);
            // 收件人的邮箱
            List<String> receivers = body.getReceivers();
            for (String receiver : receivers) {
                htmlEmail.addTo(receiver);
            }
            htmlEmail.setSmtpPort(config.getPort());

            // 发送人的邮箱
            htmlEmail.setFrom(config.getSender(), config.getName());
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            htmlEmail.setAuthentication(config.getUsername(), config.getPassword());
            // 要发送的邮件主题
            htmlEmail.setSubject(body.getSubject());
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            // 发送
            String send = htmlEmail.send();
            logger.debug("email send return:"+send);
//            logger.debug(config.getSender() + " 发送邮件到 " + body.getReceiver());
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        } finally {
            body = null;
            htmlEmail = null;
        }
        return true;
    }


    private static boolean send (MailInfo mail, HtmlEmail email) throws EmailException {
        // 发送email
        // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
        email.setHostName(mail.getHost());
        // 字符编码集的设置
        email.setCharset(MailInfo.ENCODEING);
        // 收件人的邮箱
        email.addTo(mail.getReceiver());
        // 发送人的邮箱
        email.setFrom(mail.getSender(), mail.getName());
        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
        email.setAuthentication(mail.getUsername(), mail.getPassword());
        // 要发送的邮件主题
        email.setSubject(mail.getSubject());
        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
        // 发送
        String send = email.send();
        logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
        return true;

    }

    public static boolean sendText (MailInfo mail) {
        // 发送email
        HtmlEmail email = new HtmlEmail();
        try {
            email.setTextMsg(mail.getMessage());
            send(mail, email);
            logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
                    + " 失败");
            return false;
        }
    }

    public static void main (String[] args) {
        MailConfig mailConfig = new MailConfig("smtp.mxhichina.com", "cdgx_hb@znworld.net", "测试", "cdgx_hb@znworld.net", "Cdgx123456", 465,true);
        MailBody mailBody = new MailBody(mailConfig, "linj@harme.cn", "测试", "主题");
        mailBody.getMailConfig().setSSL(true);
        send(mailBody);
    }

    /*public static void main (String[] args) {
        MailInfo mail = new MailInfo();
        mail.setHost("smtp.exmail.qq.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
        mail.setSender("log@linjie.org");
        mail.setReceiver("344758158@qq.com"); // 接收人
        mail.setUsername("log@linjie.org"); // 登录账号,一般都是和邮箱名一样吧
        mail.setPassword("E!3nXNDB$2w8"); // 发件人邮箱的登录密码
        mail.setSubject("aaaaaaaaa");
        mail.setMessage("" +
                "<html>\n" +
                "  <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  </head>\n" +
                "  <body style=\"margin-bottom: 0px; margin-top: 0px; padding-bottom: 0px; padding-top: 0px;text-align:center;\">\n" +
                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"f9f9f9\">\n" +
                "<tbody>\n" +
                "\t<tr>\n" +
                "\t<td align=\"center\">\n" +
                "\t<table width=\"588\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"f9f9f9\">\n" +
                "      <tbody>\n" +
                "      \t<tr>\n" +
                "        \t<td>&nbsp;</td>\n" +
                "        </tr>\n" +
                "      \t<tr>\n" +
                "      \t<td align=\"center\">\n" +
                "      \t<table width=\"588\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tr>\n" +
                "          <td valign=\"top\" background=\"http://qili.iqr.cc//static/img/top.jpg\" >\n" +
                "            <table width=\"588\" height=\"446\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n" +
                "              <tbody>\n" +
                "              \t<tr>\n" +
                "              \t\t<td colspan=\"3\" height=\"45\">&nbsp;</td>\n" +
                "              \t</tr>\n" +
                "                <tr>\n" +
                "                  <td width=\"69\">&nbsp;</td>\n" +
                "                  <td width=\"450\" valign=\"top\">\n" +
                "                    <table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                    \t<tr>\n" +
                "                    \t\t<td style=\"font-size:23px;color:#fff;font-family: 微软雅黑;height: 46px;line-height: 46px;\">亲爱的林杰</td>\n" +
                "                    \t</tr>\n" +
                "                    \t<tr valign=\"top\">\n" +
                "                    \t\t<td style=\"font-size:23px;color:#fff;font-family: 微软雅黑;height: 90px;line-height: 27px;\" align=\"left\">\n" +
                "                    \t\t&nbsp;&nbsp;&nbsp;&nbsp;您的二维码名片已经免费升级到最新版！<span style=\"font-size: 23px;color: #e86b6d;\">李佳楠、陈风阳阳</span> 在使用了,扫一扫加入他们！</td>\n" +
                "                    \t</tr>\n" +
                "                    \t<tr>\n" +
                "                    \t\t<td align=\"center\"><img src=\"http://qili.iqr.cc//static/img/qcode.jpg\" width=\"145\" height=\"145\"></td>\n" +
                "                    \t</tr>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                  <td width=\"69\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                \t<td colspan=\"2\" style=\"height: 8px;line-height: 8px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        \t<td background=\"http://qili.iqr.cc//static/img/center1.jpg\">\n" +
                "        \t<table width=\"588\" height=\"621\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n" +
                "            \t<tr>\n" +
                "            \t\t<td height=\"150\" colspan=\"3\">&nbsp;</td>\n" +
                "            \t</tr>\n" +
                "            \t<tr>\n" +
                "            \t\t<td width=\"330\">&nbsp;</td>\n" +
                "            \t\t<td width=\"88\" valign=\"top\"><div style=\"font-size: 22px;font-family: 微软雅黑;text-align: center;\">林杰林杰</div></td>\n" +
                "            \t\t<td width=\"170\"></td>\n" +
                "            \t</tr>\n" +
                "            </table>\n" +
                "        \t</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        \t<td background=\"http://qili.iqr.cc//static/img/center2.jpg\">\n" +
                "        \t<table width=\"588\" height=\"745\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n" +
                "            \t<tr>\n" +
                "            \t\t<td width=\"309\" valign=\"top\"><div style=\"padding:256px 0 0 60px; width:249px; text-align: center;font-size: 19px;font-family: 微软雅黑;\">李嘉楠</div></td>\n" +
                "            \t\t<td width=\"229\" valign=\"top\"><div style=\"padding:263px 18px 0 0; width:211px; text-align: center;font-size: 19px;font-family: 微软雅黑;color: #fff;\">陈凤阳</div></td>\n" +
                "            \t\t<td width=\"50\">&nbsp;</td>\n" +
                "            \t</tr>\n" +
                "            \t<tr>\n" +
                "            \t\t<td width=\"309\" valign=\"top\"><div style=\"padding:370px 0 0 60px; width:249px; text-align: center;font-size: 23px;font-family: 微软雅黑;\">李嘉楠的名片</div></td>\n" +
                "            \t\t<td width=\"229\" valign=\"top\"><div style=\"padding:340px 0 0 0; width:229px; text-align: center;font-size: 23px;font-family: 微软雅黑;\">陈凤阳的名片</div></td>\n" +
                "            \t\t<td width=\"50\">&nbsp;</td>\n" +
                "            \t</tr>\n" +
                "            </table>\n" +
                "        \t</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        \t<td background=\"http://qili.iqr.cc//static/img/bottom.jpg\">\n" +
                "        \t<table width=\"588\" height=\"220\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n" +
                "            \t<tr>\n" +
                "            \t\t<td></td>\n" +
                "            \t</tr>\n" +
                "            </table>\n" +
                "        \t</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        \t<td>&nbsp;</td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "\t</td>\n" +
                "\t</tr>\n" +
                "</table>\t\n" +
                "  </body>\n" +
                "</html>\n" +
                "\n");
        new MailUtil().sendHtml(mail);
    }*/
}
