package se.greyzone.myhome.web.action.context;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBeanContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import se.greyzone.myhome.web.security.AuthenticatedUser;

public class MyHomeContext extends ActionBeanContext {

    public MyHomeContext() {
        super();
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        super.setRequest(request);
    }

    public String getRequestIp() {
        return getRequest().getRemoteAddr();
    }

    public String getUserId() {
        return getAuthenticatedUser().getUserId();
    }

    public AuthenticatedUser getAuthenticatedUser() {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        try {
            return (AuthenticatedUser) token.getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAuthenticatedUser() {
        return getAuthenticatedUser() != null;
    }

    public String getHostAndContextPath() {
        String contextPath = getRequest().getContextPath();
        String url = getRequest().getRequestURL().toString();

        return url.substring(0, url.indexOf(contextPath) + contextPath.length());
    }
}
