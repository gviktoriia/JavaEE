package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class HwLibraryApplication {

	private static BookService bookService;
	public static void main(String[] args) {
		ApplicationContext aplCont= SpringApplication.run(HwLibraryApplication.class, args);
		bookService=aplCont.getBean(BookService.class);

	}
	@RequestMapping({ "/", "" })
	public String index(){
		return "redirect:/books-list";
	}


	@RequestMapping(value = "/book/{id}")
	public String getBook(@PathVariable(value = "id")int id, Model model){
		Book b=bookService.getBookById(id);
		//bookService.createUser(b.getName(), b.getIbsn(), b.getAuthor());
		model.addAttribute("mybook", b);
		return "BookPage";
	}

	@RequestMapping(value = "/books-list", method = RequestMethod.GET)
	public String booksList(Model model) {

		model.addAttribute("books", bookService.getBooks());

		return "form-controller-get";
	}
	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String addNewBook(@ModelAttribute Book book1, Model model) {

		Book b=new Book(book1.getName(), book1.getIbsn(), book1.getAuthor());
		if(!bookService.contains(b)) {
			bookService.createUser(b);
			model.addAttribute("books", bookService.getBooks());
		}

		return "redirect:/books-list";
	}

	@RequestMapping(value = "/filter")
	public String GetFilteredBook(){
		return "Filter";

	}
	@RequestMapping(value = "/filter2", method = RequestMethod.POST)
	public String GetFilteredBook(@RequestParam("name") String name, Model model){
		model.addAttribute("word", bookService.getWord(name));
		return "FilteredBook";

	}
}
