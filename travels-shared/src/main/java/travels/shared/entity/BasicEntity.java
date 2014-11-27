package travels.shared.entity;

import org.hibernate.annotations.GenericGenerator;
import travels.shared.util.BeanUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by irwin on 11/22/14.
 */
@MappedSuperclass
public abstract class BasicEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    protected String id;

    @Column(name = "active")
    protected String active;

    @Column(name = "create_by")
    protected String createBy;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;

    @Column(name = "last_update_by")
    protected String lastUpdateBy;

    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdateDate;

    @Column(name = "last_update_millis")
    protected Long lastUpdateMillis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdateMillis() {
        return lastUpdateMillis;
    }

    public void setLastUpdateMillis(Long lastUpdateMillis) {
        this.lastUpdateMillis = lastUpdateMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicEntity that = (BasicEntity) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return BeanUtil.objectsToString(this, true);
    }

}
