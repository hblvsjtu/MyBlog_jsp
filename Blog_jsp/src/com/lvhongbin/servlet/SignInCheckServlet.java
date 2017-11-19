package com.lvhongbin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lvhongbin.bean.User;
import com.lvhongbin.service.ServiceSignInAndUpCheck;

/**
 * Servlet implementation class LoginCheckServlet
 * 
 */

public class SignInCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (null!=request.getParameter("btnQuitSignIn")) {
			response.sendRedirect("/Blog_jsp/jsp/Index.jsp?isQuitSignIn=true");
		}else {
			System.out.println("======== 调用了LoginCheckServlet的doGet（）方法");
			String username = request.getParameter("user");
			String password = request.getParameter("pwd");
			String signUpFlag="false";
			if(null!=request.getAttribute("signUpFlag")) {
				signUpFlag= request.getAttribute("signUpFlag").toString();
			}
			User user=new User();
			user.setName(username);
			user.setPassword(password);
			ServiceSignInAndUpCheck serviceSignInAndUpCheck =new ServiceSignInAndUpCheck(user);
			RequestDispatcher rd = null;
			ServletContext context = request.getSession().getServletContext();
			// 将来访数量值放入到ServletContext中
			if(serviceSignInAndUpCheck.check() || "true".equals(signUpFlag)) {
				context.setAttribute("signInFlag", "true");
				rd=request.getRequestDispatcher("/jsp/Index.jsp");
				System.out.println("======== signInCheckServlet验证成功 ========");
				rd.forward(request, response);
				System.out.println("======== SignInCheckServlet请求转发成功 ========");
			}else if(serviceSignInAndUpCheck.checkName()) {
				context.setAttribute("signInFlag", "false");
				response.sendRedirect("/Blog_jsp/jsp/Index.jsp?hasSignInName=true");
				System.out.println("======== signInCheckServlet验证失败,用户已存在! ========");
			}else {
				context.setAttribute("signInFlag", "false");
				response.sendRedirect("/Blog_jsp/jsp/Index.jsp?hasSignInName=false");
				System.out.println("======== signInCheckServlet验证失败，用户不存在! ========");
			}
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