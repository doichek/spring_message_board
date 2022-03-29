package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageMapper;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> findAll() {
        return messageMapper.findAll();
    }

    @Override
    public Optional<Message> getMessage(int id) {

        if(messageMapper.getMessage(id).isEmpty()) {
            throw new MessageNotFoundException("該当のメッセージはありません");
        }

        return messageMapper.getMessage(id);
    }

    @Override
    public void insert(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void update(Message message) {
        messageMapper.update(message);
    }

    @Override
    public void delete(int id) {
        messageMapper.delete(id);
    }

}
