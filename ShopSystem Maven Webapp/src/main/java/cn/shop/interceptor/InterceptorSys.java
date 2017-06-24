package cn.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.shop.pojo.User;
import cn.shop.tools.Constants;

/**
 * 
 *对用户请求过滤的拦截器
 */
public class InterceptorSys extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(InterceptorSys.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("InterceptorSys   preHandle-------------------------->");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		if (user == null) {// session中用户为空
			response.sendRedirect(request.getContextPath() + "/401.html");
			return false;
		}
		return true;
	}

}
