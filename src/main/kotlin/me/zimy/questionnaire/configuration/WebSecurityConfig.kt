//package me.zimy.questionnaire.configuration
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//
//@Configuration
//@EnableWebSecurity
//open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests().antMatchers("/services/responders", "/services/questions").access("hasRole('ROLE_ADMIN')").and().formLogin().and().csrf()
//    }
//
//    @Autowired
//    @Throws(Exception::class)
//    fun globalConfig(auth: AuthenticationManagerBuilder) {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN")
//        auth.inMemoryAuthentication().withUser("owner").password("owner").roles("OWNER")
//    }
//}
