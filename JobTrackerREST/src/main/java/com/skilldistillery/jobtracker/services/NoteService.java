package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.Note;

public interface NoteService {
	List<Note> findNotesForJob(int jid);
	Note findNoteById(int noteId);
	Note create(Note note, int jid);
	Note update(Note note, int noteId);
	Boolean destroy(int noteId);
}
