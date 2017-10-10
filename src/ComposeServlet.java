

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ComposeServlet")
public class ComposeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("title.html");
		rd.include(request, response);
		RequestDispatcher rd1=request.getRequestDispatcher("link.html");
		rd1.include(request, response);
		HttpSession s=request.getSession(false);
		if(s==null)
		{
			response.sendRedirect("index.html");
		}
		else
		{
			String email=(String)s.getAttribute("email");
			out.println("<span style='float:right'>HII "+email+"</span>");
			String msg=(String)request.getAttribute("msg");
			if(msg!=null)
			{
				out.println("<p>"+msg+"</p>");
			}
			request.getRequestDispatcher("composeform.html").include(request, response);
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}

	

}
