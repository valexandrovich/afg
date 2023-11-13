package ua.com.valexa.db.model.data.attribute.inn;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.Objects;

@Entity
@Table(schema = "data", name = "inn", uniqueConstraints = {
        @UniqueConstraint(name = "inn__full__uindex", columnNames = {"code"})
})
@DiscriminatorValue("INN")
@Data
public class Inn extends Attribute {

    private String code;

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inn inn = (Inn) o;
        return Objects.equals(code, inn.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
