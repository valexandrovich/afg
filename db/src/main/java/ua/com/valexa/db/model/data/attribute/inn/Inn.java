package ua.com.valexa.db.model.data.attribute.inn;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "inn", uniqueConstraints = {
        @UniqueConstraint(name = "inn__full__uindex", columnNames = {"code"})
})
@Data
public class Inn {

    @Id
    private UUID id;

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
        this.id = UUID.nameUUIDFromBytes(toString().getBytes());
    }
}