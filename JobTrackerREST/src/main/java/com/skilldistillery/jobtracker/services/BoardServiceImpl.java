package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Board;
import com.skilldistillery.jobtracker.entites.User;
import com.skilldistillery.jobtracker.repositories.BoardRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Board> getAllBoardsForUser(String username) {
		return boardRepo.findByUserUsername(username);
	}

	@Override
	public Board getOneBoardForUser(String username, int id) {
		return boardRepo.findByUserUsernameAndId(username, id);
	}

	@Override
	public Board createBoard(String username, Board board) {
		User managedUser = userRepo.findByUsername(username);
		board.setUser(managedUser);
		return boardRepo.saveAndFlush(board);
	}

	@Override
	public Board updateBoard(String username, int boardId, Board board) {
		Optional<Board> optBoard = boardRepo.findById(boardId);
		if(optBoard.isPresent()) {
			Board managed = optBoard.get();
			if(managed.getUser().getUsername().equals(username)) {
				if(board.getTitle() != "" ) {
					managed.setTitle(board.getTitle());
				}
				if(board.getDescription() != "" ) {
					managed.setDescription(board.getDescription());
				}
				return boardRepo.saveAndFlush(managed);
			}
		}
		
		return null;
	}

	@Override
	public Boolean deleteBoard(String username, int boardId) {
		Optional<Board> optBoard = boardRepo.findById(boardId);
		if(optBoard.isPresent()) {
			Board managedBoard = optBoard.get();
			if(managedBoard.getUser().getUsername().equals(username)) {
				boardRepo.delete(managedBoard);
				return true;
			}
		}
		return false;
	}
	
}
