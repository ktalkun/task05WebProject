package by.tolkun.barbershop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
@EnableWebSecurity
@ComponentScan("by.tolkun.barbershop")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenericFilterBean menuInitFilter;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder builder)
            throws Exception {
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login.html", "/signin.html").anonymous()
                .antMatchers("/book.html").hasAuthority("CUSTOMER")
                .antMatchers("/profile/**").authenticated()
                .anyRequest().permitAll();

        security
                .addFilterBefore(menuInitFilter, BasicAuthenticationFilter.class);

        security
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/index.html");

        security
                .logout()
                .logoutUrl("/logout.html")
                .logoutSuccessUrl("/index.html")
                .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
