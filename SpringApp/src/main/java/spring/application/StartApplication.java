package spring.application;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StartApplication {
	
	public static MqttAsyncClient myClient;

	@Autowired
	private Callback myCallback;
	

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
  
  @EventListener(ApplicationReadyEvent.class)
	public void startMqttClient() throws MqttException {

		// Operations for connecting the client to the mqtt broker

		myClient = new MqttAsyncClient("tcp://192.168.0.223:1883", UUID.randomUUID().toString()); // A client is created

		myClient.setCallback(myCallback); // The callback is assigned to the client
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setAutomaticReconnect(true);
		mqttConnectOptions.setCleanSession(false);

		IMqttToken token = myClient.connect(mqttConnectOptions);
		token.waitForCompletion();

		myClient.subscribe("sensors/+/+", 2); // The customer subscribes to the requested topics
	}
  
}


