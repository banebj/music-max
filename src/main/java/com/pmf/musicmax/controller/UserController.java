package com.pmf.musicmax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pmf.musicmax.model.User;
import com.pmf.musicmax.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	UserRepository ur;
	
	/*
	 * @RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveStudent(Student s,Model m) {
		sr.save(s);
		m.addAttribute("message", "Uspesno sacuvan");
		return "unos/unosStudenta";
	}
	 * */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveUser(User u, Model m) {
		ur.save(u);
		m.addAttribute("message", "Korisnik je uspesno sacuvan.");
		return "unos/Registracija";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
