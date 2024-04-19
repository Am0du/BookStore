package com.example.book.store.rest;

import com.example.book.store.rest.repository.AuthorityRepository;
import com.example.book.store.rest.repository.BookRepository;
import com.example.book.store.rest.repository.UserRepository;
import com.example.book.store.rest.entity.Authority;
import com.example.book.store.rest.entity.Book;
import com.example.book.store.rest.entity.User;
import com.example.book.store.rest.response.SingleResponse;
import com.example.book.store.rest.service.AuthorityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class RestApplicationTests {



	@Test
	void contextLoads() {

	}


}
