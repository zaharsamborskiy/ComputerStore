package ru.samborskiy.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@Entity
@NoArgsConstructor @AllArgsConstructor
public class HardDisk  {
    @Id
    private int id;
    private int size;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private RequiredProperties requiredProperties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HardDisk disk = (HardDisk) o;
        return id == disk.id && size == disk.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }

    @Override
    public String toString() {
        return "Диск на " + size + " ГБ";
    }
}
