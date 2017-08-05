package com.rurallabs.sportsbets.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rurallabs.sportsbets.business.exceptions.DuplicatedEmailException;
import com.rurallabs.sportsbets.business.exceptions.DuplicatedLoginException;
import com.rurallabs.sportsbets.business.services.UserService;
import com.rurallabs.sportsbets.web.beans.UserBean;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping({ "/", "/home", "login"})
	public String homeRequest(final HttpServletRequest request, final ModelMap model) {
		
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			/* The user is not logged in */
			final UserBean user = new UserBean();
			model.addAttribute("user", user);
			return "home";
		}
		
		return "redirect:/overview";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(final HttpServletRequest request, final ModelMap model, 
			@Valid @ModelAttribute(value = "user") final UserBean user, final BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
	        return "home";
	    }
		
		try {
			this.userService.save(user.getLogin(), user.getEmail(), user.getPassword());
		} catch (final DuplicatedLoginException e) {
			model.addAttribute("user", user);
			redirectAttributes.addFlashAttribute("error", "register.form.duplicated.login");
			return "home";
		} catch (final DuplicatedEmailException e) {
			redirectAttributes.addFlashAttribute("error", "register.form.duplicated.login");
			model.addAttribute("user", user);
			return "home";
		}
		
		// Perform authentication
		this.authenticateUserAndSetSession(user.getLogin(), request);
		
		return "redirect:/overview";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(final HttpServletRequest request, final ModelMap model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/home";
	}
	
	private void authenticateUserAndSetSession(final String login, final HttpServletRequest request) {

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(login);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
		
        // generate session if one doesn't exist
        request.getSession();

    }
}
