package pl.coderslab;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.coderslab.utils.DbUtil;

class UserDaoTest {
  @Mock
  private Connection mockConnection;
  @Mock
  private PreparedStatement mockPreparedStatement;
  UserDao userDao = new UserDao(mockConnection);

  UserDaoTest() {
  }

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(UserDao.class);
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    mockStatic(DbUtil.class);
    when(DbUtil.getConnection()).thenReturn(mockConnection);
  }

  @Test
  void shouldCreateUser() {
  }

  @Test
  void shouldUpdateUser() throws SQLException {
    User user = new User();
    user.setId(1);
    user.setUsername("Tomek");
    user.setEmail("tomek@tomek");
    user.setPassword("password");

    userDao.updateUser(user);

    verify(mockPreparedStatement).setInt(1, 1);
    verify(mockPreparedStatement).setString(2, "Tomek");
    verify(mockPreparedStatement).setString(3, "tomek@tomek");
    verify(mockPreparedStatement).setString(4, "password");
    verify(mockPreparedStatement).executeUpdate();

    verify(mockPreparedStatement).close();
    verify(mockConnection).close();

  }

  @Test
  void readUserById() {
  }

  @Test
  void deleteUserById() {
  }

  @Test
  void readAllUsers() {
  }

  @Test
  void hashPassword() {
  }
}