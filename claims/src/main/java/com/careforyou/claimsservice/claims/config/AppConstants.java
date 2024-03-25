package com.careforyou.claimsservice.claims.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConstants {

    public static final String TOPIC_POLICY_EVENTS = "policy-events";
    public static final String TOPIC_CLAIM_EVENTS = "claim-events";
    public static final String TOPIC_CUSTOMER_EVENTS = "customer-events";
}
