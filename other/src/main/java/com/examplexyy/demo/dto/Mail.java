package com.examplexyy.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @Author: Xiongyy
 * @Date: 2020/4/4 12:18
 * .............................................
 * 佛祖保佑             永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Mail extends MimeMessage {
    //模板地址
    private String templateName;
    //邮件标题
    private String subject;
    //收件人
    private List<String > sendTo;
    //抄送人
    private List<String> sendCc;


    public Mail(Session session) {
        super(session);
    }
}
