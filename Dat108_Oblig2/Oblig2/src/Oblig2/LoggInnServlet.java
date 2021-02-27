package Oblig2;

import static Oblig2.UrlMappings.LOGIN_URL;
import static Oblig2.UrlMappings.WEBSHOP_URL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoggInnServlet", urlPatterns = "/login")
public class LoggInnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String feilkode = request.getParameter("feilkode");
        String feilmelding = "";
        if (feilkode != null) {
        	if (feilkode.equals("1")) {
        		feilmelding = "Ugyldig passord ...";
        	} else if (feilkode.equals("2")) {
        		feilmelding = "Du m� logge inn f�r du handler ...";
        	}
        }
        
        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        
       
        out.println("<p>" + feilmelding + "</p>");
        
        out.println("<form action=\"" + "http://localhost:8080" + LOGIN_URL + "\" method=\"post\">");
        out.println("  <fieldset>");
        out.println("    <legend>Login</legend>");
        out.println("    <p>Passord: <input type=\"text\" name=\"passord\" /></p>");
        out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
        out.println("  </fieldset>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	
    	String passord = request.getParameter("passord");
    	String test = getServletConfig().getInitParameter("passord");
    	
    	if (passord != null && passord.equals(test)) {  
    		
    		HttpSession sesjon = request.getSession(false);
    		if (sesjon != null) {
    			sesjon.invalidate();
    		}
    		String timeout = getServletConfig().getInitParameter("timeout");
    		int time = Integer.parseInt(timeout);
    		
    		sesjon = request.getSession(true);
    		sesjon.setMaxInactiveInterval(time); 
    		
    		response.sendRedirect(WEBSHOP_URL);
    		
    	} else {

    		response.sendRedirect("http://localhost:8080" + LOGIN_URL + "?feilkode=1");
    		
   
    	}
    	
    }
}



