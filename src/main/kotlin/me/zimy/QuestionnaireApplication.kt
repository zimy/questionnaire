package me.zimy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication
open class QuestionnaireApplication

fun main(args: Array<String>) {
    SpringApplication.run(QuestionnaireApplication::class.java, *args)
}
