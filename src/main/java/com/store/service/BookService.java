package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.Book;
import com.store.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public void save(Book b) {
		bookRepository.save(b);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();

	}

	public Book getByBookId(int id) {
		return bookRepository.findById(id).get();

	}
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}
}
