package pl.coderslab.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.User;
import pl.coderslab.UserDao;

class UserEditServletTest {
  private HttpServletRequest request;
  private HttpServletResponse response;
  private RequestDispatcher requestDispatcher;
  private UserDao userDao;

  private UserEditServlet userEditServlet;
  @BeforeEach
  void setUp() {
    request = Mockito.mock(HttpServletRequest.class);
    response = Mockito.mock(HttpServletResponse.class);
    requestDispatcher = Mockito.mock(RequestDispatcher.class);
    userDao = Mockito.mock(UserDao.class);

    userEditServlet = new UserEditServlet(userDao);

  }

  @Test
  void shouldRedirectToGivenUserWithDoGet() throws ServletException, IOException {
    when(request.getRequestDispatcher("/users/edit.jsp")).thenReturn(requestDispatcher);
    userEditServlet.doGet(request, response);
    verify(request).getRequestDispatcher("/users/edit.jsp");
    verify(requestDispatcher).forward(request, response);
  }

  @Test
  void shouldEditUserAndRedirectWithDoPost() throws IOException, ServletException, SQLException {
    userEditServlet = new UserEditServlet(userDao);

    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("userName")).thenReturn("Ania");
    when(request.getParameter("userEmail")).thenReturn("ania@ania.pl");

    userEditServlet.doPost(request, response);

    verify(userDao, times(1)).updateUser(argThat(user1 -> user1.getId()==1));
    verify(userDao, times(1)).updateUser(argThat(user1 -> user1.getUsername().equals("Ania")));
    verify(userDao, times(1)).updateUser(argThat(user1 -> user1.getEmail().equals("ania@ania.pl")));
  }
}