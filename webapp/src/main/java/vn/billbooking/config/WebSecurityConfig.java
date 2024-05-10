package vn.billbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import vn.billbooking.handler.LoginFailureHandler;
import vn.billbooking.handler.LoginSuccessHandler;
import vn.billbooking.service.CustomUserDetailsService;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http.csrf().disable().cors().configurationSource(request -> corsConfiguration);

        http.requiresChannel().antMatchers("/**").requiresSecure();

        http.authorizeRequests()
                .antMatchers("/", "/trang-chu.html", "/dang-ky.html", "/dang-nhap.html", "/dang-xuat.html",
                        "/chi-tiet.html", "/thanh-toan.html", "/khuyen-mai.html", "/ma-giam-gia.html",
                        "/cua-hang.html", "/don-hang.html", "/tim-kiem.html", "/vi-tri.html",
                        "/ca-si.html", "/ca-si/chi-tiet.html",
                        "/quan-ly/dang-nhap", "/chuyen-huong/**","/quan-ly/quen-mat-khau/**").permitAll()
                .antMatchers("/images/**", "/photos/**", "/resources/**").permitAll()

                // USER
                .antMatchers("/tai-khoan/**").hasAnyAuthority("USER", "OWNER", "ADMIN")

                // OWNER
                .antMatchers("/quan-ly/dat-phong/**").hasAnyAuthority("OWNER")

                // ADMIN
                .antMatchers("/quan-ly/tai-khoan/**").hasAnyAuthority("ACCOUNT_MANAGER")
                .antMatchers("/quan-ly/khu-vuc/**").hasAnyAuthority("LOCATION_MANAGER")
                .antMatchers("/quan-ly/quan-karaoke/**").hasAnyAuthority("KARAOKE_MANAGER")
                .antMatchers("/quan-ly/dat-phong/**").hasAnyAuthority("BOOKING_MANAGER")
                .antMatchers("/quan-ly/giam-gia/**").hasAnyAuthority("VOUCHER_MANAGER")
                .antMatchers("/quan-ly/bai-viet/**").hasAnyAuthority("POST_MANAGER")
                .antMatchers("/quan-ly/binh-luan/**").hasAnyAuthority("COMMENT_MANAGER")
                .antMatchers("/quan-ly/thu-vien/**").hasAnyAuthority("GALLERY_MANAGER")
                .antMatchers("/quan-ly/ca-si/**").hasAnyAuthority("SINGER_MANAGER")

                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error/403");
    }

}
