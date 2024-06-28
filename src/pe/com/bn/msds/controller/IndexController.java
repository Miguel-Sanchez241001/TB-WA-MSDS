package pe.com.bn.msds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.common.View;

@Controller
public class IndexController {

	private static LoggerBn log = LoggerBn.getInstance(IndexController.class.getName());
	
	@RequestMapping("/")
	public String showIndex(Model model) {
		return "redirect:/home";
	}

//	@RequestMapping("/about")
//	public String showAbout(SessionStatus sessionStatus) {
//		sessionStatus.setComplete();
//		return "about";
//	}

	@RequestMapping("/login")//home
	public String showHome(SessionStatus sessionStatus, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		String path = View.returnJsp(model, null);
		log.debug("IndexController | /home | path:" + path, "1");
		
		return path;
	}

	@RequestMapping("/home")
	public String home(SessionStatus sessionStatus, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		String path = View.returnJsp(model, null);
		log.debug("IndexController | /home | path:" + path, "1");
		
		return path;
	}
	
//	@RequestMapping("/login")
//	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
//		return "template/login";
//	}

//	@RequestMapping("/logout")
//	public String logout() {
//		return "/";
//	}
	
	@RequestMapping(value = "/redirecLogout", method = RequestMethod.GET)
	public void redirecLogout(HttpServletResponse httpServletResponse) {
		
		log.debug("IndexController | /redirecLogout | Location 302 pkmslogout", "1");
		
		SecurityContextHolder.getContext().setAuthentication(null);
		
		httpServletResponse.setHeader("Location", "/pkmslogout");
	    httpServletResponse.setStatus(302);
	}
	
	@RequestMapping(value = "/403")
	public String accessDenied(ModelMap model) {
		
		String path = View.returnJsp(model, "404");
		log.debug("IndexController | /403 | path:" + path, "1");
		
		return path;
	}
	
}
