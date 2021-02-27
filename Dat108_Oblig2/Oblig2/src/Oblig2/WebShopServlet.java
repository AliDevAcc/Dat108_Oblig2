package Oblig2;

import static Oblig2.UrlMappings.WEBSHOP_URL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/" + WEBSHOP_URL)
public class WebShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cart cart = new Cart();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);
		if (sesjon != null) {
					
			response.setContentType("text/html; charset=ISO-8859-1");
			request.setAttribute("WEBSHOP_URL", cart.getItems());

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Web Shop</title>");
			out.println("</head>");
			out.println("<body>");

			
			out.println("<p> Min handleliste </p>");

			
			out.println("</table><br/>");
			out.println("<form action=\"" + WEBSHOP_URL + "\" method=\"post\">");
			out.println("<fieldset>");
			out.println("<legend>Handleliste</legend>");
			out.println("<p><input type=\"submit\" name=\"action\" value=\"Legg til\" p><input name=\"Element\" type=\"item\" /></p> ");
			out.println("</form>");
			for(CartItem c : cart.getItems()) {
				out.println("<form action=\"" + WEBSHOP_URL + "\" method=\"post\">");
				out.println("<p>" + c.getName() + " <input type=\"submit\" name=\"action\" value=\"Slett\" p>");
				out.println("<p><input type=\"hidden\" name=\"action\"value=\"c.getName\" p>");
				out.println("</form>");
			} 
			
			out.println("</fieldset>");
			out.println("</body>");
			out.println("</html>");

		}	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);
		if (sesjon != null) {
					
			response.setContentType("text/html; charset=ISO-8859-1");
			
			String action = request.getParameter("action");
			
			if(action.equals("Legg til")) {
				cart.addItem(request.getParameter("Element"));
				
			} else if(action.equals("Slett")) {
				//cart.removeItem(("Slett"));
				
			} 
			
			response.sendRedirect(WEBSHOP_URL);
		}
	}
}
