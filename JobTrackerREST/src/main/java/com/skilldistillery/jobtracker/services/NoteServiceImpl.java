package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Job;
import com.skilldistillery.jobtracker.entites.Note;
import com.skilldistillery.jobtracker.repositories.JobRepository;
import com.skilldistillery.jobtracker.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	private JobRepository jobRepo;
	
	@Override
	public List<Note> findNotesForJob(int jid) {
		return noteRepo.findByJobId(jid);
	}

	@Override
	public Note findNoteById(int noteId) {
		Optional<Note> optNote = noteRepo.findById(noteId);
		if(optNote.isPresent()) {
			Note note = optNote.get();
			return note;
		}
		return null;
	}

	@Override
	public Note create(Note note, int jid) {
		Optional<Job> optJob = jobRepo.findById(jid);
		if(optJob.isPresent()) {
			Job job = optJob.get();
			note.setJob(job);
		}
		return noteRepo.saveAndFlush(note);
	}

	@Override
	public Note update(Note note, int noteId) {
		Optional<Note> optNote = noteRepo.findById(noteId);
		if(optNote.isPresent()) {
			Note managed = optNote.get();
			managed.setContent(note.getContent());
			return noteRepo.saveAndFlush(managed);
		}
		return null;
	}

	@Override
	public Boolean destroy(int noteId) {
		Optional<Note> optNote = noteRepo.findById(noteId);
		if(optNote.isPresent()) {
			Note note = optNote.get();
			noteRepo.delete(note);
			return true;
		}
		return false;
	}

}
