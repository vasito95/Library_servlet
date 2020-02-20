package org.brs.library.command;

import org.brs.library.model.entity.User;
import org.brs.library.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandUnitTest {

    private static final String USER_EMAIL = "brusak.vas@gmail.com";
    private static final String USER_PASSWORD = "PasswordPassword";

    @InjectMocks
    private LoginCommand loginCommand;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private User user;

    @Before
    public void setUp() {
        /*when(request.getParameter("login")).thenReturn(USER_EMAIL);
        when(request.getParameter("password")).thenReturn(USER_PASSWORD);*/
        when(request.getSession()).thenReturn(session);
        //when(userService.findUserByEmailAndPassword(USER_EMAIL, USER_PASSWORD)).thenReturn(Optional.of(user));
    }

    @Test
    public void whenFormEmptyReturnLogin() {
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);
        String path = loginCommand.execute(request);
        assertEquals("/login.jsp", path);
    }

    @Test
    @Ignore
    public void whenUserNotFoundReturnLogin() {
        when(request.getParameter("email")).thenReturn(USER_EMAIL);
        when(request.getParameter("password")).thenReturn(USER_EMAIL);
        String path = loginCommand.execute(request);
        verify(request, never()).getSession();
        verify(userService, times(1)).findUserByEmailAndPassword(USER_EMAIL,USER_PASSWORD);
        assertEquals("/login.jsp", path);
    }
}
