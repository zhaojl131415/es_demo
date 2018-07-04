//package com.zhao.demo.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.zhao.demo.repository")
//public class MyConfig {
//
//    @Bean
//    public TransportClient client() throws UnknownHostException {
//        Settings settings = Settings.builder().put("cluster.name", "zhao").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.154.21"), 9300));
//        return client;
//    }
//
//
//}
