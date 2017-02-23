package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.DefaultApiService;
import io.swagger.api.factories.DefaultApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/")


@io.swagger.annotations.Api(description = "the  API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-21T08:07:49.055Z")
public class DefaultApi  {
   private final DefaultApiService delegate = DefaultApiServiceFactory.getDefaultApi();

    @POST
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "phrase POST", response = void.class) })
    public Response rootPost(@ApiParam(value = "text")  @FormParam("text")  String text
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.rootPost(text,securityContext);
    }
}
