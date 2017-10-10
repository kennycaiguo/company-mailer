

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TrashServlet
 */
@WebServlet("/TrashServlet")
public class TrashServlet extends HttpServlet {
	
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
			out.println("<b>Sent Mail</b>");
			try{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from mailer_message where reciever=? or sender=? and trash=? order by id desc");
				ps.setString(1, email);
				ps.setString(2, email);
				ps.setString(3, "yes");
				ResultSet rs=ps.executeQuery();
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'><td>Sender</td><td>Subject</td></tr>");
				while(rs.next())
				{
					out.print("<tr><td>"+rs.getString("sender")+"</td><td>"+rs.getString("subject")+"</td></tr>");
				}
				out.print("</table>");
				
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
