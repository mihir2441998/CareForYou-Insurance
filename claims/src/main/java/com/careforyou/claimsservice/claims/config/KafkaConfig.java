package com.careforyou.claimsservice.claims.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic policyEvent() {
        return TopicBuilder.name(AppConstants.TOPIC_POLICY_EVENTS)
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic claimEvent() {
        return TopicBuilder.name(AppConstants.TOPIC_CLAIM_EVENTS)
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic customerEvent() {
        return TopicBuilder.name(AppConstants.TOPIC_CUSTOMER_EVENTS)
                .partitions(3)
                .compact()
                .build();
    }
}
