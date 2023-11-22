package ua.com.valexa.db.model.data;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "private_person")
@Data
public class PrivatePerson {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "author")
    private String author = "SYSTEM";


    @OneToMany(mappedBy = "privatePerson")
    private List<PersonNameLink> personNameLinks = new ArrayList<>();


    @PrePersist
    public void initializeUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

}
