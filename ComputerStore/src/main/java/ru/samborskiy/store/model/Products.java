package ru.samborskiy.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Products {
    private Computer computer;
    private Monitor monitor;
    private Laptop laptop;
    private HardDisk hardDisk;
}
