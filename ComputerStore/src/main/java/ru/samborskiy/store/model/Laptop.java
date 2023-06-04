package ru.samborskiy.store.model;

import jakarta.persistence.*;
import lombok.*;
import ru.samborskiy.store.model.Enums.LaptopEnum;

import java.util.Objects;

@Getter @Setter
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Laptop  {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LaptopEnum type;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private RequiredProperties requiredProperties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return id == laptop.id && type == laptop.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
