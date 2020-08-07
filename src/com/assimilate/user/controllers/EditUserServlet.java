package com.assimilate.user.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assimilate.user.models.User;
import com.assimilate.user.services.UserService;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/user/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		UserService userService = new UserService();

		User userFromDb = userService.getUserById(id);

		System.out.println("User to update: " + userFromDb);
		
		request.setAttribute("user", userFromDb);
		request.getRequestDispatcher("userform.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String sex = request.getParameter("sex");
		
		
		User user = new User(name, email, password, sex, country);
		user.setId(Integer.parseInt(id));
		
		try {
		UserService userService = new UserService();
		int result = userService.updateUser(user);
		if(result > 0) {
			//
			System.out.println("User updated succesfuly.. redirecting to view all");
			response.setContentType("text/html");
			response.getWriter().write("User updated successfuly");
			request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
		}else {
			response.setContentType("text/html");
			response.getWriter().write("Error while updating user");
			request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			
			response.setContentType("text/html");
			response.getWriter().write("Error while updating user");
			request.getRequestDispatcher("viewAllUsers.jsp").include(request, response);
		}
		
	}

}
