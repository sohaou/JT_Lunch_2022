package egovframework.jtLunch.cmmn.security.Interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import egovframework.jtLunch.cmmn.security.Service.UserService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	Logger log = Logger.getLogger(getClass());
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	//여기 변수명 LoginFailureHandler 설명 참조 같은 원리입니다
	private String loginidname;
	private String defaultUrl;
	
	
	@Resource(name="userSer")
	private UserService userSer;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

			String username = request.getParameter(loginidname);

			userSer.updateAccessDate(username);
			
			//에러 세션 지우기 > 로그인 성공햇는데 에러세션이 남아있을 필요가 없음
			clearAuthenticationAttributes(request);
			
			//Redirect URL 작업 
			resultRedirectStrategy(request, response, authentication);
		}
		
	    //에러 세션 지우는 메소드
	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			if(session==null) return;
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		
	    //마지막 접근 Redirect url 작업
		protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			
			//session이 이동할 url 정보 <= 인증 권한이 필요한 페이지 접근했을 경우
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			
			if(savedRequest!=null) { //내가 클릭해서 갓나 > 클릭하기 전 내가 클릭한 페이지 저장 > 인증 후 클릭한 페이지로 이동
				log.debug("권한이 필요한 페이지에 접근했을 경우");
				useSessionUrl(request, response);
			} else { //직접 url 쳐서 갓나 > url로 직접 쳐서 로그인 페이지로 들어갔기 때문에 저장할 필요가없음 savedRequest가 필요가 없음
				log.debug("직접 로그인 url로 이동했을 경우");
				useDefaultUrl(request, response);
			}
		}
		
		protected void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
		}
		
		protected void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {

			redirectStratgy.sendRedirect(request, response, defaultUrl);
		}
	 
	    public String getLoginidname() {
	        return loginidname;
	    }
	 
	    public void setLoginidname(String loginidname) {
	        this.loginidname = loginidname;
	    }
	 
	    public String getDefaultUrl() {
	        return defaultUrl;
	    }
	 
	    public void setDefaultUrl(String defaultUrl) {
	        this.defaultUrl = defaultUrl;
	    }
	 
	}
