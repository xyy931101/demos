package com.examplexyy.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

/**
 * ES连接的工具类
 * @author: xiongyayun
 * @date: 2022/2/19 13:16
 */
public class EsClientBuilder {

    private static String HOSTS = "106.52.223.163:9200";

    public static void main(String[] args) {

    }

    public static RestClientBuilder getClientBuilder(){
        String[] hosts = HOSTS.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i <hosts.length; i++) {
            String[] ho = hosts[i].split(":");
            httpHosts[i] = new HttpHost(ho[0], Integer.parseInt(ho[1]), "http");
        }
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts);
        return restClientBuilder;
    }

}
