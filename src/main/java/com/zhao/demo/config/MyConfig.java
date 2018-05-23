package com.zhao.demo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MyConfig {
    @Bean
    public TransportClient client() throws UnknownHostException{
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("192.168.154.21"), 9300);

        Settings settings = Settings.builder().put("cluster.name", "zhao").build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
