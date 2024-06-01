package com.example.member.framework.kafkaadapter;

import com.example.member.application.port.in.SavePointUsecase;
import com.example.member.application.port.in.UsePointUsecase;
import com.example.member.domain.event.ItemRented;
import com.example.member.domain.event.ItemSubmitted;
import com.example.member.domain.event.OverdueCleared;
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
public class MemberEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUsecase savePointUsecase;
    private final UsePointUsecase usePointUsecase;

    @KafkaListener(topics = "${consumer.topic-rent.name}", groupId = "${consumer.group-id.name}")
    public void consumeRentEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        savePointUsecase.savePoint(itemRented.getUser(), itemRented.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic-submit.name}", groupId = "${consumer.group-id.name}")
    public void consumeSubmitEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        ItemSubmitted itemSubmitted = objectMapper.readValue(record.value(), ItemSubmitted.class);
        savePointUsecase.savePoint(itemSubmitted.getUser(), itemSubmitted.getPoint());
    }

    @KafkaListener(topics = "${consumer.topic-overdue-clear.name}", groupId = "${consumer.group-id.name}")
    public void consumeOverdueClearEvent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("record = {}", record.value());
        OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);
        usePointUsecase.usePoint(overdueCleared.getUser(), overdueCleared.getPoint());
    }
}
