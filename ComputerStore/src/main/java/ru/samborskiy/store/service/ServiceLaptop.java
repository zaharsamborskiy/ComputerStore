package ru.samborskiy.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.samborskiy.store.model.Laptop;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryLaptop;
import ru.samborskiy.store.repository.RepositoryProperties;

@Service
public class ServiceLaptop {
    private final RepositoryLaptop repositoryLaptop;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    public ServiceLaptop(RepositoryLaptop repositoryLaptop, RepositoryProperties repositoryProperties) {
        this.repositoryLaptop = repositoryLaptop;
        this.repositoryProperties = repositoryProperties;
    }

    public void save(Laptop laptop, RequiredProperties requiredProperties) {
        Laptop laptopToSave = new Laptop();
        RequiredProperties propertiesToSave = new RequiredProperties();
        setFields(laptop, laptopToSave, propertiesToSave, requiredProperties);
    }

    public void update(int id, Laptop laptop, RequiredProperties requiredProperties) {
        Laptop laptopToUpdate = repositoryLaptop.getById(id);
        RequiredProperties propertiesToUpdate = repositoryProperties.getById(id);
        setFields(laptop, laptopToUpdate, propertiesToUpdate, requiredProperties);
    }

    private void setFields(Laptop laptop, Laptop laptopToSave, RequiredProperties propertiesToSave, RequiredProperties requiredProperties) {
        laptopToSave.setType(laptop.getType());
        propertiesToSave.setSerialNumber(requiredProperties.getSerialNumber());
        propertiesToSave.setProducer(requiredProperties.getProducer());
        propertiesToSave.setAmount(requiredProperties.getAmount());
        propertiesToSave.setPrice(requiredProperties.getPrice());
        laptopToSave.setRequiredProperties(propertiesToSave);
        repositoryLaptop.save(laptopToSave);
        repositoryProperties.save(propertiesToSave);
    }
}
