package ua.com.valexa.db.model.data.attribute.birthplace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthplace",
        uniqueConstraints = @UniqueConstraint(name = "birthplace__full__uindex", columnNames = {
                "birthplace"
        })
)
@Data
public class Birthplace extends Attribute {
    @Column(name = "birthplace")
    private String birthplace;

    @Override
    public String toString() {
        return birthplace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Birthplace that = (Birthplace) o;
        return Objects.equals(birthplace, that.birthplace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), birthplace);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
