package pis.projekt.security;

import org.springframework.security.core.context.SecurityContextHolder;
import pis.projekt.models.Employee;

public class SecurityHelper {
    public static Employee getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getClass() != Employee.class)
            return null;
        return (Employee) principal;
    }
}
