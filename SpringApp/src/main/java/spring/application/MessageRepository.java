package spring.application;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
	
	List<Message> findAllByStore(String store);

}
