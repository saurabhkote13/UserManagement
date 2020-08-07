package com.assimilate.user.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assimilate.user.services.UserService;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/user/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		UserService userService = new UserService();
		
		int status = userService.deleteUser(id);
		
		if(status >0) {
			System.out.println("User deleted succesfuly.. redirecting to view all");
			response.setContentType("text/html");
			response.getWriter().write("User deleted successfuly");
			request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
		}else {
			//
			response.setContentType("text/html");
			response.getWriter().write("Error while deleting the user");
			request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
