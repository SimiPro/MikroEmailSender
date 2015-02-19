package com.belongo.services.email.main

import com.belongo.services.email.RabbbitConfiguration
import org.springframework.boot.SpringApplication

object EmailServiceMain {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[RabbbitConfiguration])
  }
}