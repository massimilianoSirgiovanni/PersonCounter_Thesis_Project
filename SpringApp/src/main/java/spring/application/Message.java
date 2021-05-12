package spring.application;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

	// This class allows you to keep both the MQTT message and the message topic in
	// a single object

	@Id
	private String id = CreateID.createID();
	private String topic;
	private String message;
	private String store;
	Date creationDate;

	public Message() {

	}

	public Message(String topic, String message, String store) {

		this.topic = topic;

		this.message = message;

		this.store = store;

		this.creationDate = new Date(); // sets to the current date/time

	}

	///////// Getter and Setters//////////////////////////

	public String getTopic() {
		return topic;
	}

	public String getMessage() {
		return message;
	}

	public String getId() {
		return id;
	}

	public String getStore() {
		return store;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}