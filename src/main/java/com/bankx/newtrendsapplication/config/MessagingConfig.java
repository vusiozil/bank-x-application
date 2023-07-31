package com.bankx.newtrendsapplication.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class MessagingConfig {

  @Value("${activemq.broker.url}")
  String brokerUrl;

  @Bean
  public BrokerService broker() throws Exception {

    BrokerService broker = new BrokerService();
    broker.setPersistent(false);
    broker.setUseJmx(true);
    broker.addConnector(brokerUrl);
    return broker;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(new PooledConnectionFactory(brokerUrl));
    return factory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(new PooledConnectionFactory(brokerUrl));
  }

}
