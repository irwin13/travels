package travels.shared.entity;

import javax.persistence.*;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "city")
@NamedQueries({
        @NamedQuery(name = "city.getAll", query = "SELECT c FROM City c ORDER BY c.cityName"),
        @NamedQuery(name = "city.getById", query = "SELECT c FROM City c WHERE id = :id ")
})
public class City extends BasicEntity {

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_code")
    private String cityCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
