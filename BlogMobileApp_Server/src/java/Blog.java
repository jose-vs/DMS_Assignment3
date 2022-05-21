
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jcvsa
 */
@Entity
@Table(name = "M_Blog")
@IdClass(value = BlogPK.class)
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;
    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;

    public Blog() {
    }

    public Blog(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        String creationDateString = dtf.format(this.dateCreated);
        return creationDateString;
    }

    public String getXMLString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<blog>");
        buffer.append("<title>").append(getTitle()).append("</title>");
        buffer.append("<content>").append(getContent()).append("</content");
        buffer.append("<username>").append(getAuthor()).append("</username>");
        buffer.append("<dateCreated>").append(getDateCreatedString()).append("</dateCreated>");
        buffer.append("</blog>");
        return buffer.toString();
    }

    public JsonObject getJSONObject() {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("title", title);
        jsonBuilder.add("author", content);
        jsonBuilder.add("author", author);
        jsonBuilder.add("dateCreated", getDateCreatedString());
        return jsonBuilder.build();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
