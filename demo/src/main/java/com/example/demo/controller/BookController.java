package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.form.BookForm;
import com.example.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    private static List<Book> books = new ArrayList<Book>();

    static {
        books.add(new Book("Кобзар", "fjh1840", "Тарас Шевченко"));
        books.add(new Book("Лис Микита", "ghk1896", "Іван Франко"));
    }


    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = { "/bookList" }, method = RequestMethod.GET)
    public String bookList(Model model) {

        model.addAttribute("books", books);

        return "bookList";
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {

        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);

        return "create-book";
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    public String saveBook(Model model, //
                             @ModelAttribute("bookForm") BookForm bookForm) {

        String title = bookForm.getTitle();
        String isbn = bookForm.getIsbn();
        String author = bookForm.getAuthor();

        if (title != null && title.length() > 0 //
                && isbn != null && isbn.length() > 0
                && author != null && author.length() > 0){
            Book newBook = new Book(title, isbn, author);
            books.add(newBook);

            return "redirect:/bookList";
        }
        return "create-book";
    }

}
