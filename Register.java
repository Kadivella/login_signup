package jsp.org.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Register")
public class Register extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String phone = req.getParameter("phno");
		String mail = req.getParameter("mail");
		String user = req.getParameter("un");
		String pass = req.getParameter("pwd");
		
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "insert into test.login values(?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, user);
			preparedStatement.setString(5, pass);
			preparedStatement.executeUpdate();
			connection.close();
			System.out.println("Done");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
