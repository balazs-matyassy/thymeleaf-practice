package hu.progmatic.thymeleafpractice.controller;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class TaskController {

    @GetMapping("/task1")
    public String task1(Model model) {
        BlogEntry blogEntry = new BlogEntry(
                "Title1", "Content1", "Java",
                5, true, LocalDate.now()
        );
        model.addAttribute("entry", blogEntry);

        return "task1";
    }

}