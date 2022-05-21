
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

    public Blog updateBlog(String title, String content, Integer id) {
        BlogPK primaryKey = new BlogPK(id);
        Blog blogEdit = (Blog) entityManager.find(Blog.class, primaryKey);       
        blogEdit.setContent(content);
        blogEdit.setTitle(title);
        entityManager.merge(blogEdit);

        return blogEdit;
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

    public List<Blog> getUserBlogs(String author) {
        String jpqlCommand = "SELECT u FROM Blog u WHERE u.author LIKE :author";
        Query query = entityManager.createQuery(jpqlCommand);
        query.setParameter("author", author);

        return query.getResultList();
    }
    
    public void deleteBlog(Integer id) {
       entityManager.remove(getBlog(id));
    }
}
