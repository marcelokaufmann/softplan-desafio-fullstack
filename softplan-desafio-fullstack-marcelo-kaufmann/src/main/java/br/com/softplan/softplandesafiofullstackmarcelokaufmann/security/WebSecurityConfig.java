package br.com.softplan.softplandesafiofullstackmarcelokaufmann.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/iniciarusuarios/**").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/**").permitAll() // comentar linha para autenticacao
                .antMatchers(HttpMethod.GET, "/processos/**").permitAll() // comentar linha para autenticacao
                //.antMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.DELETE, "/usuarios").hasRole("ADMIN") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.GET, "/processos").hasRole("TRIADOR") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.POST, "/processos").hasRole("TRIADOR") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.GET, "/processos").hasRole("FINALIZADOR") // descomentar linha para autenticacao
                //.antMatchers(HttpMethod.PUT, "/processos").hasRole("FINALIZADOR") // descomentar linha para autenticacao
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
