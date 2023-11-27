package ua.com.valexa.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "user_in")
@Data
public class UserIn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer value;

    @ManyToOne
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIn userIn = (UserIn) o;
        return Objects.equals(value, userIn.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
