package com.example.rental.framework.kafkaadapter;

import com.example.rental.application.port.out.EventOutputPort;
import com.example.rental.domain.model.event.ItemRented;
import com.example.rental.domain.model.event.ItemSubmitted;
import com.example.rental.domain.model.event.OverdueCleared;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalKafkaProducer implements EventOutputPort {
    @Value("${producers.topic-rent.name}")
    private String TOPIC_RENT;
    @Value("${producers.topic-submit.name}")
    private String TOPIC_SUBMIT;
    @Value("${producers.topic-overdue-clear.name}")
    private String TOPIC_OVERDUE_CLEAR;
    private final KafkaTemplate<String, ItemRented> itemRentedKafkaTemplate;
    private final KafkaTemplate<String, ItemSubmitted> itemSubmittedKafkaTemplate;
    private final KafkaTemplate<String, OverdueCleared> overdueClearedKafkaTemplate;

    @Override
    public void publishRentalEvent(ItemRented itemRented) {
        itemRentedKafkaTemplate.send(TOPIC_RENT, itemRented)
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, ItemRented> result) {
                        log.info("Success to publish item rent event. offset = {}, itemRented = {}",
                                result.getRecordMetadata().offset(), result.getProducerRecord().value());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Fail to publish item rent event. itemRented = {}", itemRented);
                    }
                });
    }

    @Override
    public void publishSubmitEvent(ItemSubmitted itemSubmitted) {
        itemSubmittedKafkaTemplate.send(TOPIC_SUBMIT, itemSubmitted)
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, ItemSubmitted> result) {
                        log.info("Success to publish item submit event. offset = {}, itemSubmitted = {}",
                                result.getRecordMetadata().offset(), result.getProducerRecord().value());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Fail to publish item submit event. itemSubmitted = {}", itemSubmitted);
                    }
                });
    }

    @Override
    public void publishOverdueClearedEvent(OverdueCleared overdueCleared) {
        overdueClearedKafkaTemplate.send(TOPIC_OVERDUE_CLEAR, overdueCleared)
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, OverdueCleared> result) {
                        log.info("Success to publish overdue clear event. offset = {}, overdueCleared = {}",
                                result.getRecordMetadata().offset(), result.getProducerRecord().value());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Fail to publish overdue clear event. overdueCleared = {}", overdueCleared);
                    }
                });
    }
}
