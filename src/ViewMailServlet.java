

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class ViewMailServlet
 */
@WebServlet("/ViewMailServlet")
public class ViewMailServlet extends HttpServlet {
	
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
			
			try{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from mailer_message where id=?");
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.println("<h1>"+rs.getString("subject")+"</h1><hr/>");
					out.println("<p><b>Message:</b><br/>"+rs.getString("message")+"<br/><b>By:</b>"+rs.getString("sender")+"</p>");
					out.println("<p><a href='DeleteMailServlet?id="+rs.getString(1)+"'>Delete Mail</a></p>");
					
				}
				con.close();
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
