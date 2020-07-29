package com.neonicz.coding.challenge.register.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neonicz.coding.challenge.register.dao.RegisterApplicantDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher serve = request.getRequestDispatcher("register.jsp");
		
		serve.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("fullName"));
		System.out.println(request.getParameter("education"));
		
		String name = request.getParameter("fullName");
		String education =  request.getParameter("education");
		String place = request.getParameter("place");
		String institute = request.getParameter("institute");
		String email = request.getParameter("email");
		String contactNumber = request.getParameter("contactNumber");
		String description = request.getParameter("description");
		
		System.out.println("name.length() :> "+name.length());
		
		if((name != null && name.length()==0)  ||  
				(email != null && email.isEmpty()) || 
				(contactNumber != null && contactNumber.isEmpty())) {
			
			System.out.println("validation Failed");
			request.setAttribute("validationFailed", true);
			request.setAttribute("failure", true);
		}else {
			// Insert the data to database
			System.out.println("validation successful");
			RegisterApplicantDAO applicantDao = new RegisterApplicantDAO();
			if(!applicantDao.isApplicantRegistered(email,  Long.valueOf(contactNumber))) {
				if(applicantDao.saveApplicant(name, education, place, institute, email, Long.valueOf(contactNumber), description)) {
					request.setAttribute("success", true);
				}else {
					request.setAttribute("failure", true);
				}
			}else {
				request.setAttribute("alreadyRegistered", true);
			}
			
		}
		
		doGet(request, response);
	}

}
