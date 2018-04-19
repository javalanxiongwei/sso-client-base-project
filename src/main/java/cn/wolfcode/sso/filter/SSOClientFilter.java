package cn.wolfcode.sso.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wolfcode.sso.util.SSOClientUtil;
@WebFilter(filterName="SSOClientFilter",urlPatterns="/*")
public class SSOClientFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		//1.判断是否有局部的会话
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if(isLogin!=null && isLogin){
			//有局部会话,直接放行.
			chain.doFilter(request, response);
			return;
		}
		//没有局部会话,重定向到统一认证中心,检查是否有其他的系统已经登录过.
		// http://www.sso.com:8443/checkLogin?redirectUrl=http://www.crm.com:8088
		SSOClientUtil.redirectToSSOURL(req, resp);
	}

	@Override
	public void destroy() {}
}
