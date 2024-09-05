package com.miniProject.OlShop.config;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_INVENTORY = "purchase_exchange";
    public static final String ROUTING_INVENTORY = "inventory_routing_key";


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_INVENTORY);
    }

    // Menggunakan Jackson untuk konversi JSON
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Konfigurasi RabbitTemplate untuk menggunakan Jackson2JsonMessageConverter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }
    
    
    
    
}

