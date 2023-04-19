//package com.example.Backend.elasticsearch.configuration;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.beans.factory.annotation.Value;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.example.Backend.repository.elasticSearch")
//public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Value("${spring.elasticsearch.host}")
//    private String elasticsearchHost;
//
//    @Value("${spring.elasticsearch.port}")
//    private int elasticsearchPort;
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(elasticsearchHost + ":" + elasticsearchPort)
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchOperations() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//
//    @Bean
//    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//}