package pl.coderslab.users;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.User;
import pl.coderslab.UserDao;

class UserAddServletTest {

  private HttpServletRequest request;
  private HttpServletResponse response;
  private RequestDispatcher requestDispatcher;
  private UserDao userDao;
  private UserAddServlet userAddServlet;

  @BeforeEach
  void setUp() {
    request = Mockito.mock(HttpServletRequest.class);
    response = Mockito.mock(HttpServletResponse.class);
    requestDispatcher = Mockito.mock(RequestDispatcher.class);
    userDao = Mockito.mock(UserDao.class);

    userAddServlet = new UserAddServlet(userDao);

  }

  @Test
  void shouldRedirectToAddJspWithDoGet() throws ServletException, IOException {

    when(request.getRequestDispatcher("/users/add.jsp")).thenReturn(requestDispatcher);

    userAddServlet.doGet(request, response);
    verify(request).getRequestDispatcher("/users/add.jsp");
    verify(requestDispatcher).forward(request, response);

  }

  @Test
  void shouldCreateUserAndRedirectWithDoPost() throws ServletException, IOException {
    userAddServlet = new UserAddServlet(userDao);

    User user = new User();
    user.setUsername("Ania");
    user.setEmail("Ania@anna.com");
    user.setPassword("Ania123");

    when(request.getParameter("userName")).thenReturn(user.getUsername());
    when(request.getParameter("userEmail")).thenReturn(user.getEmail());
    when(request.getParameter("userPassword")).thenReturn(user.getPassword());

    when(request.getContextPath()).thenReturn("/app");

    userAddServlet.doPost(request, response);

    verify(userDao, times(1)).createUser(argThat(user1 ->
        "Ania".equals(user1.getUsername()) &&
            "Ania@anna.com".equals(user1.getEmail()) &&
            "Ania123".equals(user1.getPassword())
    ));
    verify(response).sendRedirect("/app/user/list");  }
}