package edu.hawaii.its.holiday.security;

import edu.hawaii.its.holiday.access.UhAttributes;
import edu.hawaii.its.holiday.access.User;

public interface UserContextService {
    public abstract User getCurrentUser();

    public abstract String getCurrentUsername();

    public abstract Long getCurrentUhuuid();

    public void setCurrentUhuuid(Long uhuuid);

    public String getAttribute(String name);

    public UhAttributes getAttributes();
}