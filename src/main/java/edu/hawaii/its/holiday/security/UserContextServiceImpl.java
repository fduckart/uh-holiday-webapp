package edu.hawaii.its.holiday.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.hawaii.its.holiday.access.AnonymousUser;
import edu.hawaii.its.holiday.access.Role;
import edu.hawaii.its.holiday.access.UhAttributes;
import edu.hawaii.its.holiday.access.User;

@Service("userContextService")
public class UserContextServiceImpl implements UserContextService {

    @Override
    public User getCurrentUser() {
        User user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
        } else {
            user = new AnonymousUser();
        }
        return user;
    }

    @Override
    public String getCurrentUsername() {
        User user = getCurrentUser();
        return user != null ? user.getUsername() : "";
    }

    @Override
    public Long getCurrentUhuuid() {
        User user = getCurrentUser();
        return user != null ? user.getUhuuid() : Long.valueOf(0);
    }

    public void setCurrentUhuuid(Long uhuuid) {
        User user = getCurrentUser();
        if (user != null) {
            if (user.hasRole(Role.ADMIN)) {
                user.setUhuuid(uhuuid);
            }
        }
    }

    @Override
    public String getAttribute(String name) {
        return getCurrentUser().getAttribute(name);
    }

    @Override
    public UhAttributes getAttributes() {
        return getCurrentUser().getAttributes();
    }

    public String toString() {
        return "UserContextServiceImpl [context=" + SecurityContextHolder.getContext() + "]";
    }
}
