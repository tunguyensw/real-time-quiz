package com.elsaspeak.quiz.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuizEventHandler {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.quiz-submitted-topic}")
    private String topic;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public void publish(Object event) {
        // kafkaTemplate.send(topic, event);
        log.info("Send event: {}", event);
    }

    // test function
//    @KafkaListener(topics = "test", groupId = "${spring.kafka.consumer.group-id}")
//    public void listen(String message) {
//        System.out.println("Received Message in group foo: " + message);
//    }
}
