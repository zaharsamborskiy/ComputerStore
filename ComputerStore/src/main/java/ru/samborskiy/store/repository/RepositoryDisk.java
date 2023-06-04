package ru.samborskiy.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samborskiy.store.model.HardDisk;


@Repository
public interface RepositoryDisk extends JpaRepository<HardDisk, Integer> {
    HardDisk getById(int id);
}
