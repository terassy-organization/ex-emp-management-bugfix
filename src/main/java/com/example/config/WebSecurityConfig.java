package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * パスワードをハッシュ化するためのコンフィグ.
 *
 * @author YusakuTerashima
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * ログインのためのフィルタ処理をする.
     *
     * @param http HttpSecurityクラスのオブジェクト
     * @return 生成したオブジェクト
     * @throws Exception 例外処理
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    /**
     * パスワードをハッシュ化する.
     *
     * @return ハッシュ化したパスワード
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
