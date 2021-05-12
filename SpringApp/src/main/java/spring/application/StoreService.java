package spring.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public List<Store> getAllStores() {
		// Returns all saved stores
		List<Store> stores = new ArrayList<>();
		storeRepository.findAll().forEach(t -> stores.add(t));
		return stores;
	}
	
	public List<Store> getAllMessagesOrderByName(List<Store> stores){
		stores.sort(Comparator.comparing(Store::getName));
		return stores;
	}

	public List<Store> getAllMessagesOrderByCount(List<Store> stores){
		stores.sort(Comparator.comparing(Store::getCount));
		return stores;
	}

	
	public List<Store> getAllMessagesOrderByMax(List<Store> stores){
		stores.sort(Comparator.comparing(Store::getNumberMax));
		return stores;
	}



	public List<Store> getStoresByName(String name) {
		// Returns the store named "name"
		List<Store> stores = new ArrayList<>();
		storeRepository.findAllByName(name).forEach(t -> stores.add(t));
		return stores;

	}
	
	public Store getStore(long id) {
		return storeRepository.findById(id).get();
	}

	public void addCount(String name, int number) {
		// Update the number of customers in the selected store
		List<Store> thisStore = this.getStoresByName(name);
		thisStore.forEach(t -> t.addCount(number));
		addStore(thisStore);
	}

	public void addStore(List<Store> store) {

		store.forEach(t -> storeRepository.save(t));

	}

	public void addStore(Store store) {

		storeRepository.save(store);

	}

	public void removeStore(Store store) {

		storeRepository.delete(store);

	}

	public void removeStore(List<Store> store) {

		store.forEach(t -> storeRepository.delete(t));

	}

	public void createAndAdd(String name) {
		// Create a store and save it directly
		Store store = new Store(name);
		addStore(store);
	}

	public void updateMaxValue(String name, int max) {
		List<Store> store = getStoresByName(name);
		store.forEach(t -> t.setNumberMax(max));
		addStore(store);
	}

	public void updateMaxValueByID(long id, int max) {
		Store store = storeRepository.findById(id).get();
		store.setNumberMax(max);
		addStore(store);
	}

}
