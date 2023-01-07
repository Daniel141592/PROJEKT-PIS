package pis.projekt.utils;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

public class ElasticConnector {

    private final String cloudId;

    private final CredentialsProvider credentialsProvider;

    public ElasticConnector(String cloudId, String username, String password) {
        this.cloudId = cloudId;
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
    }


    private @NotNull RestClient getRestClient() {
        RestClientBuilder builder = RestClient.builder(cloudId)
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                );

        return builder.build();
    }

    public String elasticSearch(String search) {
        return elasticSearch(null, search);
    }

    public String elasticSearch(String index, String search) {
        JSONObject result = null;
        RestClient client = getRestClient();

        String searchQuery = "{\"query\":{\"multi_match\":{\"query\":\"" + search + "\", \"fuzziness\":\"AUTO\"}}}";
        String endpoint = index != null ? "/" + index + "/_search" : "/_search";
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

            client.close();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return result.toString();
    }

    public int getCount(String index) {
        int count = 0;
        JSONObject result = null;
        RestClient client = getRestClient();
        String endpoint = "/" + index + "/_count";
        try{
            Request request = new Request(
                    "GET",
                    endpoint);

            Response response = client.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            result = new JSONObject(responseBody);
            count = JSONHandler.getCountFromJSON(result);
            client.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }
}
