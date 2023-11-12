package com.otece.devsullc2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otece.devsullc2.entity.MessageRecord;
import com.otece.devsullc2.repository.MessageRecordRepository;

@Service
public class MessageStorageServiceImpl {

    @Autowired
    private MessageRecordRepository repository;

    public void saveMessage(String content) {
        MessageRecord record = new MessageRecord();
        record.setContent(content);
        record.setProcessed(false);
        repository.save(record);
    }
    
    public MessageRecord findByContent(String content) {
    	MessageRecord opt = repository.findByContent(content);
    	return opt ;
    }
}
