package pis.projekt.services;

import org.apache.http.RequestLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

import java.io.IOException;
import java.security.Principal;

public class ElasticConnector {

    final static Credentials credentials = new UsernamePasswordCredentials("elastic", "TR9S8Q4HHFjUCySyU0Ru4I0D");
    final static CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    static String cloudId = "magazyn:ZXVyb3BlLXdlc3QzLmdjcC5jbG91ZC5lcy5pbzo0NDMkMThiMGZhNmNmMDA1NDY0YWI0NTU1Yjc3NjBiZGJhZjIkMzllZGViOTgxNTAwNGViN2EzYTJlNTQ1MTVjOWUyNmM=";

    public static void main(String[] args){
        //RestClient restClient = RestClient.builder(
        //        new HttpHost("localhost", 9200, "http")).build();

        credentialsProvider.setCredentials(AuthScope.ANY, credentials);


        RestClientBuilder builder = RestClient.builder(cloudId)
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestClient client = builder.build();

        try{
            Request request = new Request(
                    "GET",
                    "/zlecenia/_count");
            request.addParameter("pretty", "true");
            Response response = client.performRequest(request);

            RequestLine requestLine = response.getRequestLine();
            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
            client.close();
        } catch (IOException e){
            e.printStackTrace();
        }


//    SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("tesla_employees");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequest.source(searchSourceBuilder);
//        Map<String, Object> map=null;
//
//        try {
//            SearchResponse searchResponse = null;
//            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
//            if (searchResponse.getHits().getTotalHits().value > 0) {
//                SearchHit[] searchHit = searchResponse.getHits().getHits();
//                for (SearchHit hit : searchHit) {
//                    map = hit.getSourceAsMap();
//                    System.out.println("map:"+ Arrays.toString(map.entrySet().toArray()));
//
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
