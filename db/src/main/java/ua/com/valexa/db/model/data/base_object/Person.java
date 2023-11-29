package ua.com.valexa.db.model.data.base_object;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(schema = "data", name = "person", uniqueConstraints = {
        @UniqueConstraint(name = "person__full__uindex", columnNames = {"name"})
})
@Data
@EqualsAndHashCode(of = {"id", "name"})
public class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;

}
