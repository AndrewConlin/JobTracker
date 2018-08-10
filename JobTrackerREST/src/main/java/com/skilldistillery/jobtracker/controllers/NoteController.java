package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Note;
import com.skilldistillery.jobtracker.services.NoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@RequestMapping(path="jobs/{jid}/notes", method=RequestMethod.GET)
	public List<Note> indexByJob(@PathVariable int jid){
		return noteService.findNotesForJob(jid);
	}
	
	@RequestMapping(path="jobs/{jid}/notes/{noteId}", method=RequestMethod.GET)
	public Note show(@PathVariable int noteId){
		return noteService.findNoteById(noteId);
	}
	
	@RequestMapping(path="jobs/{jid}/notes", method=RequestMethod.POST)
	public Note create(@PathVariable int jid, @RequestBody Note note){
		return noteService.create(note, jid);
	}
	
	@RequestMapping(path="jobs/{jid}/notes/{noteId}", method=RequestMethod.PATCH)
	public Note update(@PathVariable int noteId,  @RequestBody Note note){
		return noteService.update(note, noteId);
	}
	
	
	@RequestMapping(path="job/{jid}/notes/{noteId}", method=RequestMethod.DELETE)
	public Boolean update(@PathVariable int noteId){
		return noteService.destroy(noteId);
	}
	
	
	
}
