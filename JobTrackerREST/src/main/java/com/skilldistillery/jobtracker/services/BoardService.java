package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.Board;

public interface BoardService {
	List<Board> getAllBoardsForUser(String username);
	Board getOneBoardForUser(String username, int id);
	Board createBoard(String username, Board board);
	Board updateBoard(String username, int boardId, Board board);
	Boolean deleteBoard(String usrename, int boardId);
}
