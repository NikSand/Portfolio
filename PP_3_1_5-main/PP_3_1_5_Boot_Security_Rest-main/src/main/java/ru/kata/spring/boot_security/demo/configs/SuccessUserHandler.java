package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Перенаправление на стартовые страницы после успешной авторизации в зависимости от ролей у пользователя.
 */
@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    /**
     * Перенаправить пользователя на стартовую страницу в зависимости от роли пользователя.
     * @param httpServletRequest Параметры запроса на сервер.
     * @param httpServletResponse Параметры ответа от сервера.
     * @param authentication Параметры аутентификации.
     * @throws IOException Ошибка сервера или аутентификации.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admins");
        }
        else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/");
        }
        else {
            httpServletResponse.sendRedirect("/");
        }
    }
}
