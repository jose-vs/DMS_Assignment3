
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jcvsa
 */
@Stateless
@LocalBean
public class BlogBean {

    @PersistenceContext
    private EntityManager entityManager;

    public Blog addBlog(String title, String content, String author) {
        Blog newBlog = new Blog(title, content, author);
        entityManager.persist(newBlog); // note already in transaction
        return newBlog;
    }

    public Blog getBlog(Integer id) {
        BlogPK primaryKey = new BlogPK(id);
        Blog blog = entityManager.find(Blog.class, primaryKey);
        return blog;
    }    

    public List<Blog> getAllBlogs() {
        String jpqlCommand = "SELECT u FROM Blog u";
        Query query = entityManager.createQuery(jpqlCommand);
        return query.getResultList();
    }
}
