package com.skilldistillery.jobtracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Board;
import com.skilldistillery.jobtracker.services.BoardService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private String username= "andrew"; // test username, will be replaced by Spring Security Principal object 
	
	@RequestMapping(path="boards", method=RequestMethod.GET)
	private List<Board> index(Principal principal){
		return boardService.getAllBoardsForUser(principal.getName());
	}
	
	@RequestMapping(path="boards/{bid}", method=RequestMethod.GET)
	private Board show(@PathVariable int bid, Principal principal){
		return boardService.getOneBoardForUser(principal.getName(), bid);
	}
	
	@RequestMapping(path="boards", method=RequestMethod.POST)
	private Board create(@RequestBody Board board, HttpServletResponse res, Principal principal){
		Board created = boardService.createBoard(principal.getName(), board);
		if(created == null) {
			res.setStatus(500);
		}
		else {
			res.setStatus(201);
		}
			
		return created;
	}
	
	@RequestMapping(path="boards/{bid}", method=RequestMethod.PATCH)
	private Board update(@RequestBody Board board, @PathVariable int bid, HttpServletResponse res, Principal principal){
		Board updated = boardService.updateBoard(principal.getName(), bid, board);
		if(updated == null) {
			res.setStatus(500);
		}
		else {
			res.setStatus(201);
		}
		
		return updated;
	}
	
	@RequestMapping(path="boards/{bid}", method=RequestMethod.DELETE)
	private Boolean destroy(@PathVariable int bid, Principal principal){
		return boardService.deleteBoard(principal.getName(), bid);
	}
}
