package ru.samborskiy.store.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RequiredProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String serialNumber;
    private String producer;
    private int price;
    private int amount;

    @Override
    public String toString() {
        return "Серийный номер:" + serialNumber + " Производитель:" + producer + " Цена:" + price + " Количество на складе:" + amount;
    }
}
