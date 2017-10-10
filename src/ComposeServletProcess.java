

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComposeServletProcess
 */
@WebServlet("/ComposeServletProcess")
public class ComposeServletProcess extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("title.html");
		rd.include(request, response);
		String reciever=request.getParameter("to");
		String message=request.getParameter("message");
		message=message.replaceAll("\n","<br/>");
		String subject=request.getParameter("subject");
		String sender=(String)request.getSession(false).getAttribute("email");
		int i=ComposeDao.save(sender,reciever,subject,message);
		if(i>0)
		{
			request.setAttribute("msg", "message successfully sent");
			request.getRequestDispatcher("ComposeServlet").forward(request, response);
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}

	

}
