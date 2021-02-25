package com.example.notepad.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notepad.dao.NotepadDAO;
import com.example.notepad.model.ErrorMessage;
import com.example.notepad.model.Text;

@CrossOrigin(origins="*")
@RestController
@SpringBootApplication()
public class DemoApplication {
	
	NotepadDAO notepadDao = new NotepadDAO();
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping("/")
	public String homeRoute() {
		return "Notepad Home !";
	}
	
	@PostMapping("/saveNote")
	public Text saveNote(@RequestBody Text text) {
		return notepadDao.saveURLNote(text);
	}
	
	@RequestMapping("/deleteNotes")
	public String deleteNotes() {
		return notepadDao.deleteAllnotes();
	}
	
	@RequestMapping("/getRequiredNote/{uniqueURL}")
	public Text getUniqueURLText(@PathVariable("uniqueURL") String URLNote) {
		return notepadDao.uniqueURLText(URLNote);
	}
	
	@PostMapping("/changeURL")
	public Text changeURL(@RequestBody Text text) {
		return notepadDao.changeContent(text);
	}
	
	@RequestMapping("/addPassword/{uniqueURL}")
	public String addPassword(@PathVariable("uniqueURL") String URLNote , @RequestHeader("password") String passwordNote ) {
		return notepadDao.addPasswordToNote(URLNote, passwordNote);
	}
	
	@RequestMapping("/validatePassword/{uniqueURL}")
	public ErrorMessage validatePassword(@PathVariable("uniqueURL") String URLNote, @RequestHeader("password") String passwordNote) {
		return notepadDao.validPassword(URLNote, passwordNote);
	}
	
	@RequestMapping("/updateURL/{uniqueURL}")
	public String updateNoteURL(@PathVariable("uniqueURL") String oldURL, @RequestHeader("updatedURL") String updatedURL) {
		return notepadDao.updateNoteURL(oldURL, updatedURL);
	}
	
	@RequestMapping("/removeNotePassword/{uniqueURL}")
	public String removePasswordURL(@PathVariable("uniqueURL") String textURL) {
		return notepadDao.removePasswordForURL(textURL);
	}
	
	@RequestMapping("/changeLoggedIn/{uniqueURL}")
	public String changeLoggedInStatus(@PathVariable("uniqueURL") String textURL) {
		return notepadDao.removeLoggedStatus(textURL);
	}
}
