package ua.com.valexa.db.model.data.tag;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "data", name = "tag_type")
@Data
public class TagType {
    @Id
    private String id;
    private String description;
}
