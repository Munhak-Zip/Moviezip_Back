package com.example.moviezip.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // 암호화
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults()) // CORS 설정 활성화
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/", "/loginProc", "/join", "/joinProc","/session-expired","/findUserId", "/checkExistsId", "/changePassword").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/loginProc")
                                .usernameParameter("username") // 폼 필드 이름과 일치시킵니다.
                                .successHandler(successHandler())
                                .failureHandler(failureHandler())
                                .permitAll()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .invalidSessionUrl("/session-expired")
                                .maximumSessions(1)
                                .expiredUrl("/session-expired")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")   // 로그아웃 처리 URL (= form action url)
                                //.logoutSuccessUrl("/login") // 로그아웃 성공 후 targetUrl,
                                // logoutSuccessHandler 가 있다면 효과 없으므로 주석처리.
                                .addLogoutHandler((request, response, authentication) -> {
                                    // 사실 굳이 내가 세션 무효화하지 않아도 됨.
                                    // LogoutFilter가 내부적으로 해줌.
                                    HttpSession session = request.getSession();
                                    if (session != null) {
                                        session.invalidate();
                                    }
                                })  // 로그아웃 핸들러 추가
                                .logoutSuccessHandler((request, response, authentication) -> {
                                }) // 로그아웃 성공 핸들러
                                .deleteCookies("remember-me") // 로그아웃 후 삭제할 쿠키 지정
                )
                .csrf(csrf ->
                        csrf.disable()
                );
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
