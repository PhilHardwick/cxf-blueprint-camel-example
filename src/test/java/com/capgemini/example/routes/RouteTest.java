package com.capgemini.example.routes;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.Dictionary;

import static org.hamcrest.CoreMatchers.containsString;

public class RouteTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/blueprint.xml";
    }

    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        props.put("apiRootUrl", "http://localhost:9999/api");
        return "com.capgemini.example";
    }

    @Test
    public void testRoute() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:9999/api/blog/1");

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(getRequest);

        String responseBody = EntityUtils.toString(response.getEntity());
        assertThat(responseBody, containsString("title"));
    }

}