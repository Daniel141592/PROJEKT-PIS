package pis.projekt.security;

import org.springframework.security.core.context.SecurityContextHolder;
import pis.projekt.models.Employee;

import java.util.Optional;

public class SecurityHelper {
    public static Employee getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal.getClass() == Optional.of(Employee.class).getClass()))
            return null;

        var user = (Optional<?>) principal;

        return (Employee) user.orElse(null);
    }
}
