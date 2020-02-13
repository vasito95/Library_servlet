package org.brs.library;

import org.brs.library.command.*;
import org.brs.library.model.dao.BookDao;
import org.brs.library.model.dao.DaoFactory;
import org.brs.library.model.dao.OrderDao;
import org.brs.library.model.dao.UserDao;
import org.brs.library.service.BookService;
import org.brs.library.service.OrderService;
import org.brs.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    public static final String LOGIN_PAGE = "login";
    public static final String REGISTRATION = "registration";
    public static final String ADMIN_PAGE = "admin";
    public static final String ADMIN_LIBRARY_PAGE = "admin/library";
    public static final String ADMIN_ADD_BOOK_PAGE = "admin/add-book";
    public static final String ADMIN_ORDERS_PAGE = "admin/orders";
    public static final String ADMIN_ORDERS_ACCEPT_PATH = "admin/orders/accept";
    public static final String ADMIN_ORDERS_DECLINE_PATH = "admin/orders/decline";
    public static final String ADMIN_EDIT_BOOKS_PAGE = "admin/edit-books";
    public static final String ADMIN_EDIT_BOOK_DELETE_PATH = "admin/edit-book/delete";
    public static final String ADMIN_EDIT_BOOK_ACCEPT_PATH = "admin/edit-book/accept";
    public static final String ADMIN_EDIT_BOOK_PAGE = "admin/edit-book";
    public static final String ADMIN_LOGOUT_PATH = "admin/logout";
    public static final String USER_PAGE = "user";
    public static final String USER_ALL_BOOKS_PAGE = "user/all-books";
    public static final String USER_MY_BOOKS = "user/my-books";
    public static final String USER_ORDER_BOOK_PAGE = "user/order-book";
    public static final String USER_LOGOUT_PATH = "user/logout";
    public static final String LOGOUT_PATH = "/logout";
    public static final String ERROR_PAGE = "error";

    private Map<String, Command> commands = new HashMap<>();
    private UserService userService;
    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init() throws ServletException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        BookDao bookDao = factory.createBookDao();
        OrderDao orderDao = factory.createOrderDao();
        orderService = new OrderService(orderDao);
        userService = new UserService(userDao);
        bookService = new BookService(bookDao);

        commands.put(LOGIN_PAGE, new LoginCommand(userService));
        commands.put(REGISTRATION, new RegistrationCommand(userService));
        commands.put(ADMIN_PAGE, new AdminCommand());
        commands.put(ADMIN_LIBRARY_PAGE, new LibraryCommand(bookService));
        commands.put(ADMIN_ADD_BOOK_PAGE, new AddBookCommand(bookService));
        commands.put(ADMIN_ORDERS_PAGE, new OrdersCommand(orderService, bookService));
        commands.put(ADMIN_ORDERS_ACCEPT_PATH, new OrdersCommand(orderService, bookService));
        commands.put(ADMIN_ORDERS_DECLINE_PATH, new OrdersCommand(orderService, bookService));
        commands.put(ADMIN_EDIT_BOOKS_PAGE, new EditBooksCommand(bookService));
        commands.put(ADMIN_EDIT_BOOK_DELETE_PATH, new EditBookCommand(bookService));
        commands.put(ADMIN_EDIT_BOOK_ACCEPT_PATH, new EditBookCommand(bookService));
        commands.put(ADMIN_EDIT_BOOK_PAGE, new EditBookCommand(bookService));
        commands.put(ADMIN_LOGOUT_PATH, new LogoutCommand());
        commands.put(USER_PAGE, new UserCommand());
        commands.put(USER_ALL_BOOKS_PAGE, new AllBooksCommand(bookService));
        commands.put(USER_MY_BOOKS, new MyBooksCommand(bookService));
        commands.put(USER_ORDER_BOOK_PAGE, new OrderBooksCommand(orderService, bookService));
        commands.put(USER_LOGOUT_PATH, new LogoutCommand());
        commands.put(LOGOUT_PATH, new LogoutCommand());
        commands.put(ERROR_PAGE, new ErrorCommand());
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path, (p) -> "redirect:/error");
        String page = command.execute(request);

        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", "/app"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
