package project001.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor  {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	  @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception exception)
	            throws Exception {
		  	//System.out.println("1");
		  	
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response,
	            Object handler, ModelAndView view) throws Exception {
	    	//System.out.println("2");
	    }

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	            Object handler) throws Exception {
	    	//System.out.println("3");
	    	HttpSession httpSession = request.getSession();
	    	String basePath = getBasePath(request);
		  	if(httpSession.getAttribute("admin") == null) {
		  		response.sendRedirect(basePath+"admin/login");
				return false;
			}
	    	 return true;
	    }
	    
}
