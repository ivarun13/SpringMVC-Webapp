package csjobs.security;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import csjobs.model.User;

public class SecurityUtils {

    private static AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    /**
     * <security:http> adds an AnonymousAuthenticationFilter which creates an
     * Authentication token for anonymous users. The problem is that
     * Authentication.isAuthenticated() will then return true even for anonymous
     * users, so we have to use a AuthenticationTrustResolver to check for
     * anonymous/authenticated.
     */
    public static boolean isAnonymous()
    {
        return authenticationTrustResolver.isAnonymous(
            SecurityContextHolder.getContext().getAuthentication() );
    }

    public static boolean isAuthenticated()
    {
        return !isAnonymous();
    }

    public static User getUser()
    {
        return isAuthenticated() ? (User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal() : null;
    }

}
