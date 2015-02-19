package com.belongo.services.email

import java.util.Properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{ Bean, PropertySource, Configuration}
import org.springframework.mail.javamail.{JavaMailSenderImpl, JavaMailSender}


/**
 * Created by Simon on 18.02.2015.
 */
@Configuration
@PropertySource(Array("classpath:mail.properties"))
class MailConfiguration {

  @Value("${mail.protocol}")
  var protocol:String = _
  @Value("${mail.host}")
  var host:String = _
  @Value("${mail.smtp.auth}")
  var auth:String = _
  @Value("${mail.port}")
  var port:String = _
  @Value("${mail.smtp.starttls-enable}")
  var starttls:String = _
  @Value("${mail.from}")
  var from:String = _
  @Value("${mail.username}")
  var username:String = _
  @Value("${mail.password}")
  var password:String = _

  @Bean
  def javaMailSender():JavaMailSender = {
    val mailSender = new JavaMailSenderImpl()
    val mailProperties = new Properties()
    mailProperties.put("mail.debug", Boolean.box(true))
    mailProperties.put("mail.smtp.auth", auth )
    mailProperties.put("mail.smtp.starttls.enable", starttls)
    mailSender.setJavaMailProperties(mailProperties)
    mailSender.setHost(host)
    mailSender.setPort(port.toInt)
    mailSender.setProtocol(protocol)
    mailSender.setUsername(username)
    mailSender.setPassword(password)
    mailSender
  }
}

