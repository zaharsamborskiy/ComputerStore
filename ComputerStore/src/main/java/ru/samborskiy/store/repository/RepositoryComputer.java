package ru.samborskiy.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samborskiy.store.model.Computer;


@Repository
public interface RepositoryComputer extends JpaRepository<Computer, Integer> {
    Computer getById(int id);

}
