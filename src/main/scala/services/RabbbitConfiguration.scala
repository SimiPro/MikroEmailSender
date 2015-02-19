package com.belongo.services.email

import com.belongo.services.email.listeners.{UserRegistration}
import com.belongo.services.email.backend.ScalaObjectMapper
import org.springframework.amqp.core.{MessageListener, AcknowledgeMode, AmqpAdmin}
import org.springframework.amqp.rabbit.connection.{CachingConnectionFactory, ConnectionFactory}
import org.springframework.amqp.rabbit.core.{RabbitAdmin, RabbitTemplate}
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.{MessageConverter, Jackson2JsonMessageConverter}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Primary, Bean, Configuration, ComponentScan}



@Configuration
@EnableAutoConfiguration
@ComponentScan(Array(
  "com.belongo.services.email",
  "com.belongo.services.email.mail",
  "com.belongo.services.email.listeners"))
class RabbbitConfiguration  {

  @Autowired
  var mailConf: MailConfiguration = _

  @Autowired
  var userRegistration:UserRegistration = _

  @Bean
  def connectionFactory():ConnectionFactory = new CachingConnectionFactory("localhost",5672 )
/*
  @Bean
  def connectionFactory():ConnectionFactory = {
    val cachingConnectionFactory = new CachingConnectionFactory("localhost")
    cachingConnectionFactory.setUsername("guest")
    cachingConnectionFactory.setPassword("guest")
    return connectionFactory
  }
*/

  @Bean
  def admin():AmqpAdmin =  new RabbitAdmin(connectionFactory())


  @Bean
  def rabbitTemplate():RabbitTemplate= {
    val template = new RabbitTemplate(connectionFactory())
    template.setRoutingKey(UserRegistration.routingKey)
    template.setQueue(UserRegistration.queueName)

    template
  }

  @Bean
  def listener():SimpleMessageListenerContainer = {
    val container = new SimpleMessageListenerContainer(connectionFactory());
    container.setQueueNames(UserRegistration.queueName);
    container.setMessageListener(messageListenerAdapter());
    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
    container.start()
    return container;
  }

  @Bean
  def messageListenerAdapter():MessageListenerAdapter = {
    new MessageListenerAdapter(userRegistration, messageConverter())
  }



  @Bean
  def messageConverter(): MessageConverter = {
    val jsonConverter = new Jackson2JsonMessageConverter
    jsonConverter.setJsonObjectMapper(scalaObjectMapper())
    jsonConverter
  }

  @Bean
  @Primary
  def scalaObjectMapper() = new ScalaObjectMapper()



}