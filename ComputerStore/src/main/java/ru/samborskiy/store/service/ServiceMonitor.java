package ru.samborskiy.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samborskiy.store.model.Monitor;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryMonitor;
import ru.samborskiy.store.repository.RepositoryProperties;

@Service
public class ServiceMonitor {
    private final RepositoryMonitor repositoryMonitor;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    public ServiceMonitor(RepositoryMonitor repositoryMonitor, RepositoryProperties repositoryProperties) {
        this.repositoryMonitor = repositoryMonitor;
        this.repositoryProperties = repositoryProperties;
    }
    public void save(Monitor monitor, RequiredProperties requiredProperties) {
        Monitor monitorToSave = new Monitor();
        RequiredProperties propertiesToSave = new RequiredProperties();
        setFields(monitor, monitorToSave, propertiesToSave, requiredProperties);
    }
    public void update(int id, Monitor monitor, RequiredProperties requiredProperties) {
        Monitor monitorToUpdate = repositoryMonitor.getById(id);
        RequiredProperties propertiesToUpdate = repositoryProperties.getById(id);
        setFields(monitor, monitorToUpdate, propertiesToUpdate, requiredProperties);
    }

    private void setFields(Monitor monitor, Monitor monitorToSave, RequiredProperties propertiesToSave, RequiredProperties requiredProperties) {
        monitorToSave.setDiagonal(monitor.getDiagonal());
        propertiesToSave.setSerialNumber(requiredProperties.getSerialNumber());
        propertiesToSave.setProducer(requiredProperties.getProducer());
        propertiesToSave.setAmount(requiredProperties.getAmount());
        propertiesToSave.setPrice(requiredProperties.getPrice());
        monitorToSave.setRequiredProperties(propertiesToSave);
        repositoryMonitor.save(monitorToSave);
        repositoryProperties.save(propertiesToSave);
    }
}
