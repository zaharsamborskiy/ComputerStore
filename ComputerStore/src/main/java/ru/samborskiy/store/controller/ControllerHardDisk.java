package ru.samborskiy.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.samborskiy.store.model.HardDisk;
import ru.samborskiy.store.model.Laptop;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryDisk;
import ru.samborskiy.store.repository.RepositoryProperties;
import ru.samborskiy.store.service.ServiceDisk;

@Controller
@RequestMapping("/hardDisk")
public class ControllerHardDisk {
    private final RepositoryDisk repositoryDisk;
    private final ServiceDisk serviceDisk;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    public ControllerHardDisk(RepositoryDisk repositoryDisk, ServiceDisk serviceDisk, RepositoryProperties repositoryProperties) {
        this.repositoryDisk = repositoryDisk;
        this.serviceDisk = serviceDisk;
        this.repositoryProperties = repositoryProperties;
    }

    @GetMapping
    public String centralPageHardDisk(Model model) {
        model.addAttribute("hardDisk", repositoryDisk.findAll());
        return "hardDiskPage/hardDisk";
    }

    @GetMapping("/new")
    public String newHardDisk(Model model) {
        model.addAttribute("hardDisk", new HardDisk());
        model.addAttribute("req", new RequiredProperties());
        return "hardDiskPage/new";
    }
    @PostMapping
    public String create(@ModelAttribute("hardDisk") HardDisk hardDisk, @ModelAttribute("req") RequiredProperties requiredProperties) {
        serviceDisk.save(hardDisk, requiredProperties);
        return "redirect:/hardDisk";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model) {
        model.addAttribute("hardDisk", repositoryDisk.getById(id));
        model.addAttribute("properties", repositoryProperties.getById(id));
        return "hardDiskPage/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("hardDisk", repositoryDisk.getById(id));
        model.addAttribute("req", repositoryProperties.getById(id));
        return "hardDiskPage/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("hardDisk") HardDisk hardDisk, @PathVariable("id") int id, RequiredProperties requiredProperties) {
        serviceDisk.update(id, hardDisk, requiredProperties);
        return "redirect:/hardDisk";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        repositoryDisk.deleteById(id);
        repositoryProperties.deleteById(id);
        return "redirect:/hardDisk";
    }
}
