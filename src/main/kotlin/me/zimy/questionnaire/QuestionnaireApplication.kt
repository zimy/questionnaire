package me.zimy.questionnaire

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

//@Configuration
//@EnableWebSecurity
//open class Security(disableDefaults: Boolean) : WebSecurityConfigurerAdapter(disableDefaults) {
//    override fun configure(http: HttpSecurity) {
//        http
//                .authorizeRequests()
//                .antMatchers("/*").permitAll()
//                .antMatchers("/api/*").hasRole("USER")
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin();
//    }
//}
