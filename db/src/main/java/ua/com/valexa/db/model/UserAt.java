package ua.com.valexa.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "user_at")
@Data
public class UserAt {

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
        UserAt userAt = (UserAt) o;
        return Objects.equals(value, userAt.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
