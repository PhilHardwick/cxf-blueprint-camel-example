package com.capgemini.example.api.resources;


import com.capgemini.example.model.blog.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/post/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BlogPost {

    @GET
    @Path("/{id}")
    public Post getPost(@PathParam("id") String postId) {
        return null;
    }

}
