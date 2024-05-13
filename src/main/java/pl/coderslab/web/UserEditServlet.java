package pl.coderslab.web;

import pl.coderslab.User;
import pl.coderslab.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/edit")
public class UserEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserDao userDao = new UserDao();
        try {
            User read = userDao.readUserById(Integer.parseInt(id));
            req.setAttribute("user", read);
            getServletContext().getRequestDispatcher("/users/edit.jsp")
                    .forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
