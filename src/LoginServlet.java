

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("title.html");
		rd.include(request, response);
		String email,password;
		email=request.getParameter("email");
		password=request.getParameter("password");
		if(LoginDao.validate(email,password))
		{
			HttpSession s=request.getSession();
			s.setAttribute("login",true);
			s.setAttribute("email", email);
			response.sendRedirect("InboxServlet");
		}
		else
		{
			out.print("<p>Invalid username and password</p>");
			RequestDispatcher rd2=request.getRequestDispatcher("loginform.html");
			rd2.include(request, response);
		}
		RequestDispatcher rd1=request.getRequestDispatcher("footer.html");
		rd1.include(request, response);
		out.close();
	

	}

}



