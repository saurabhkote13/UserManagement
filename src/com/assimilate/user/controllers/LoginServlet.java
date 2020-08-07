package com.assimilate.user.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assimilate.user.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
		PreparedStatement ps=con.prepareStatement("select * from accounts where username=? and password=?");
		ps.setString(1,name);
		ps.setString(2,password);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			String usernamefromdb=rs.getString("username");
			String passwordfromdb=rs.getString("password");
			LoginService loginservice=new LoginService();
			Boolean isValiduser=loginservice.validateUser(usernamefromdb,passwordfromdb);
			if(isValiduser)
			{
				HttpSession session=request.getSession();
				session.setAttribute("UserLoggedIn",usernamefromdb);
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.print("<h2>Welcome:"+usernamefromdb+"</h2>");
				RequestDispatcher rd=request.getRequestDispatcher("user/userform.jsp");
				rd.include(request,response);
		}
		}
			else
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.print("incorrect username and password");
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.include(request,response);
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
