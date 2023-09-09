package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.MyBookList;
import com.store.repository.MyBookListRepository;

@Service
public class MyBookListService {
@Autowired
private MyBookListRepository bookListRepository;

public void saveMyBook(MyBookList b) {
	bookListRepository.save(b);
}
public List<MyBookList> getAllMyBook(){
	return bookListRepository.findAll();
	
}
public void deleteById(int id) {
	bookListRepository.deleteById(id);;
}
}
