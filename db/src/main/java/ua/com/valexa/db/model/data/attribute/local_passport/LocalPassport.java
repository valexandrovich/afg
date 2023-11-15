package ua.com.valexa.db.model.data.attribute.local_passport;


import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "local_passport", indexes = {
        @Index(name = "local_passport__serial__index", columnList = "serial"),
        @Index(name = "local_passport__number__index", columnList = "number")
},
        uniqueConstraints = @UniqueConstraint(name = "local_passport__full__uindex", columnNames = {
                "serial",
                "number"
        })
)
@Data
@DiscriminatorValue("LOCAL_PASSPORT")
public class LocalPassport extends Attribute {
    @Column(name = "serial", length = 2)
    private String serial;
    @Column(name = "number", length = 6)
    private String number;
    @Column(name = "issuer_name")
    private String issuerName;
    @Column(name = "issued_at")
    private LocalDate issuedAt;

    @Override
    public String toString() {
        return serial + '_' +
               number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LocalPassport that = (LocalPassport) o;
        return Objects.equals(serial, that.serial) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serial, number);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
