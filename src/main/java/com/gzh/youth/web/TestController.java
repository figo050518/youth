package com.gzh.youth.web;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzh.youth.entity.User;
import com.gzh.youth.exception.MyException;
import com.gzh.youth.repository.UserRepository;
import com.gzh.youth.service.UserService;


@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class TestController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	@RequestMapping("/")
	 String home() {
	 return "Hello World!";
	 }
	
	@RequestMapping("/addUser")
	 String addUser() {
		User u = new User();
		u.setCreateDate(new Date());
		u.setName("张三");
		userRepo.save(u);
		return "Hello World!";
	 }
	@RequestMapping("/userPage")
	public Object getUserPage(){
			Sort sort = new Sort(Sort.Direction.DESC, "id");  
	       Pageable pageable = new PageRequest(0, 10, sort);  
	       Page<User> pages=userService.findPage("张",pageable);  
	       Iterator<User> it=pages.iterator();  
	       while(it.hasNext()){  
	           System.out.println("value:"+((User)it.next()).getId());  
	       }
	       return pages;
	}
	@RequestMapping("/testSql")
	public User sql(){
		User user = userService.findByName("张");
	       return user;
	}
	@RequestMapping("/testError")
	 public String testError() throws MyException {
			throw new MyException(MyException.TEST_ERROR,MyException.getMessage(MyException.TEST_ERROR));
	 }
	@RequestMapping("/testError2")
	 public String testError2() throws Exception {
			throw new Exception();
	 }
}
