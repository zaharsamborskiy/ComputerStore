package ru.samborskiy.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.samborskiy.store.model.Enums.SelectComputerEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Computer {

    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SelectComputerEnum type;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private RequiredProperties requiredProperties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return id == computer.id && type == computer.type;
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
