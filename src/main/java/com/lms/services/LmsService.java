package com.lms.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.LmsRepository;
import com.lms.models.Book;

@Service
public class LmsService {
	
	@Autowired
	private LmsRepository lmsRepository;
	
	public Collection<Book> findAllBooks(){
		List<Book> books = new ArrayList<>();
		for (Book book : lmsRepository.findAll()) {
			books.add(book);
		}
		
		return books;
	}
	
	public void delete(long id) {
		lmsRepository.delete(id);
		
	}
	
	public Book findOne(long id)  {
		return lmsRepository.findOne(id);
		//return lmsRepository.findById(id).orElseThrow(()-> new NotFound());
	}
	
	public void save(Book book) {
		lmsRepository.save(book);
	}

}
