
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import java.util.Collection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jcvsa
 */
@Named // so that dependency injection can be used for the EJB
@Path("/blogs")
public class BlogResource {

    @EJB
    private BlogBean blogBean;
    @Context
    private UriInfo context;
    private static final char QUOTE = '\"';

    public BlogResource() {
    }

//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getAllBlogXML() {
//        StringBuilder buffer = new StringBuilder();
//        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        buffer.append("<blogs uri=").append(QUOTE).append(
//                context.getAbsolutePath()).append(QUOTE).append(">");
//        Collection<Blog> allBlog = blogBean.getAllBlogs();
//        for (Blog blog : allBlog) {
//            buffer.append(blog.getXMLString());
//        }
//        buffer.append("</blogs>");
//        return buffer.toString();
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBlogsJSON() {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        Collection<Blog> allBlogs = blogBean.getAllBlogs();
        for (Blog blog : allBlogs) {
            arrayBuilder.add(blog.getJSONObject());
        }
        JsonObject json = jsonBuilder.add("blogs", arrayBuilder).build();

        return json.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{author}")
    public String getUserBlogs(@PathParam("author") String author) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        Collection<Blog> blogs = blogBean.getUserBlogs(author);
        for (Blog blog : blogs) {
            arrayBuilder.add(blog.getJSONObject());
        }
        JsonObject json = jsonBuilder.add("blogs", arrayBuilder).build();

        return json.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public void updateBlog(MultivaluedMap<String, String> formParams) {        
        BlogPK blogPK = new BlogPK(Integer.parseInt(formParams.getFirst("id")));
        String blogTitle = formParams.getFirst("title");
        String blogContent = formParams.getFirst("content");

        blogBean.updateBlog(blogTitle, blogContent, blogPK);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addNewBlog(MultivaluedMap<String, String> formParams) {
        String title = formParams.getFirst("title");
        String content = formParams.getFirst("content");
        String author = formParams.getFirst("author");
        blogBean.addBlog(title, content, author);
    }
}
