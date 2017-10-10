

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("title.html");
		rd.include(request, response);
		request.getSession().invalidate();
		out.println("<p>You are successfully logged out</p>");
		RequestDispatcher rd1=request.getRequestDispatcher("loginform.html");
		rd1.include(request, response);
		RequestDispatcher rd2=request.getRequestDispatcher("footer.html");
		rd2.include(request, response);
		out.close();
	}

	

}
