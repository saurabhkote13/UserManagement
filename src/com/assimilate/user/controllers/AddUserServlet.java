package com.assimilate.user.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assimilate.user.models.User;
import com.assimilate.user.services.UserService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/user/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("user/add.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String sex = request.getParameter("sex");

		User user = new User(name, email, password, sex, country);

		UserService userService = new UserService();
		List<String> errors = userService.validateUser(user);
		
		if(errors.size() > 0) {
			// do not add this user
			//show the error messages on ui
			//show the add user form
		}else {
			int result = userService.addUser(user);
			
			if(result > 0) {
				//
				System.out.println("User added succesfuly.. redirecting to view all");
				response.setContentType("text/html");
				response.getWriter().write("User added successfuly");
				request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
			}
		}

	}

}
