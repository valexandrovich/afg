package ua.com.valexa.db.model.data.attribute.birthday;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthday", uniqueConstraints = {
        @UniqueConstraint(name = "birthday__full__uindex", columnNames = {"birthday"})
})
@Data
public class Birthday extends Attribute {
    @Column(name = "birthday", length = 15)
    private LocalDate birthday;

    @Override
    public String toString() {
        return birthday.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Birthday birthday1 = (Birthday) o;
        return Objects.equals(birthday, birthday1.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), birthday);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
