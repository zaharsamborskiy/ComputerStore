package ru.samborskiy.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.samborskiy.store.model.Computer;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryComputer;
import ru.samborskiy.store.repository.RepositoryProperties;
import ru.samborskiy.store.service.ServicePC;

@Controller
@RequestMapping("/computer")
public class ControllerPC {

    private final RepositoryComputer repositoryComputer;
    private final RepositoryProperties repositoryProperties;

    private final ServicePC servicePC;

    @Autowired
    public ControllerPC(RepositoryComputer repositoryComputer, ServicePC servicePC, RepositoryProperties repositoryProperties) {
        this.repositoryComputer = repositoryComputer;
        this.servicePC = servicePC;
        this.repositoryProperties = repositoryProperties;
    }

    @GetMapping
    public String centralPageComputer(Model model) {
        model.addAttribute("computer", repositoryComputer.findAll());
        return "computerPage/computer";
    }

    @GetMapping("/new")
    public String newComputer(Model model) {
        model.addAttribute("computer", new Computer());
        model.addAttribute("req", new RequiredProperties());
        return "computerPage/new";
    }

    @PostMapping
    public String create(@ModelAttribute("computer") Computer computer, @ModelAttribute("req") RequiredProperties requiredProperties) {
        servicePC.save(computer, requiredProperties);
        return "redirect:/computer";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model) {
        model.addAttribute("computer", repositoryComputer.getById(id));
        model.addAttribute("properties", repositoryProperties.getById(id));
        return "computerPage/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("computer", repositoryComputer.getById(id));
        model.addAttribute("req", repositoryProperties.getById(id));
        return "computerPage/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("computer") Computer computer, @PathVariable("id") int id, RequiredProperties requiredProperties) {
        servicePC.update(id, computer, requiredProperties);
        return "redirect:/computer";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        repositoryComputer.deleteById(id);
        repositoryProperties.deleteById(id);
        return "redirect:/computer";
    }
}
