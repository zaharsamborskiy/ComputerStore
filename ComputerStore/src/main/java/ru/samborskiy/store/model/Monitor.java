package ru.samborskiy.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Monitor {
    @Id
    private int id;
    private int diagonal;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private RequiredProperties requiredProperties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return id == monitor.id && diagonal == monitor.diagonal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diagonal);
    }

    @Override
    public String toString() {
        return "Монитор диагональю " + diagonal;
    }
}
