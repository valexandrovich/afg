package ua.com.valexa.db.model.data.attribute.birthplace;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthplace_link", indexes = {
        @Index(name = "birthplace_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "birthplace_link__birthplace_id__index", columnList = "birthplace_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "birthplace_link__full__uindex", columnNames = {"private_person_id", "birthplace_id", "source"})
        })
@Data
public class BithplaceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "birthplace_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "birthplace_id", foreignKey = @ForeignKey(name = "birthplace_link__birthplace_fk"))
    private Birthplace birthplace;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;
}
