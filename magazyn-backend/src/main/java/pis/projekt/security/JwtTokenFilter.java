package pis.projekt.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;
import pis.projekt.services.EmployeeService;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    IEmployeeService employeeService;

    public JwtTokenFilter(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (request.getRequestURI().startsWith("/employees/login") ||
            request.getRequestURI().startsWith("/employees/register")) {
            doFilter(request, response, filterChain);
            return;
        }

        if (token == null || token.isBlank()) {
            return401(response);
            return;
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(Roles.USER.toString()));

        String[] chunks = token.split("\\.");
        if (chunks.length != 3) {
            return401(response);
            return;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(EmployeeService.SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, secretKeySpec);

        if (!validator.isValid(chunks[0] + '.' + chunks[1], chunks[2])) {
            return401(response);
            return;
        }

        String login, password;
        boolean isManager;

        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            JSONObject json = new JSONObject(payload);

            login = json.getString("login");
            password = json.getString("password");
            isManager = json.getBoolean("manager");

            if (login.isBlank() || password.isBlank()) {
                return401(response);
                return;
            }
        } catch (JSONException e) {
            return401(response);
            return;
        }

        Employee employee = employeeService.findEmployeeByLogin(login);
        if (employee == null) {
            return401(response);
            return;
        }

        if (!password.equals(employee.getPasswordHash())) {
            return401(response);
            return;
        }

        if (isManager)
            grantedAuthorityList.add(new SimpleGrantedAuthority(Roles.MANAGER.toString()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                employee, token, grantedAuthorityList);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private void return401(HttpServletResponse response) {
        response.setStatus(401);
    }
}