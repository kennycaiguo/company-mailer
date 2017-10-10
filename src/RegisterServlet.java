

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name,email,password,dob,city,state,country,gender,address,contact;
		PrintWriter out=response.getWriter();
		name=request.getParameter("name");
		email=request.getParameter("email");
		password=request.getParameter("password");
		dob=request.getParameter("dob");
		city=request.getParameter("city");
		state=request.getParameter("state");
		country=request.getParameter("country");
		contact=request.getParameter("contact");
		gender=request.getParameter("gender");
		address=request.getParameter("addressLine");
	
		RequestDispatcher rd=request.getRequestDispatcher("title.html");
		rd.include(request, response);
		int status=RegisterDao.save(name,email+"@mymailer.com",password,gender,dob,address,city,state,country,contact);
		if(status>0)
		{
			out.println("successfully stored");
			RequestDispatcher rd1=request.getRequestDispatcher("loginform.html");
			rd1.include(request, response);
		}
		else
		{
			out.println("something went wrong");
			out.println("<br><a href='index.html'>try again</a>");

		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
