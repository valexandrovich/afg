package ua.com.valexa.db.model.data.attribute.inn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "inn", uniqueConstraints = {
        @UniqueConstraint(name = "inn__full__uindex", columnNames = {"code"})
})
@Data
public class Inn extends Attribute {
    @Column(name = "code", length = 15)
    private String code;

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Inn inn = (Inn) o;
        return Objects.equals(code, inn.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
