package pl.coderslab.users;

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

class UserAddServletTest {

  private HttpServletRequest request;
  private HttpServletResponse response;
  private RequestDispatcher requestDispatcher;
  private UserAddServlet userAddServlet;

  @BeforeEach
  void setUp() {
    request = Mockito.mock(HttpServletRequest.class);
    response = Mockito.mock(HttpServletResponse.class);
    requestDispatcher = Mockito.mock(RequestDispatcher.class);

    userAddServlet = new UserAddServlet();

  }

  @Test
  void doGet() throws ServletException, IOException {

    when(request.getRequestDispatcher("/users/add.jsp")).thenReturn(requestDispatcher);

    userAddServlet.doGet(request, response);
    verify(request).getRequestDispatcher("/users/add.jsp");
    verify(requestDispatcher).forward(request, response);

  }

  @Test
  void doPost() {


  }
}