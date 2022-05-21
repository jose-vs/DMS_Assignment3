
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jcvsa
 */
public class BlogPK implements Serializable {

    public Integer id;

    public BlogPK(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BlogPK)) {
            return false;
        } else {
            BlogPK other = (BlogPK) obj;
            return id.equals(other.id);
        }
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        } else {
            return id.hashCode();
        }
    }
}
