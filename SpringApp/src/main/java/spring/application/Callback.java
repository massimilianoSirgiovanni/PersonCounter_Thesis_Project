package spring.application;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Callback implements MqttCallback {

	@Autowired
	private MqttService mqtt;

	public Callback() {

	}

//////////////Methods implemented by the MtqqCallback interface currently unused/////////////////////
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	public void messageArrived(String topic, MqttMessage message) throws Exception {

		String storeName = elaborateStore(topic);
		elaborateMessage(topic, message, storeName); // The content of the message is processed and used
		mqtt.storeMessages(topic, message.toString(), storeName);
		System.out.println("MESSAGGIO RICEVUTO: " + message + " - CON IL SEGUENTE TOPIC: " + topic);
	}

	public void elaborateMessage(String topic, MqttMessage message, String storeName) {

		// The content of the message is processed and used

		topic = elaborateTopic(topic);
		if (topic.compareTo("enter") == 0) {
			mqtt.messageEnterStore(storeName, message.toString());

		} else if (topic.compareTo("exit") == 0) {
			mqtt.messageExitStore(storeName, message.toString());

		}

	}

	public String elaborateTopic(String topic) {

		String finalTopic = "";

		for (int i = topic.length() - 1; i >= 0; i--) {
			if (topic.charAt(i) != '/') {

				finalTopic = topic.charAt(i) + finalTopic;
			} else {

				return finalTopic;

			}

		}

		return finalTopic;
	}

	public String elaborateStore(String topic) {
		// The name of the store to send the message to is extracted from the topic

		String finalStore = "";

		boolean second = false;

		for (int i = topic.length() - 1; i >= 0; i--) {
			if (topic.charAt(i) != '/' && second == true) {

				finalStore = topic.charAt(i) + finalStore;
			} else if (topic.charAt(i) == '/' && second == true) {

				return finalStore;

			} else if (topic.charAt(i) == '/') {
				second = true;
			}

		}

		return finalStore;

	}

}
