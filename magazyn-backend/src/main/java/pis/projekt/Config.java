package pis.projekt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.security.JwtTokenFilter;
import pis.projekt.security.Roles;
import pis.projekt.services.EmployeeService;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "pis.projekt.repository"
        }
)

@EnableJpaAuditing
@EnableWebSecurity
public class Config {

    public final static String CORS_URL = "http://localhost:5173/";
    @Bean
    public IEmployeeService getEmployeeService() {
        return new EmployeeService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .cors().and()
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/employees/login", "/employees/login/",
                                                "/employees/register", "/employees/register/",
                                                "/magazines/report/**"
                    ).permitAll();
                    authorize.requestMatchers(
                            HttpMethod.POST,
                            "/employees/login", "/employees/login/",
                                    "/employees/register", "/employees/register/"
                    ).permitAll();

                    authorize.requestMatchers(
                            "/employees/all/**",
                            "/issues/**",
                            "/issuehistories/**",
                            "/magazines/**",
                            "/products/**",
                            "/reports/**",
                            "/sections/**"
                    ).hasAuthority(Roles.USER.toString());
                    authorize.requestMatchers(
                            HttpMethod.POST,
                            "/issues/**",
                            "/issuehistories/**",
                            "/magazines/**",
                            "/products/**",
                            "/reports/**",
                            "/sections/**"
                    ).hasAuthority(Roles.USER.toString());
                    authorize.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtTokenFilter(getEmployeeService()), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
