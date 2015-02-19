package com.belongo.services.email.listeners


import com.belongo.services.email.mail.MailSubmissionController
import com.belongo.services.registration.controllers.NewRegistrationNotification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * Created by Simon on 18.02.2015.
 */

@Component
class UserRegistration {

  @Autowired
  var mailSubmissionController:MailSubmissionController = _

  def handleMessage(message: NewRegistrationNotification): Unit = {
    println("New user registered: " + message.emailAddress)
    mailSubmissionController.send(message.emailAddress, "no-replay@belongo.com", "Thanks! Confirm your Email", confirmYourEmailText)
  }

  def confirmYourEmailText(): String = {
    "not implemented yet - do it? "
  }
}

object UserRegistration {
  val queueName = "user-registration"
  val routingKey = queueName
  val exchangeName = "user-registrations"
}