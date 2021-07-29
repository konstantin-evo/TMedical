package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.service.api.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageSender {

    private final ActiveMQConnectionFactory connectionFactory;
    private final MapperService mapper;
    private final Environment environment;

    public void sendMessage(List<TherapyCaseDto> standDto) {
        try {
            Connection connection = connectionFactory.createQueueConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            log.info("Session created > " + session.toString());
            Queue queue = session.createQueue(environment.getProperty("queue.name"));
            log.info("Queue created > " + queue.getQueueName());

            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            ActiveMQTextMessage mqTextMessage = (ActiveMQTextMessage) session.createTextMessage();
            String textMessage = mapper.converToJson(standDto);
            mqTextMessage.setText(textMessage);
            messageProducer.send(mqTextMessage);
            log.info("Message sent > " + textMessage);
            session.close();
            connection.close();
        }
        catch (Exception e) {
            log.error("Error sending message", e);
        }
    }

}
