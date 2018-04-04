/**
 * Copyright (C) 2018 Demitrius Belai
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package br.unesp.fc.central;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
            AuthenticationProvider authenticationProvider) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    public class Unauthorized401AuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public final void commence(HttpServletRequest request, HttpServletResponse response,
                                   AuthenticationException authException) throws IOException {
            switch (request.getRequestURI()) {
                case "/user":
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    break;
                case "/oauth/authorize":
                case "/sistemas":
                    response.sendRedirect("/");
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
             http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/*.map").permitAll()
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(new Unauthorized401AuthenticationEntryPoint())
                .and()
            // TODO: put CSRF protection back into this endpoint
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
            .formLogin()
                .defaultSuccessUrl("/sistemas")
                .loginProcessingUrl("/login")
                .failureUrl("/authentication_error=true")
                .loginPage("/");
        // @formatter:on
    }

}
