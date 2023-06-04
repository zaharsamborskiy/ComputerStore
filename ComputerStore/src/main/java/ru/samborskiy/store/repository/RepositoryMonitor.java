package ru.samborskiy.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samborskiy.store.model.Monitor;

@Repository
public interface RepositoryMonitor extends JpaRepository<Monitor, Integer> {
    Monitor getById(int id);
}
