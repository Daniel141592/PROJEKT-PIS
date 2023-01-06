package pis.projekt.services;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.json.JSONObject;
import java.io.IOException;

public class ElasticConnector {

    final static Credentials credentials = new UsernamePasswordCredentials("elastic", "TR9S8Q4HHFjUCySyU0Ru4I0D");
    final static CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    static String cloudId = "magazyn:ZXVyb3BlLXdlc3QzLmdjcC5jbG91ZC5lcy5pbzo0NDMkMThiMGZhNmNmMDA1NDY0YWI0NTU1Yjc3NjBiZGJhZjIkMzllZGViOTgxNTAwNGViN2EzYTJlNTQ1MTVjOWUyNmM=";

    public static void setCredentials(){
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
    }

    public static RestClient getRestClient(){
        RestClientBuilder builder = RestClient.builder(cloudId)
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestClient client = builder.build();

        return client;
    }

    public static JSONObject elasticSearch(String search){
        JSONObject result = null;
        setCredentials();
        RestClient client = getRestClient();

        String searchQuery = "{\"query\":{\"multi_match\":{\"query\":\"" + search + "\", \"fuzziness\":\"AUTO\"}}}";

        try{
            Request request = new Request(
                    "GET",
                    "/_search");
            request.addParameter("pretty", "true");
            request.addParameter("format", "json");
            request.setJsonEntity(searchQuery);
            Response response = client.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            result = new JSONObject(responseBody);

            // System.out.println(responseBody);
            client.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public static JSONObject elasticSearch(String index, String search){
        JSONObject result = null;
        setCredentials();
        RestClient client = getRestClient();

        String searchQuery = "{\"query\":{\"multi_match\":{\"query\":\"" + search + "\", \"fuzziness\":\"AUTO\"}}}";
        String endpoint = "/" + index + "/_search";
        try{
            Request request = new Request(
                    "GET",
                    endpoint);
            request.addParameter("pretty", "true");
            request.addParameter("format", "json");
            request.setJsonEntity(searchQuery);
            Response response = client.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            result = new JSONObject(responseBody);

            // System.out.println(responseBody);
            client.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args){
        JSONObject a = elasticSearch("pracownicy", "Dembski");
    }
}
