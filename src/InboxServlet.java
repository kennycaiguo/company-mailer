

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.sql.*;

/**
 * Servlet implementation class InboxServlet
 */
@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet {
	
    
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
			out.println("<h1>inbox</h1>");
			String msg=(String)request.getAttribute("msg");
			if(msg!=null)
			{
				out.println("<p>msg</p>");
			}
			try
			{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from mailer_message where reciever=? and trash='no' order by id desc");
				ps.setString(1, email);
				ResultSet rs=ps.executeQuery();
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:green;color:white;'><td>sender</td><td>subject</td></tr>");
				while(rs.next())
				{
					out.print("<tr><td>"+rs.getString("sender")+"</td><td><a href='ViewMailServlet?id="+rs.getString(1)+"'>"+rs.getString("subject")+"</a></td></tr>");
				}
				out.print("</table>");
				con.close();
			}
			catch(Exception e)
			{
				out.println(e);
			}
			
		}
		RequestDispatcher rd2=request.getRequestDispatcher("footer.html");
		rd2.include(request, response);
		out.close();
	}

	
}
