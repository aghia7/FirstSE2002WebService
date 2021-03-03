package controllers;

import entities.Product;
import entities.User;
import filters.customAnnotations.JWTTokenNeeded;
import services.interfaces.IProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductController {
    @Inject
    private IProductService productService;

    @JWTTokenNeeded
    @GET
    public Response getAllProducts() {
        return Response.ok("Nothing").build();
    }


    @JWTTokenNeeded
    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") int id) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (product == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Product does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(product)
                .build();
    }
}
