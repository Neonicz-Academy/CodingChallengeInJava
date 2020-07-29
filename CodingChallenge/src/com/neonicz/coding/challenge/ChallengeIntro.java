package com.neonicz.coding.challenge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChallengeIntro
 */
@WebServlet("/ChallengeIntro")
public class ChallengeIntro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeIntro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * PrintWriter out = response.getWriter();
		 * out.println("<h1>Coding Challenge</h1>" + new Date());
		 * out.println("<h1>Coding Challenge</h1>" + new Date());
		 * out.println("<h1>Coding Challenge</h1>" + new Date());
		 * out.println("<h1>Coding Challenge</h1>" + new Date());
		 */// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String myName = (String) request.getParameter("name");
		System.out.println("My name Get :> "+ myName);
		
		RequestDispatcher serve = request.getRequestDispatcher("ccj.jsp");
		request.setAttribute("userName", myName);
		serve.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String myName = (String) request.getParameter("name");
		System.out.println("My name Post :> "+ myName);
		doGet(request, response);
	}

}
