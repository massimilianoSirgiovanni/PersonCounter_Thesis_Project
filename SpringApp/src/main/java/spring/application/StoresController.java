package spring.application;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoresController {

	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/stores")
	public String getStores(Model model) {
		model.addAttribute("stores",  storeService.getAllStores());
		return "stores";
	}
	
	@RequestMapping("/stores/OrderByName")
	public String getMessagesByName(Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByName(storeService.getAllStores()));
		return "stores";
	}
	
	@RequestMapping("/stores/OrderByCount")
	public String getMessagesByCount(Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByCount(storeService.getAllStores()));
		return "stores";
	}
	
	@RequestMapping("/stores/OrderByMax")
	public String getMessagesByMax(Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByMax(storeService.getAllStores()));
		return "stores";
	}
	
	@RequestMapping("/selectStores")
	public String getSelectStores() {
		return "selectStores";
	}
	
	@RequestMapping("/stores/{name}")
	public String selectStoresByName(@PathVariable String name, Model model) {
		model.addAttribute("stores",  storeService.getStoresByName(name));
		model.addAttribute("name", name);
		return "storesSelected";
	}
	
	@RequestMapping("/stores/{name}/OrderByName")
	public String selectMessagesOrderByName(@PathVariable String name, Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByName(storeService.getStoresByName(name)));
		model.addAttribute("name", name);
		return "storesSelected";
	}
	
	@RequestMapping("/stores/{name}/OrderByCount")
	public String selectMessagesOrderByCount(@PathVariable String name, Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByCount(storeService.getStoresByName(name)));
		model.addAttribute("name", name);
		return "storesSelected";
	}
	
	@RequestMapping("/stores/{name}/OrderByMax")
	public String selectMessagesOrderByMax(@PathVariable String name, Model model) {
		model.addAttribute("stores",  storeService.getAllMessagesOrderByMax(storeService.getStoresByName(name)));
		model.addAttribute("name", name);
		return "storesSelected";
	}
	
	@RequestMapping("/store/{id}")
	public String getStoreByID(@PathVariable long id, Model model) {
		model.addAttribute("stores",  storeService.getStore(id));
		return "store";
	}
	
	@RequestMapping("/addStore")
	public String addStore() {
		return "addStore";
	}
	
	@RequestMapping("/addStore/{name}/setmax/{max}")
	public String storeAdded(@PathVariable String name, @PathVariable int max) {
		Store store = new Store(name, max);
		storeService.addStore(store);
		return "storeAdded";
	}
	
	@RequestMapping("/removeStore")
	public String removeStore() {
		return "removeStore";
	}
	
	@RequestMapping("/removeStores/{name}")
	public String removeStore(@PathVariable String name) {
		List<Store> storeToDelete = storeService.getStoresByName(name);
		if(storeToDelete.isEmpty()) {
			return "removeError";
		}else {
		storeService.removeStore(storeToDelete);
		}
		return "storeRemoved";
	}

	@RequestMapping("/removeStore/{id}")
	public String removeStoreByID(@PathVariable long id) {
		Store storeToDelete = storeService.getStore(id);
		if(storeToDelete == null) {
			return "removeError";
		}else {
		storeService.removeStore(storeToDelete);
		}
		return "storeRemoved";
	}
	
	@RequestMapping("/setMax")
	public String setMax() {
		return "setMaxInput";
	}
	
	@RequestMapping("/setMax/{name}/byName/value/{max}")
	public String setStoresMax(@PathVariable String name, @PathVariable int max) {
		List<Store> storeToDelete = storeService.getStoresByName(name);
		if(storeToDelete.isEmpty()) {
			return "setMaxError";
		}else {
		storeService.updateMaxValue(name, max);
		return "setMaxUpdated";
		}
	}

	@RequestMapping("/setMax/{id}/byID/value/{max}")
	public String setStoreMax(@PathVariable long id, @PathVariable int max) {
		Store storeToDelete = storeService.getStore(id);
		if(storeToDelete == null) {
			return "setMaxError";
		}else {
		storeService.updateMaxValueByID(id, max);
		return "setMaxUpdated";
		}
		
	}
	
	@RequestMapping("/realTime/{id}")
	public String Realtime(@PathVariable long id, Model model) {
		Store store = storeService.getStore(id);
		model.addAttribute("store",  store);
		model.addAttribute("ID", id);
		
		model.addAttribute("max", store.getNumberMax());
		model.addAttribute("initialCount", store.getCount());
		return "realTime";
	}
	
}
