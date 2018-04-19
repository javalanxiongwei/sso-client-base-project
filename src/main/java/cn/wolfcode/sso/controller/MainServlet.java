package cn.wolfcode.sso.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.sso.util.SSOClientUtil;

@WebServlet(name = "mainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("serverLogOutUrl", SSOClientUtil.getServerLogOutUrl());
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	}
}
