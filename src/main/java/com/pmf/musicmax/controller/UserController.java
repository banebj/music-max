package com.pmf.musicmax.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmf.musicmax.model.User;
import com.pmf.musicmax.repository.UserRepo;
import com.pmf.musicmax.repository.UserRepository;


@Controller
@RequestMapping(value="user")
public class UserController {

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserRepo urr;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveUser(User u, Model m) {
		/*ur.save(u);
		m.addAttribute("message", "Korisnik je uspesno sacuvan.");*/
		return "unos/Registracija";
	}
	
	@RequestMapping(value="registration", method = RequestMethod.GET)
	public String registracija(Model m) {
		return "unos/Registracija.jsp";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = urr.login(username, password);
		if(user!=null){
			request.getSession().setAttribute("messagee", "");
			request.getSession().setAttribute("user", user);
			session.setAttribute("userr", user);
			return "home";
		}
		request.getSession().setAttribute("messagee", "Username or password does not exist");
		return "unos/login";
    }
}
