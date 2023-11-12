package com.otece.devsullc2.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otece.devsullc2.config.RestTemplateConfig;
import com.otece.devsullc2.dto.ClientDTO;
import com.otece.devsullc2.exception.AccountException;

@Service
public class ClienteStorageServiceImpl {
	
	@Autowired
    private RestTemplateConfig restTemplate;
	
	private String clientEndpoint;

    public void endPointClientData(String endPoint) {
        this.clientEndpoint = endPoint;
    }

    public ClientDTO getClientInfo(String clientId) throws AccountException {
       try {
    	   ClientDTO client = restTemplate.restTemplate().getForObject(this.clientEndpoint + clientId, ClientDTO.class);
    	   return client;
       } catch (Exception e) {
          throw new AccountException(" receiveClientInfo: " + e.getMessage());
       }
    }
}
