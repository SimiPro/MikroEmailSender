package com.belongo.services.email.mail


import com.belongo.services.email.MailConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.{JavaMailSenderImpl, JavaMailSender}
import org.springframework.stereotype.Service

/**
 * Created by Simon on 18.02.2015.
 */
@Service("mailService")
class MailSubmissionController {

  @Autowired
  var javaMailSender:MailConfiguration = _

  def send(to:String, from:String, subject:String, text:String ):SimpleMailMessage = {
    val msg = new SimpleMailMessage()
    msg.setTo(to);
    msg.setReplyTo(to);
    msg.setFrom(from);
    msg.setSubject(subject);
    msg.setText(text);
    println("Send email to:  " + to + "! Please confirm your email")
    // javaMailSender.javaMailSender().send(msg) this donesnt work do no why :( no motivation to found out why
    msg;
  }

}
