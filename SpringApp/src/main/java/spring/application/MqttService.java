package spring.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

	// This class links the mqtt client with the items in the repository

	@Autowired
	private StoreService stores;

	@Autowired
	private MessageRepository messages;

	public void messageEnterStore(String storeName, String message) {
		stores.addCount(storeName, Integer.parseInt(message));
		
	}

	public void messageExitStore(String storeName, String message) {

		stores.addCount(storeName, -Integer.parseInt(message));
	}

	public void storeMessages(String topic, String message, String store) {

		Message toStore = new Message(topic, message, store);
		messages.save(toStore);

	}

	public List<Message> getAllMessages() {
		List<Message> toStoreMessages = new ArrayList<>();
		messages.findAll().forEach(t -> toStoreMessages.add(t));
		return toStoreMessages;
	}
	
	public List<Message> getAllMessagesOrderByTopic(List<Message> messages){
		messages.sort(Comparator.comparing(Message::getTopic));
		return messages;
	}

	public List<Message> getAllMessagesOrderByMessage(List<Message> messages){
		messages.sort(Comparator.comparing(Message::getMessage));
		return messages;
	}

	
	public List<Message> getAllMessagesOrderByStore(List<Message> messages){
		messages.sort(Comparator.comparing(Message::getStore));
		return messages;
	}
	
	public List<Message> getAllMessagesOrderByDate(List<Message> messages){
		messages.sort(Comparator.comparing(Message::getCreationDate));
		return messages;
	}


	public void cleanMessageDatabase() {
		messages.deleteAll();
	}

	public List<Message> getAllMessagesByStore(String store) {
		List<Message> toStoreMessages = new ArrayList<>();
		messages.findAllByStore(store).forEach(t -> toStoreMessages.add(t));
		return toStoreMessages;
	}
	

}
