package ru.samborskiy.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samborskiy.store.model.RequiredProperties;

@Repository
public interface RepositoryProperties extends JpaRepository<RequiredProperties, Integer> {
    RequiredProperties getById(int id);
}
