package com.backend.blog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    // authenticationManager 를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()       //CorsFilter 라는 필터가 존재하는데 이를 활성화 시키는 작업
            .csrf().disable()   //세션을 사용하지 않고 JWT 토큰을 활용하여 진행하고 REST API 를 만드는 작업이기때문에 csrf 사용은 disable 처리
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //현재 스프링 시큐리티에서 세션을 관리하지 않겠다는 뜻입니다. 서버에서 관리되는 세션 없이 클라이언트에서 요청하는 헤더에 token을 담아보낸다면 서버에서 토큰을 확인하여 인증하는 방식을 사용할 것이므로 서버에서 관리되어야할 세션이 필요없어지게 됩니다.
            .and()
            .authorizeRequests() //이제부터 인증절차에 대한 설정을 진행하겠다는 것입니다.
                .antMatchers("/user/login").permitAll() //permitAll = 스프링 시큐리티에서 인증되지 않더라고 통과시켜 누구에게나 사용을 열어줌
                //.antMatchers("/users").hasAnyRole("ADMIN")
                .antMatchers("/**").authenticated()
                .anyRequest().permitAll()
                //.anyRequest().hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            .and();
        //.accessDeniedHandler(new CustomAccessDeniedHandler()) // 로그인 후 권한 없을 경우? 테스트 해봐야함*/
    }
}
