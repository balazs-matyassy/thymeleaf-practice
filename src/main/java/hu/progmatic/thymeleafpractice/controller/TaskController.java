package hu.progmatic.thymeleafpractice.controller;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import hu.progmatic.thymeleafpractice.repository.BlogEntryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    // SPRING DATA JPA:
    // Spring automatikusan generál nekünk egy repository-t.
    // Nem kell közvetlenül az EntityManagerrel és SQL (vagy JPQL) lekérdezésekkel foglalkoznunk.
    // https://www.baeldung.com/spring-data-derived-queries

    // https://www.baeldung.com/spring-data-jpa-query
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

    @GetMapping("/search-entries")
    public String searchEntries(
            @RequestParam(name = "search", required = false) String search,
            Model model
    ) {
        List<BlogEntry> entries;

        if (search == null) {
             entries = (List<BlogEntry>) repository.findAll();
        } else {
            entries = repository.findByContentContaining(search);
        }

        model.addAttribute("result", entries);

        return "search";
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

    // Összes publikált Java bejegyzés
    @GetMapping("/task11")
    public String task11(Model model) {
        // findByCategoryAndPublished: mindkettő feltételnek teljesülnie kell
        // findByCategoryOrPublished: elég, ha az egyik teljesül
        List<BlogEntry> entries = repository.findByCategoryAndPublished("Java", true);
        model.addAttribute("result", entries);

        return "result";
    }

    // Összes Java bejegyzés létrehozás dátuma alapján rendezve
    @GetMapping("/task12")
    public String task12(Model model) {
        List<BlogEntry> entries = repository.findByCategoryOrderByCreated("Java");
        model.addAttribute("result", entries);

        return "result";
    }

    // Mainál régebbi bejegyzések
    @GetMapping("/task13")
    public String task13(Model model) {
        List<BlogEntry> entries = repository.findByCreatedLessThan(LocalDate.now());
        model.addAttribute("result", entries);

        return "result";
    }

    // Összes adott kategóriával rendelkező bejegyzés
    @GetMapping("/task14/{search}")
    public String task14(@PathVariable String search, Model model) {
        List<BlogEntry> entries = repository.findByContentContaining(search);
        model.addAttribute("result", entries);

        return "result";
    }

    // Összes nem publikált bejegyzés
    @GetMapping("/task15")
    public String task15(Model model) {
        List<BlogEntry> entries = repository.findAllUnpublishedBlogEntries();
        model.addAttribute("result", entries);

        return "result";
    }

    // TASK16: /task16/{rating}
    // Listázzuk ki azokat a bejegyzéseket,
    // ahol a rating a megadott érték és 5 között van.
    // Pl. rating = 3 esetében a 3 és 5 csillagos bejegyzéseket.
    // 1. Metódus létrehozása a BlogEntryRepository-ban.
    // https://www.baeldung.com/spring-data-derived-queries
    // 2. Endpoint megvalósítása (pl. task14 hasonló)

    // TASK17: /task17/{rating}
    // Listázzuk ki azokat a publikált bejegyzéseket,
    // ahol az értékelés legalább a megadott paraméter.
    // 1. Metódus létrehozása a BlogEntryRepository-ban.
    // https://www.baeldung.com/spring-data-derived-queries
    // 2. Endpoint megvalósítása (pl. task14 hasonló)

}
