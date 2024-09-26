package pl.coderslab.users;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import pl.coderslab.UserDao;

class UserDeleteServletTest {

  private HttpServletRequest request;
  private HttpServletResponse response;
  private RequestDispatcher requestDispatcher;
  private UserDao userDao;
  private UserDeleteServlet userDeleteServlet;

  @BeforeEach
  void setUp() {
    request = Mockito.mock(HttpServletRequest.class);
    response = Mockito.mock(HttpServletResponse.class);
    requestDispatcher = Mockito.mock(RequestDispatcher.class);
    userDao = Mockito.mock(UserDao.class);

    userDeleteServlet = new UserDeleteServlet(userDao);

  }


  @Test
  void shouldDeleteUserAndRedirect() throws ServletException, IOException {
    userDeleteServlet = new UserDeleteServlet(userDao);

    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("userName")).thenReturn("Ania");
    when(request.getParameter("userEmail")).thenReturn("ania@ania.pl");

    doNothing().when(userDao).deleteUserById(1);

    userDeleteServlet.doPost(request, response);

    verify(userDao, times(1)).deleteUserById(1);

  }

  @Test
  void shouldHandleSQLException() throws Exception {
    when(request.getParameter("id")).thenReturn("1");
    doThrow(new SQLException()).when(userDao).deleteUserById(1);

    userDeleteServlet.doPost(request, response);

    verify(userDao, times(1)).deleteUserById(1);
  }
}