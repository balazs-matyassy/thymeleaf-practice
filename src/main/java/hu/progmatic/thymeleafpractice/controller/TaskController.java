package hu.progmatic.thymeleafpractice.controller;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import hu.progmatic.thymeleafpractice.repository.BlogEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {
    private final BlogEntryRepository repository;

    public TaskController(BlogEntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/task1")
    public String task1(Model model) {
        BlogEntry blogEntry = new BlogEntry(
                "Title1", "Content1", "Java",
                5, true, LocalDate.now()
        );
        model.addAttribute("entry", blogEntry);

        return "task1";
    }

    @GetMapping("/task3")
    public String task3(Model model) {
        // FELTÉTELES

        // https://www.baeldung.com/spring-thymeleaf-conditionals
        // https://www.baeldung.com/thymeleaf-boolean

        // Ha helyettesítjük az értékeket, akkor a Thymeleaf sablont
        // közvetlenül a böngészőben megnyítva is érvényes HTML5 oldalt kapunk.
        // Natural templates.
        // >        &gt;
        // <        &lt;
        BlogEntry blogEntry = new BlogEntry(
                "Title1", "Content1", "Python",
                6, true, LocalDate.now()
        );
        model.addAttribute("entry", blogEntry);

        return "task3";
    }

    @GetMapping("/task4")
    public String task4(Model model) {
        // KIFEJEZÉSEK

        // https://www.baeldung.com/dates-in-thymeleaf

        BlogEntry blogEntry = new BlogEntry(
                "Title1", "Content1", "Python",
                6, true, LocalDate.now()
        );
        model.addAttribute("entry", blogEntry);

        List<String> names = List.of("Pista", "Józsi");
        model.addAttribute("names", names);

        return "task4";
    }

    @GetMapping("/task5")
    public String task5() {
        return "task5";
    }

    // TASK6: Legyen több bejegyzés (pl. List.of)
    // Táblázatban jelenítsük meg valamennyi bejegyzés adatát.
    // A publikált bejegyzések legyenek félkövér betűvel (style="font-weight: bold;").
    // data-th-each / data-th-if + data-th-unless

    // TASK7: Legyen egy űrlapunk, ahol meg tudjuk adni egy bejegyzés adatait.
    // Hozzunk létre egy endpointot, ahova elküldjük az új bejegyzés adatát.
    // A második endpointnál hozzunk létre egy task7_tostring.html-t,
    // ahol egyszerűen megjelenítjük egy <span> elemen belül a létrehozott bejegyzést.
    // @GetMapping + @PostMapping

    // 2 endpoint + 2 view
    // 1. endpoint megjeleníti az űrlapot
    // 2. endpoint megjeleníti a toStringet

    // JPA Gyakorlás
    // https://www.baeldung.com/spring-data-jpa-query
    // https://www.baeldung.com/spring-data-derived-queries
    // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring

    @GetMapping("/create-entries")
    public String createEntries() {
        repository.save(new BlogEntry(
                "Title1", "Content1", "Python",
                4, true, LocalDate.now()
        ));
        repository.save(new BlogEntry(
                "Title2", "Content2", "Java",
                5, true, LocalDate.now()
        ));
        repository.save(new BlogEntry(
                "Title3", "Content3 (boring)", "Python",
                2, true, LocalDate.now()
        ));
        repository.save(new BlogEntry(
                "Title4", "Content4 (mediocre)", "Java",
                3, true, LocalDate.now()
        ));
        repository.save(new BlogEntry(
                "Draft1", "Draft content 1 (Java)", "Java",
                0, false, LocalDate.now().minusDays(100)
        ));
        repository.save(new BlogEntry(
                "Draft2", "Draft content 2 (Python)", "Python",
                0, false, LocalDate.now().minusDays(25)
        ));

        return "redirect:/list-entries";
    }

    @GetMapping("/list-entries")
    public String listEntries(Model model) {
        List<BlogEntry> entries = (List<BlogEntry>) repository.findAll();
        model.addAttribute("result", entries);

        return "result";
    }

    @GetMapping("/task8")
    public String task8(Model model) {
        List<BlogEntry> entries = List.of(
                new BlogEntry(
                        "Title1", "Content1", "Python",
                        4, true, LocalDate.now()
                ),
                new BlogEntry(
                        "Title2", "Content2", "Java",
                        5, true, LocalDate.now()
                )
        );
        model.addAttribute("result", entries);

        return "result";
    }

    // Összes Javaval kapcsolatos bejegyzés?
    @GetMapping("/task9")
    public String task9(Model model) {
        List<BlogEntry> entries = repository.findByCategory("Java");
        model.addAttribute("result", entries);

        return "result";
    }

    // Összes adott kategóriával rendelkező bejegyzés
    @GetMapping("/task10/{category}")
    public String task10(@PathVariable String category, Model model) {
        List<BlogEntry> entries = repository.findByCategory(category);
        model.addAttribute("result", entries);

        return "result";
    }

}
