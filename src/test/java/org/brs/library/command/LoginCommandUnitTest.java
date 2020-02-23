package org.brs.library.command;

import org.brs.library.exception.UserNotFoundException;
import org.brs.library.model.dao.UserDao;
import org.brs.library.model.dao.impl.JDBCUserDao;
import org.brs.library.model.entity.User;
import org.brs.library.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginCommandUnitTest {

    private static final String USER_EMAIL = "brusak.vas@gmail.com";
    private static final String USER_PASSWORD = "PasswordPassword";

    @InjectMocks
    private LoginCommand loginCommand;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    private UserDao userDao = new JDBCUserDao();
    private UserService userService = new UserService(userDao);
    private UserService userServiceSpy = Mockito.spy(userService);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
    public void whenUserExistReturnRedirect() {
        when(userServiceSpy.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(new User.UserBuilder()
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .build());
        String path = loginCommand.execute(request);
        verify(session).setAttribute(any(),any());
        assertEquals("redirect:/user", path);
    }

    @Ignore
    @Test(expected = UserNotFoundException.class)
    public void whenUserNotFoundReturnLogin() throws UserNotFoundException {
        doThrow(UserNotFoundException.class).when(userServiceSpy.findUserByEmailAndPassword(anyString(),anyString()));
        when(request.getParameter("email")).thenReturn(USER_EMAIL);
        when(request.getParameter("password")).thenReturn(USER_PASSWORD);
        String path = loginCommand.execute(request);
        assertEquals("/login.jsp", path);
    }
}