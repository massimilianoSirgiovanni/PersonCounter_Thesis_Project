package spring.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessagesController {

	@Autowired
	private MqttService messageService;
	
	@RequestMapping("/deleteMessages")
	public String warningDeliteMessage() {
		return "warningDeleteMessages";
	}
	
	@RequestMapping("/messagesDeleted")
	public String removeAllMessages() {
		messageService.cleanMessageDatabase();
		CreateID.resetID();
		return "messagesDeleted";
	}
	
	@RequestMapping("/messages")
	public String getMessages(Model model) {
		model.addAttribute("messages",  messageService.getAllMessages());
		return "messages";
	}
	
	@RequestMapping("/messages/OrderByTopic")
	public String getMessagesByTopic(Model model) {
		model.addAttribute("messages",  messageService.getAllMessagesOrderByTopic(messageService.getAllMessages()));
		return "messages";
	}
	
	@RequestMapping("/messages/OrderByContent")
	public String getMessagesByMessageContent(Model model) {
		model.addAttribute("messages",  messageService.getAllMessagesOrderByMessage(messageService.getAllMessages()));
		return "messages";
	}
	
	@RequestMapping("/messages/OrderByStore")
	public String getMessagesByStore(Model model) {
		model.addAttribute("messages",  messageService.getAllMessagesOrderByStore(messageService.getAllMessages()));
		return "messages";
	}
	
	@RequestMapping("/messages/OrderByDate")
	public String getMessagesByDate(Model model) {
		model.addAttribute("messages",  messageService.getAllMessagesOrderByDate(messageService.getAllMessages()));
		return "messages";
	}
	
	@RequestMapping("/selectMessages")
	public String selectMessages() {
		return "messagesByStore";
	}
	
	@RequestMapping("/selectMessages/{store}")
	public String selectMessagesByStore(@PathVariable String store, Model model) {
		
		model.addAttribute("messages",  messageService.getAllMessagesByStore(store));
		model.addAttribute("store", store);
		return "messagesSelected";
	}
	
	@RequestMapping("/selectMessages/{store}/OrderByTopic")
	public String selectMessagesOrderByTopic(@PathVariable String store, Model model) {
		
		model.addAttribute("messages", messageService.getAllMessagesOrderByTopic(messageService.getAllMessagesByStore(store)));
		model.addAttribute("store", store);
		return "messagesSelected";
	}
	
	@RequestMapping("/selectMessages/{store}/OrderByContent")
	public String selectMessagesOrderByMessageContent(@PathVariable String store, Model model) {
		
		model.addAttribute("messages",  messageService.getAllMessagesOrderByTopic(messageService.getAllMessagesByStore(store)));
		model.addAttribute("store", store);
		return "messagesSelected";
	}
	
	@RequestMapping("/selectMessages/{store}/OrderByStore")
	public String selectMessagesOrderByStore(@PathVariable String store, Model model) {
		
		model.addAttribute("messages",  messageService.getAllMessagesOrderByTopic(messageService.getAllMessagesByStore(store)));
		model.addAttribute("store", store);
		return "messagesSelected";
	}
	
	@RequestMapping("/selectMessages/{store}/OrderByDate")
	public String selectMessagesOrderByDate(@PathVariable String store, Model model) {
		
		model.addAttribute("messages",  messageService.getAllMessagesOrderByTopic(messageService.getAllMessagesByStore(store)));
		model.addAttribute("store", store);
		return "messagesSelected";
	}
	
	
	
}
