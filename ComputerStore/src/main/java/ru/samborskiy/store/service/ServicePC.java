package ru.samborskiy.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samborskiy.store.model.Computer;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryComputer;
import ru.samborskiy.store.repository.RepositoryProperties;

@Service
public class ServicePC {
    private final RepositoryComputer repositoryComputer;
    private final RepositoryProperties repositoryProperties;


    @Autowired
    public ServicePC(RepositoryComputer repositoryComputer, RepositoryProperties repositoryProperties) {
        this.repositoryComputer = repositoryComputer;
        this.repositoryProperties = repositoryProperties;

    }
    public void update(int idComputer, Computer computer, RequiredProperties requiredProperties) {
        Computer computerToUpdate = repositoryComputer.getById(idComputer);
        RequiredProperties propertiesToUpdate = repositoryProperties.getById(idComputer);
        setFields(computer, computerToUpdate, propertiesToUpdate, requiredProperties);
    }

    private void setFields(Computer computer, Computer computerToUpdate, RequiredProperties propertiesToSave, RequiredProperties requiredProperties) {
        computerToUpdate.setType(computer.getType());
        propertiesToSave.setSerialNumber(requiredProperties.getSerialNumber());
        propertiesToSave.setProducer(requiredProperties.getProducer());
        propertiesToSave.setAmount(requiredProperties.getAmount());
        propertiesToSave.setPrice(requiredProperties.getPrice());
        computerToUpdate.setRequiredProperties(propertiesToSave);
        repositoryComputer.save(computerToUpdate);
        repositoryProperties.save(propertiesToSave);
    }

    public void save(Computer computer, RequiredProperties requiredProperties) {
        Computer computerToSave = new Computer();
        RequiredProperties propertiesToSave = new RequiredProperties();
        setFields(computer, computerToSave, propertiesToSave, requiredProperties);
    }
}
