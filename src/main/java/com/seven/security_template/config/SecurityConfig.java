package com.seven.security_template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// SecurityFilter 단 설정
@Configuration
public class SecurityConfig {

    // 비밀번호 암호화용 Bean - AuthenticationProvider에서 디비에서 가져온 User객체의 pw와 입력한 값을 비교할때 입력한 값의 encoding때도 사용됨
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 필터 구획을 내 마음대로 커스텀
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 수많은 필터 중 CSRF 필터를 disable 시킴
        http
                .csrf(csrf -> csrf.disable());

        // 로그인 필터 설정
        http
                .formLogin(login -> login
                        .loginPage("/login") // login page GET uri
                        .loginProcessingUrl("/login")); // login POST 요청 -> Authentication Manager -> Authentication Provider

        // 인가 필터 설정
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/join").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/").permitAll() // 메소드 별 권한 나누기
                        .anyRequest().hasRole("ADMIN")
                );

        // 최종 빌드
        return http.build();
    }

}
