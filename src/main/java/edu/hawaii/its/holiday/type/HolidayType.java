package edu.hawaii.its.holiday.type;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday_type")
public class HolidayType implements Serializable {

    public static final long serialVersionUID = 23L;
    private Integer id;        // Unique surrogate primary key.
    private Integer typeId;    // Part of natural key.
    private Integer holidayId; // Part of natural key.

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "type_id", nullable = false)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Column(name = "holiday_id", nullable = false)
    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((holidayId == null) ? 0 : holidayId.hashCode());
        result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HolidayType other = (HolidayType) obj;
        if (holidayId == null) {
            if (other.holidayId != null)
                return false;
        } else if (!holidayId.equals(other.holidayId))
            return false;
        if (typeId == null) {
            if (other.typeId != null)
                return false;
        } else if (!typeId.equals(other.typeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HolidayTypeMapping "
                + "[typeId=" + typeId
                + ", holidayId=" + holidayId
                + ", id=" + id
                + "]";
    }
}
