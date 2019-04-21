package cn.hselfweb.cnjpbbs.configuration;

import cn.hselfweb.cnjpbbs.fliter.OptionsRequestFilter;
import cn.hselfweb.cnjpbbs.utils.TokenDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    static String issuerUri;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/echo").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/html/**").permitAll()
                .antMatchers("/api/**").hasAuthority("SCOPE_user")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().disable()
                .cors()
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
               // new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", "Authorization"))))
                .and()
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                .oauth2ResourceServer()
                .jwt()
        ;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        TokenDecoder jwtDecoder = new TokenDecoder();
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(new AudienceValidator());
        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder;
    }
}
