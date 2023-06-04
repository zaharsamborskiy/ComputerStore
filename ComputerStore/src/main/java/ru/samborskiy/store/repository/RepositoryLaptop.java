package ru.samborskiy.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.samborskiy.store.model.Laptop;

@Repository
public interface RepositoryLaptop extends JpaRepository<Laptop, Integer> {
    Laptop getById(int id);
}
