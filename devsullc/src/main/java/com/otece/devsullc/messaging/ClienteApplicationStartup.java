package com.otece.devsullc.messaging;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //String endpointUrl = "http://localhost:8087/"; develop
    	String endpointUrl = "http://cliente:8087/"; // Production
        String path = "clientes/";
        rabbitTemplate.convertAndSend("cliente_exchange", "endpoint_info", endpointUrl + path, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
        
        System.out.println("Mensaje reenviado al iniciar la aplicación");
    }
}

