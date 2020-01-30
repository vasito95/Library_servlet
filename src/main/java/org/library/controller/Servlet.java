package org.library.controller;

import org.library.controller.command.*;
import org.library.controller.model.dao.BookDao;
import org.library.controller.model.dao.DaoFactory;
import org.library.controller.model.dao.UserDao;
import org.library.controller.model.services.BookService;
import org.library.controller.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService;
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest,httpServletResponse);

    }
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest,httpServletResponse);
    }

    @Override
    public void init() throws ServletException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        BookDao bookDao = factory.createBookDao();
        userService = new UserService(userDao);
        bookService = new BookService(bookDao);


        commands.put("login", new LoginCommand(userService));
        commands.put("registration", new RegistrationCommand(userService));
        commands.put("admin", new AdminCommand());
        commands.put("user", new UserCommand());
        commands.put("admin/library", new LibraryCommand());
        commands.put("admin/add-book", new AddBookCommand());
        commands.put("admin/orders", new OrdersCommand());
        commands.put("error", new ErrorCommand());
        commands.put("admin/logout", new LogoutCommand());
        commands.put("user/logout", new LogoutCommand());
        commands.put("user/all-books", new AllBooksCommand(bookService));
        commands.put("user/my-books", new MyBooksCommand());
        commands.put("user/order-book", new OrderBooksCommand());

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/" , "");
        Command command = commands.getOrDefault(path , (p) -> "redirect:/error");
        String page = command.execute(request);

        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect:", "/app"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
