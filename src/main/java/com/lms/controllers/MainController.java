package com.lms.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.models.Book;
import com.lms.services.LmsService;

@Controller
public class MainController {
	
	@Autowired
	private LmsService lmsService;
	
	@GetMapping(value="/")
	public ModelAndView init(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("index");
		
		//Collection<Book> books = lmsService.findAllBooks();
		
		mv.addObject("books", lmsService.findAllBooks());
		req.setAttribute("mode", "BOOK_VIEW");
		
		return mv;
	}
	
	@GetMapping(value="/update")
	public ModelAndView init(@RequestParam long id, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("index");
		
		
		Book book = lmsService.findOne(id);
		
		mv.addObject("book", book);
		req.setAttribute("mode", "BOOK_EDIT");
		
		return mv;
	}
	
	@GetMapping(value="/newBook")
	public ModelAndView create( HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("index");
		
		
		Book book = new Book();
		
		mv.addObject("book", book);
		req.setAttribute("mode", "BOOK_NEW");
		
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), false));
	}
	
	@PostMapping(value="/save")
	public void update(@ModelAttribute Book book, BindingResult result, HttpServletResponse res) throws IOException{
		
		lmsService.save(book);

		res.sendRedirect("/");
	}
	
	@GetMapping(value="/deleteBook")
	public void deleteBook(@RequestParam long id, HttpServletRequest req, HttpServletResponse res) throws IOException {

		 lmsService.delete(id);
		 
		res.sendRedirect("/");
	}

}
