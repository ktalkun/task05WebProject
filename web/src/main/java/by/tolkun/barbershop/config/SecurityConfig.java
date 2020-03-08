package by.tolkun.barbershop.config;

import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.url.AllowPageURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(
        prePostEnabled = true,  // @PreAuthorize, @PostAuthorize
        securedEnabled = true,  // @Secured
        jsr250Enabled = true    // @RoleAllowed
)
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
                .antMatchers(AllowPageURL.LOGIN, AllowPageURL.SIGNIN).anonymous()
                .antMatchers(AllowPageURL.BOOK).hasAuthority(
                Role.ROLE_CUSTOMER.toString())
                .antMatchers("/profile/**").authenticated()
                .anyRequest().permitAll();

        security
                .addFilterBefore(menuInitFilter, BasicAuthenticationFilter.class);

        security
                .formLogin()
                .loginPage(AllowPageURL.LOGIN)
                .loginProcessingUrl(AllowPageURL.LOGIN)
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl(AllowPageURL.ROOT)
                .failureHandler((request, response, e) -> {
                    request.setAttribute("message",
                            "User with such login or password isn't existed.");
                    request.setAttribute("redirectUrl", AllowPageURL.LOGIN);
                    request
                            .getRequestDispatcher(AllowPageURL.MESSAGE)
                            .forward(request, response);
                });

        security
                .logout()
                .logoutUrl(AllowPageURL.LOGOUT)
                .logoutSuccessUrl(AllowPageURL.ROOT)
                .invalidateHttpSession(true);

        security
                .exceptionHandling()
                .accessDeniedHandler((request, response, e) -> {
                    request.setAttribute("message",
                            "Access to the resource denied.");
                    request.setAttribute("redirectUrl", AllowPageURL.ROOT);
                    request
                            .getRequestDispatcher(AllowPageURL.MESSAGE)
                            .forward(request, response);
                });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
