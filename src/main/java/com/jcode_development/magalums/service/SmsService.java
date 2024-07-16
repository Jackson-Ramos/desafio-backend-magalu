package com.jcode_development.magalums.service;

import com.jcode_development.magalums.model.notification.Notification;
import com.jcode_development.magalums.model.status.Status;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Value("${spring.twilio.auth-token}")
    private String fromNumber;

    @Value("${spring.twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${spring.twilio.auth-token}")
    private String AUTH_TOKEN;

    public void sendSms(Notification notification) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            Message message = Message.creator(
                            new PhoneNumber(notification.getDestination()),
                            new PhoneNumber(fromNumber),
                            notification.getMessage())
                    .create();

            notification.setStatus(new Status(2L, "SUCCESS"));

            LOGGER.info("SMS sent");

        } catch (Exception e) {

            notification.setStatus(new Status(3L, "ERROR"));
            LOGGER.error(e.getMessage());

        }
    }
}
