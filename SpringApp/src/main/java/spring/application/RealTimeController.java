package spring.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RealTimeController {

	@Autowired
	private StoreService storeService;

  @MessageMapping("/visualStore/{id}")
  @SendTo("/realtime/count")
  public StoreCount realTimeStore(@DestinationVariable long id) throws Exception {
    Store store = storeService.getStore(id);
    return new StoreCount("" + store.getCount());
  }

}