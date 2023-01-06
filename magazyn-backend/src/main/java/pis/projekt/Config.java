package pis.projekt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.services.EmployeeService;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "pis.projekt.repository"
        }
)
public class Config {
    @Bean
    public IEmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
