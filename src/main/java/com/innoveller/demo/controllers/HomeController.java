package com.innoveller.demo.controllers;

import com.innoveller.demo.models.Paradigm;
import com.innoveller.demo.models.ProgrammingLanguage;
import com.innoveller.demo.services.ParadigmService;
import com.innoveller.demo.services.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String programmingLanguageForm(Model model, ProgrammingLanguage language ) {

        List<Paradigm> paradigms = paradigmService.findAll();
        model.addAttribute("paradigmList", paradigms);
        model.addAttribute("language",  language);
        return "addProgramming";
    }

    @PostMapping("/add")
    public String programmingLanguageSubmit(@Valid @ModelAttribute("language") ProgrammingLanguage language,
                                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Paradigm> paradigms = paradigmService.findAll();
            model.addAttribute("paradigmList", paradigms);
            return "addProgramming";
        }
        programmingLanguageService.save(language);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        ProgrammingLanguage language = programmingLanguageService.findById(id);
        List<Paradigm> paradigms = paradigmService.findAll();
        model.addAttribute("paradigmList", paradigms);
        model.addAttribute("language", language);
        return "editProgramming";
    }

    @PostMapping("/edit/{id}")
    public String handleUpdateForm(@PathVariable Long id,
                                   @Valid @ModelAttribute("language") ProgrammingLanguage language,
                                   BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<Paradigm> paradigms = paradigmService.findAll();
            model.addAttribute("paradigmList", paradigms);
            return "editProgramming";
        }
        programmingLanguageService.update(language);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String handleDelete(@PathVariable Long id) {
        programmingLanguageService.delete(id);
        return "redirect:/";
    }
}
