package iocruz.dropwizard.resources;

import iocruz.dropwizard.api.Product;
import iocruz.dropwizard.db.ProductDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource
{
    private final ProductDAO productDAO;

    public ProductResource(final ProductDAO productDAO) { this.productDAO = productDAO; }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") final Integer id)
    {
        return Response.ok(productDAO.getProductById(id)).build();
    }

    @GET
    public Response getAll()
    {
        return Response.ok(productDAO.getAll()).build();
    }

    @POST
    public Response createProduct(final Product product)
    {

        var id = productDAO.create(product);
        return Response
                .created(URI.create("api/products/" + id))
                .build();
    }


}
