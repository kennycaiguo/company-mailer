

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class DeleteMailServlet
 */
@WebServlet("/DeleteMailServlet")
public class DeleteMailServlet extends HttpServlet {
	
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
			int id=Integer.parseInt(request.getParameter("id"));
			try
			{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("update mailer_message set trash=? where id=?"); 
				ps.setString(1, "yes");
				ps.setInt(2, id);
				int i=ps.executeUpdate();
				if(i>0)
				{
					request.setAttribute("msg", "mail successfully deleted");
					request.getRequestDispatcher("InboxServlet").forward(request, response);
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	

}
