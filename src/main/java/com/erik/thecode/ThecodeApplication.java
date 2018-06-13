package com.erik.thecode;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@Controller
public class ThecodeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ThecodeApplication.class, args);
	}
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	@PostMapping("/code")
	public String code(@RequestParam(value="code") String code, RedirectAttributes redirectAttributes, HttpSession session) {
		System.out.println(code);
		if (code.equals("blue")) {
			session.setAttribute("in", true);
			System.out.println("correct code");
			return "redirect:/code";
		}
		else {
			session.setAttribute("in", false);
			redirectAttributes.addFlashAttribute("code", "You have answered... poorly.");
			System.out.println("wrong code");
			return "redirect:/";
		}
	}
	@GetMapping("/code")
	public String validate(HttpSession session) {
		System.out.println(Boolean.valueOf(session.getAttribute("in").toString()));
		if(Boolean.valueOf(session.getAttribute("in").toString()) == true) {
			return "code.jsp";
		}
		else {
			return "redirect:/";
		}
		
	}
}
