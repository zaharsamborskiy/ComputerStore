package ru.samborskiy.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.samborskiy.store.model.Laptop;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryLaptop;
import ru.samborskiy.store.repository.RepositoryProperties;
import ru.samborskiy.store.service.ServiceLaptop;

@Controller
@RequestMapping("/laptop")
public class ControllerLaptop {
    private final RepositoryLaptop repositoryLaptop;
    private final ServiceLaptop serviceLaptop;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    public ControllerLaptop(RepositoryLaptop repositoryLaptop, ServiceLaptop serviceLaptop, RepositoryProperties repositoryProperties) {
        this.repositoryLaptop = repositoryLaptop;
        this.serviceLaptop = serviceLaptop;
        this.repositoryProperties = repositoryProperties;
    }

    @GetMapping
    public String centralPageLaptop(Model model) {
        model.addAttribute("laptop", repositoryLaptop.findAll());
        return "laptopPage/laptop";
    }
    @GetMapping("/new")
    public String newLaptop(Model model) {
        model.addAttribute("laptop", new Laptop());
        model.addAttribute("req", new RequiredProperties());
        return "laptopPage/new";
    }
    @PostMapping
    public String create(@ModelAttribute("laptop") Laptop laptop, @ModelAttribute("req") RequiredProperties requiredProperties) {
        serviceLaptop.save(laptop, requiredProperties);
        return "redirect:/laptop";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model) {
        model.addAttribute("laptop", repositoryLaptop.getById(id));
        model.addAttribute("properties", repositoryProperties.getById(id));
        return "laptopPage/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("laptop", repositoryLaptop.getById(id));
        model.addAttribute("req", repositoryProperties.getById(id));
        return "laptopPage/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("laptop") Laptop laptop, @PathVariable("id") int id, RequiredProperties requiredProperties) {
        serviceLaptop.update(id, laptop, requiredProperties);
        return "redirect:/laptop";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        repositoryLaptop.deleteById(id);
        repositoryProperties.deleteById(id);
        return "redirect:/laptop";
    }
}
