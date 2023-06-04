package ru.samborskiy.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samborskiy.store.model.HardDisk;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryDisk;
import ru.samborskiy.store.repository.RepositoryProperties;

@Service
public class ServiceDisk {
    private final RepositoryDisk repositoryDisk;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    public ServiceDisk(RepositoryDisk repositoryDisk, RepositoryProperties repositoryProperties) {
        this.repositoryDisk = repositoryDisk;
        this.repositoryProperties = repositoryProperties;
    }
    public void save(HardDisk hardDisk, RequiredProperties requiredProperties) {
        HardDisk hardDiskToSave = new HardDisk();
        RequiredProperties propertiesToSave = new RequiredProperties();
        setFields(hardDisk, hardDiskToSave, propertiesToSave, requiredProperties);
    }
    public void update(int id, HardDisk hardDisk, RequiredProperties requiredProperties) {
        HardDisk hardDiskToUpdate = repositoryDisk.getById(id);
        RequiredProperties propertiesToUpdate = repositoryProperties.getById(id);
        setFields(hardDisk, hardDiskToUpdate, propertiesToUpdate, requiredProperties);
    }

    private void setFields(HardDisk hardDisk, HardDisk hardDiskToSave, RequiredProperties propertiesToSave, RequiredProperties requiredProperties) {
        hardDiskToSave.setSize(hardDisk.getSize());
        propertiesToSave.setSerialNumber(requiredProperties.getSerialNumber());
        propertiesToSave.setProducer(requiredProperties.getProducer());
        propertiesToSave.setAmount(requiredProperties.getAmount());
        propertiesToSave.setPrice(requiredProperties.getPrice());
        hardDiskToSave.setRequiredProperties(propertiesToSave);
        repositoryDisk.save(hardDiskToSave);
        repositoryProperties.save(propertiesToSave);
    }
}
