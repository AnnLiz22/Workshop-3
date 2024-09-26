package pl.coderslab.users;

import pl.coderslab.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {

  private final UserDao userDao;

  public UserDeleteServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String idParam = req.getParameter("id");
      if (idParam == null || idParam.isEmpty()) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing.");
        return;
      }
      int userId = Integer.parseInt(idParam);

      userDao.deleteUserById(userId);
      resp.sendRedirect("/users/list");
    } catch (NumberFormatException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID.");
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the user.");
    }
  }
}