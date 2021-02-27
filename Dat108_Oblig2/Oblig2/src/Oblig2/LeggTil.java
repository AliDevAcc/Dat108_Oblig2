package Oblig2;

import static Oblig2.UrlMappings.LEGGTIL_URL;
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



@WebServlet("/" + LEGGTIL_URL)
public class LeggTil extends HttpServlet {
    private static final long serialVersionUID = 1L;
	   
	    @Override
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        // Inn noe kode her i forbindelse med innlogging av bruker?
	    	String passord = request.getParameter("hardkodet");
	    	
	    	if (passord != null) { // || !erGyldig(passord)) {
	    		response.sendRedirect("/" + LOGIN_URL + "?feilkode=1");
	    	} else {
	    		
	    		//Innlogging:
	    		HttpSession sesjon = request.getSession(false);
	    		if (sesjon != null) {
	    			sesjon.invalidate();
	    		}
	    		sesjon = request.getSession(true);
	    		sesjon.setMaxInactiveInterval(30); //sekunder
	    		
	    		sesjon.setAttribute("Passord", passord);
	    		sesjon.setAttribute("cart", new Cart());
	    		
	    		
	    	    response.sendRedirect(WEBSHOP_URL);
	    	}
	    	response.sendRedirect(WEBSHOP_URL);
	    }
	   }
