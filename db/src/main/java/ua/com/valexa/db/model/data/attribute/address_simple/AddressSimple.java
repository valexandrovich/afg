package ua.com.valexa.db.model.data.attribute.address_simple;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "address_simple",
        uniqueConstraints = @UniqueConstraint(name = "address_simple__full__uindex", columnNames = {
                "address"
        })
)
@Data
@DiscriminatorValue("ADDRESS_SIMPLE")
public class AddressSimple extends Attribute {
    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressSimple that = (AddressSimple) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
