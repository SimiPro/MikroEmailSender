package com.belongo.services.email.mail

import com.belongo.services.email.MailConfiguration
import org.junit.{Test, After, Before}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.subethamail.wiser.Wiser


/**
 * Created by Simon on 18.02.2015.
 */
@SpringApplicationConfiguration(classes = Array(classOf[MailConfiguration]))
@RunWith(classOf[SpringJUnit4ClassRunner])
@WebAppConfiguration
class MailSubmissionControllerTest {

  var wiser:Wiser = _

  @Test
  def send():Unit={

  }

  @Before
  def setUp():Unit =  {
    wiser = new Wiser;
    wiser.start()
  }

  @After
  def tearDown():Unit={
    wiser.stop()
  }



}

object Application  {
  def main(args: Array[String]) : Unit = SpringApplication.run(classOf[MailConfiguration], args :_ *)
}
