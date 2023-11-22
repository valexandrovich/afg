package ua.com.valexa.db.model.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "person_name_link")
@Data
public class PersonNameLink {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id")
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "person_name_id")
    private PersonName personName;
    private LocalDateTime createdAt;
    private String source;

    @PrePersist
    public void initializeUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

}
