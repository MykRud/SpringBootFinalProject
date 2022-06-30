package com.spring_final.SpringFinalProject.security;

import com.spring_final.SpringFinalProject.filter.CustomAuthenticationFilter;
import com.spring_final.SpringFinalProject.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService service;

    @Bean
    public AuthenticationProvider getProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                csrf().disable()
                .authorizeRequests().antMatchers("/login/**", "/home/**", "/registration/**", "/logout/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/activityRequestAdd/**", "/activityRequestComplete/**",
                        "/activities/**", "/getActivityById/**", "/markTime/**", "/profile/**", "/userProfileUpdate/**")
                .hasAnyAuthority("USER", "ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin/activitiesAdd/**", "/admin/typesAdd/**",
                        "/admin/activityRequestApprove/**", "/admin/activityDelete/**", "/admin/userDelete/**", "/admin/activitiesRequests/**",
                        "/admin/users/**", "/admin/activityRequestReject/**", "/admin/userUpdate/**", "/admin/setAdmin/**",
                        "/admin/deleteAdmin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()



                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home").permitAll();
    }

    /*private final UserDetailsService userDetailsService; //bean
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //bean
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/login", "/home", "/registration", "/logout").permitAll();// - to allow some pages
        http.authorizeRequests().antMatchers("/activityRequestAdd/**", "/activityRequestComplete/**",
                "/activities", "/getActivityById/**", "/markTime/**", "/profile/**", "/userProfileUpdate/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/activitiesAdd/**", "/typesAdd/**",
                "/activityRequestApprove/**", "/activityDelete/**", "/userDelete/**", "/activitiesRequests",
                "/users", "/activityRequestReject/**", "/userUpdate/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);// we need to intercept any requests before any other filters
        http.formLogin().loginPage("/login").permitAll();
        http.logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }*/

}
