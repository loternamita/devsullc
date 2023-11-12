package com.otece.devsullc2.messaging;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.otece.devsullc2.entity.MessageRecord;
import com.otece.devsullc2.exception.AccountException;
import com.otece.devsullc2.service.impl.ClienteStorageServiceImpl;
import com.otece.devsullc2.service.impl.MessageStorageServiceImpl;
import com.rabbitmq.client.Channel;

@Component
public class ClienteInfoReceiver {
	
	@Autowired
    private ClienteStorageServiceImpl clienteStorageServiceImpl;
	@Autowired
    private MessageStorageServiceImpl messageStorageService;

	@RabbitListener(queues = "cuenta_queue", ackMode = "MANUAL")
    public void receiveClientInfo(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws AccountException {
		try {
			MessageRecord isSaveMessage = messageStorageService.findByContent(message);
			
			if (isSaveMessage == null) {
			   messageStorageService.saveMessage(message);
			}
			
			clienteStorageServiceImpl.endPointClientData(message);
			channel.basicAck(tag, false);
			
		} catch (Exception e) {
			try {
	            channel.basicNack(tag, false, true); // Rechazar y reencolar si hay error
	        } catch (IOException e1) {
	            throw new AccountException("Se presentó un error en basicNack: " + e1.getMessage());
	        }
			throw new AccountException("Se presento un error: " + e.getMessage());
		}
    }
}
