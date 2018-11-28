package com.mit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter{
//	Logger logger = LoggerFactory.getLogger(MainInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Object o=request.getSession().getAttribute("id");
//		logger.info(request.getRemoteAddr()+"==="+request.getRequestURI());
		response.setContentType("text/plain;charset=UTF-8");
		if(o==null){//用
			response.getWriter().print("{'s':-1}");
    		return false;
		}

		return super.preHandle(request, response, handler);
	}

//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		super.afterCompletion(request, response, handler, ex);
//	}
//
//	@Override
//	public void afterConcurrentHandlingStarted(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		// TODO Auto-generated method stub
//		super.afterConcurrentHandlingStarted(request, response, handler);
//	}

}
