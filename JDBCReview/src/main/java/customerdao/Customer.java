package customerdao;

import java.sql.Date;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:27
 */
public class Customer {

    private Integer id;
    private String name;
    private String email;
    private Date birth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Customer() {
    }

    public Customer(Integer id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "id:" + "\t" + id + ",name:" + "\t" +  name + ",email:" + "\t" + email + ",birth:" + "\t" + email;
    }
}
