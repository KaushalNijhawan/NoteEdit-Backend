package com.example.notepad.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import com.example.notepad.model.ErrorMessage;
import com.example.notepad.model.Text;
import com.mongodb.client.MongoClients;

public class NotepadDAO {
	
	
	public Text saveURLNote(Text text) {
		System.out.println(text.getTextContent() + "    " + text.getTextURL());
		MongoOperations mongoOps = getConnection();
		text.setPassword("");
		text.setIsloggedIn(false);
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(text.getTextURL()));
		if(!text.getTextContent().equals("")) {
			if(mongoOps.exists(query, Text.class, "noteEdit")) {
				Text textObj = mongoOps.findOne(query,Text.class ,"noteEdit");
				Update update = new Update();
				update.set("textContent" ,text.getTextContent());
				mongoOps.updateMulti(query, update, Text.class,"noteEdit");
				mongoOps.findAndModify(query, update, Text.class);
				
		    }else {
		    	
		    	mongoOps.insert(text, "noteEdit");
		    }
		}
		
		return text;
	}
	
	public String deleteAllnotes() {
		MongoOperations mongoOps = getConnection();
		mongoOps.dropCollection("noteEdit");
		mongoOps.dropCollection("passwordSet");
		return "Every Note is Deleted!";
	}
	
	public Text uniqueURLText(String URLNote) {
		System.out.println("got a new cover!!");
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(URLNote));
		Query query2 = new Query();
		//if(mongoOps.exists(query, Text.class, "noteEdit") ) {
			Text textObj = mongoOps.findOne(query, Text.class, "noteEdit");
			return textObj;
		//}
		
		
	}
	

	public Text changeContent(Text text) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textContent").is(text.getTextContent()));
		Text textObj = mongoOps.findOne(query,Text.class ,"noteEdit");
		Update update = new Update();
		update.set("textURL" ,text.getTextURL());
		mongoOps.updateMulti(query, update, Text.class,"noteEdit");
		mongoOps.findAndModify(query, update, Text.class);
		return text;
	}

	public String addPasswordToNote(String uRLNote, String passwordNote) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(uRLNote));
		Text textObj = mongoOps.findOne(query,Text.class,"noteEdit");
		Update update = new Update();
		update.set("password", passwordNote);
		Update update2 = new Update();
		update2.set("isloggedIn", false);
		mongoOps.updateMulti(query, update, Text.class,"noteEdit");
		mongoOps.findAndModify(query, update,Text.class);
		mongoOps.updateMulti(query, update2, Text.class, "noteEdit");
		mongoOps.findAndModify(query, update2, Text.class);
		return "String with URL " + uRLNote + " updated";
		
	}
	
	public ErrorMessage validPassword(String uRLNote, String passwordNote) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(uRLNote));
		Text textObj = mongoOps.findOne(query,Text.class,"noteEdit");
		if(mongoOps.exists(query, Text.class, "noteEdit") && textObj.getPassword().equalsIgnoreCase(passwordNote)) {
			textObj.setIsloggedIn(true);
			Update update = new Update();
			update.set("isloggedIn", true);
			mongoOps.updateMulti(query, update, Text.class,"noteEdit");
			return new ErrorMessage("Password Matched!", uRLNote) ;
		}else {
			textObj.setIsloggedIn(false);
			Update update = new Update();
			update.set("isloggedIn", false);
			mongoOps.updateMulti(query, update, Text.class,"noteEdit");
			mongoOps.findAndModify(query, update,Text.class);
			return new ErrorMessage("Password doesnot matched!",uRLNote);
		}
		
	}
	
	public String updateNoteURL(String oldURL, String updatedURL) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(oldURL));
		Update update = new Update();
		update.set("textURL", updatedURL);
		mongoOps.updateMulti(query, update, Text.class,"noteEdit");
		mongoOps.findAndModify(query, update,Text.class);
		return "URL updated!";
	}
	
	public String removePasswordForURL(String textURL) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(textURL));
		Update update = new Update();
		update.set("password", "");
		Update update2 = new Update();
		update2.set("isloggedIn", true);
		mongoOps.updateMulti(query, update, Text.class, "noteEdit");
		mongoOps.findAndModify(query, update, Text.class);
		mongoOps.updateMulti(query, update2, Text.class, "noteEdit");
		mongoOps.findAndModify(query, update2, Text.class);
		return "Password Removed!";
	}
	
	public String removeLoggedStatus(String textURL) {
		MongoOperations mongoOps = getConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("textURL").is(textURL));
		Update update = new Update();
		update.set("isloggedIn", false);
		mongoOps.updateMulti(query, update, Text.class, "noteEdit");
		mongoOps.findAndModify(query, update, Text.class);
		return "Logged Status Updated!!";
	}	
	
	
	private MongoOperations getConnection() {
		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "NoteEdit");
		return mongoOps;
	}

	

	

	

	

	
}
