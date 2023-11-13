package ua.com.valexa.db.model.data.attribute.local_passport;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "local_passport_link", indexes = {
        @Index(name = "local_passport_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "local_passport_link__local_passport_id__index", columnList = "local_passport_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "local_passport_link__full__uindex", columnNames = {"private_person_id", "local_passport_id", "source"})
        })
@Data
public class LocalPassportLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "local_passport_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "local_passport_id", foreignKey = @ForeignKey(name = "local_passport_link__local_passport_fk"))
    private LocalPassport localPassport;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;
}
