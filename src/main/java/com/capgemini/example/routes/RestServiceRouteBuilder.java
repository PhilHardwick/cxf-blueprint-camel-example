package com.capgemini.example.routes;

import com.capgemini.example.model.blog.Post;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.Arrays;
import java.util.Date;

public class RestServiceRouteBuilder extends RouteBuilder {

    private String restEndpoint;

    @Override
    public void configure() throws Exception {
        from(restEndpoint)
                .recipientList(simple("direct:${header.operationName}"));


        from("direct:getPost")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Post blogPost = new Post();
                        blogPost.setId(exchange.getIn().getHeader("id", Integer.class));
                        blogPost.setTitle("My Title");
                        blogPost.setContent("##Secondary title");
                        blogPost.setTags(Arrays.asList("interesting", "camel", "blueprint"));
                        blogPost.setPublishedDate(new Date());
                        exchange.getIn().setBody(blogPost);
                    }
                }).end();
    }

    public void setRestEndpoint(String restEndpoint) {
        this.restEndpoint = restEndpoint;
    }
}
