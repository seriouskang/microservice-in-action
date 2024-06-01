package com.example.bestbook.presentation;

import com.example.bestbook.application.BestBookService;
import com.example.bestbook.domain.model.event.ItemRented;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BestBookEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BestBookService bestBookService;

    @KafkaListener(topics = "rent", groupId = "best-book")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        bestBookService.increaseBestBookCount(itemRented.getItem());
    }
}
