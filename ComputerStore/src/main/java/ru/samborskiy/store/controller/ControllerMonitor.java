package ru.samborskiy.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.samborskiy.store.model.Monitor;
import ru.samborskiy.store.model.RequiredProperties;
import ru.samborskiy.store.repository.RepositoryMonitor;
import ru.samborskiy.store.repository.RepositoryProperties;
import ru.samborskiy.store.service.ServiceMonitor;

@Controller
@RequestMapping("/monitor")
public class ControllerMonitor {
    private final RepositoryMonitor repositoryMonitor;
    private final ServiceMonitor serviceMonitor;
    private final RepositoryProperties repositoryProperties;
    @Autowired
    public ControllerMonitor(RepositoryMonitor repositoryMonitor, ServiceMonitor serviceMonitor, RepositoryProperties repositoryProperties) {
        this.repositoryMonitor = repositoryMonitor;
        this.serviceMonitor = serviceMonitor;
        this.repositoryProperties = repositoryProperties;
    }

    @GetMapping
    public String centralPageMonitor(Model model) {
        model.addAttribute("monitor", repositoryMonitor.findAll());
        return "monitorPage/monitor";
    }
    @GetMapping("/new")
    public String newMonitor(Model model) {
        model.addAttribute("monitor", new Monitor());
        model.addAttribute("req", new RequiredProperties());
        return "monitorPage/new";
    }
    @PostMapping
    public String create(@ModelAttribute("monitor") Monitor monitor, @ModelAttribute("req") RequiredProperties requiredProperties) {
        serviceMonitor.save(monitor, requiredProperties);
        return "redirect:/monitor";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model) {
        model.addAttribute("monitor", repositoryMonitor.getById(id));
        model.addAttribute("properties", repositoryProperties.getById(id));
        return "monitorPage/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("monitor", repositoryMonitor.getById(id));
        model.addAttribute("req", repositoryProperties.getById(id));
        return "monitorPage/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("monitor") Monitor monitor, @PathVariable("id") int id, RequiredProperties requiredProperties) {
        serviceMonitor.update(id, monitor, requiredProperties);
        return "redirect:/monitor";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        repositoryMonitor.deleteById(id);
        repositoryProperties.deleteById(id);
        return "redirect:/monitor";
    }
}
