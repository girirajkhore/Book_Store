package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Book;
import com.store.entity.MyBookList;
import com.store.service.BookService;
import com.store.service.MyBookListService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	MyBookListService bookListService;

	@GetMapping("/")
	public String home() {
		return "home";

	}

	@GetMapping("/bookregister")
	public String bookregister() {
		return "bookRegister";

	}

	@GetMapping("/availablebook")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBooks();
//ModelAndView m= new ModelAndView();
//m.setViewName("bookList");
//m.addObject("book", list);
		return new ModelAndView("bookList", "book", list);

	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookService.save(b);
		return "redirect:/availablebook";

	}

	@GetMapping("/myBooks")
	public String getMyBook(Model model) {
		List<MyBookList> list=bookListService.getAllMyBook();
		model.addAttribute("book",list);
		return "myBooks";
	}

	@RequestMapping("/myList/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b =bookService.getByBookId(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		bookListService.saveMyBook(mb);
		return "redirect:/myBooks";
	}
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable ("id") int id, Model model) {
		Book b=bookService.getByBookId(id);
		model.addAttribute("book", b);
		return "bookEdit";
		
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable ("id") int id) {
		bookService.deleteById(id);
		return "redirect:/availableBook";
		
	}
}
