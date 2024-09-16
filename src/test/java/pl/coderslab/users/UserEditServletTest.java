package pl.coderslab.users;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
  void doGet() {
  }

  @Test
  void doPost() {
  }
}