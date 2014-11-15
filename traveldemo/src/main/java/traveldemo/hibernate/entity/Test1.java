package traveldemo.hibernate.entity;

import javax.persistence.*;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "test1")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT t FROM Test1 t")
})
public class Test1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test1 test1 = (Test1) o;

        if (id != test1.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
