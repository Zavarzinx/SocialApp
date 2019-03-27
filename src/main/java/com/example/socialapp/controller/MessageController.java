package com.example.socialapp.controller;

import com.example.socialapp.domain.Message;
import com.example.socialapp.domain.Views;
import com.example.socialapp.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }


    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        return messageRepo.save(message);
    }
    @PutMapping("{id}")
    public Message update(@PathVariable ("id") Message messageFromDB,@RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDB,"id");
        return messageRepo.save(messageFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
}
