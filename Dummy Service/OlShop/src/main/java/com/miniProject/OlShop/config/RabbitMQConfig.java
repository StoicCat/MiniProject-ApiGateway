package com.miniProject.OlShop.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nama Exchange dan Queue
    public static final String EXCHANGE_INVENTORY = "purchase_exchange";
    public static final String QUEUE_INVENTORY= "inventory_queue";
    public static final String ROUTING_INVENTORY = "inventory_routing_key";

    // Deklarasi Exchange (Direct Exchange)
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_INVENTORY);
    }

    // Deklarasi Queue
    @Bean
    public Queue purchaseQueue() {
        return new Queue(QUEUE_INVENTORY, true);  // true untuk membuat queue durable
    }

    // Binding antara Queue dan Exchange dengan Routing Key
    @Bean
    public Binding binding(Queue purchaseQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(purchaseQueue).to(directExchange).with(ROUTING_INVENTORY);
    }
}
