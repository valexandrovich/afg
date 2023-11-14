package ua.com.valexa.db.model.data.attribute.birthday;

import jakarta.persistence.*;
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
@DiscriminatorValue("BIRTHDAY")
public class Birthday extends Attribute {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
    @Column(name = "birthday")
    private LocalDate birthday;

    @Override
    public String toString() {
        return birthday.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthday birthday = (Birthday) o;
        return Objects.equals(this.birthday, birthday.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
