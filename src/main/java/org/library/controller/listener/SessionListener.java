package org.library.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");

        String userEmail = (String) httpSessionEvent.getSession()
                .getAttribute("userEmail");
        loggedUsers.remove(userEmail);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
