package jsp.org.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Login")
public class Login extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "select * from test.login where username=? and password=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String name = resultSet.getString("Name");
				String phno = resultSet.getString("phno");
				String mail = resultSet.getString("mail");
				PrintWriter printWriter = resp.getWriter();
				printWriter.println("your name is:"+name);
				printWriter.println("your phone number is:"+phno);
				printWriter.println("your emailID is:"+mail);
				connection.close();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
