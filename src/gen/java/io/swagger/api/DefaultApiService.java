package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-21T08:07:49.055Z")
public abstract class DefaultApiService {
    public abstract Response rootPost(String text,SecurityContext securityContext) throws NotFoundException;
}
