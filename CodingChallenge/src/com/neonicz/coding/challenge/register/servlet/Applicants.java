package com.neonicz.coding.challenge.register.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neonicz.coding.challenge.register.dao.RegisterApplicantDAO;

/**
 * Servlet implementation class Applicants
 */
@WebServlet("/Applicants")
public class Applicants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Applicants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher serve = request.getRequestDispatcher("applicants.jsp");
		
		RegisterApplicantDAO applicantDao = new RegisterApplicantDAO();
		List<Map<String,String>>  applicants = applicantDao.getAllApplicants();
		request.setAttribute("applicants", applicants);
		serve.forward(request, response);
	}

}
