package com.example.book.framework.kafkaadapter;

import com.example.book.application.port.in.MakeAvailableUsecase;
import com.example.book.application.port.in.MakeUnAvailableUsecase;
import com.example.book.domain.model.event.ItemRented;
import com.example.book.domain.model.event.ItemSubmitted;
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
public class BookEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUsecase makeAvailableUsecase;
    private final MakeUnAvailableUsecase makeUnAvailableUsecase;

    @KafkaListener(topics = "${consumer.topic-rent.name}", groupId = "${consumer.group-id.name}")
    public void consumeRentalEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        makeUnAvailableUsecase.unavailable(itemRented.getItem().getItemId());
    }

    @KafkaListener(topics = "${consumer.topic-submit.name}", groupId = "${consumer.group-id.name}")
    public void consumeSubmitEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        ItemSubmitted itemSubmitted = objectMapper.readValue(record.value(), ItemSubmitted.class);
        makeAvailableUsecase.available(itemSubmitted.getItem().getItemId());
    }
}
