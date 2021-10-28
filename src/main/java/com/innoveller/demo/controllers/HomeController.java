package com.innoveller.demo.controllers;

import com.innoveller.demo.models.Paradigm;
import com.innoveller.demo.models.ProgrammingLanguage;
import com.innoveller.demo.services.ParadigmService;
import com.innoveller.demo.services.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @Autowired
    private ParadigmService paradigmService;

    @GetMapping
    @RequestMapping("/")
    public String home(Model modal) {
        modal.addAttribute("programming", programmingLanguageService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String programmingLanguageForm(Model model) {
        List<Paradigm> paradigms = paradigmService.findAll();
        model.addAttribute("paradigmList", paradigms);
        model.addAttribute("language",  new ProgrammingLanguage());
        return "addProgramming";
    }

    @PostMapping("/add")
    public String programmingLanguageSubmit(ProgrammingLanguage language) {
        System.out.println("Hi I am paradigm:" +language.getParadigms().get(0).getName());
        programmingLanguageService.save(language);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        ProgrammingLanguage language = programmingLanguageService.findById(id);
        model.addAttribute("language", language);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String handleUpdateForm(@PathVariable Long id, ProgrammingLanguage language) {
        programmingLanguageService.update(language);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String handleDelete(@PathVariable Long id) {
        programmingLanguageService.delete(id);
        return "redirect:/";
    }
}
