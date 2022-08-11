package hu.progmatic.thymeleafpractice.repository;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BlogEntryRepository extends CrudRepository<BlogEntry, Long> {
    // SELECT * FROM blog_entry WHERE category = ':category';
    List<BlogEntry> findByCategory(String category);

    // SELECT * FROM blog_entry WHERE category = ':category' AND published = :published;
    List<BlogEntry> findByCategoryAndPublished(String category, boolean published);

    // SELECT * FROM blog_entry WHERE category = ':category' ORDER BY created;
    List<BlogEntry> findByCategoryOrderByCreated(String category);

    // SELECT * FROM blog_entry WHERE created < ':created';
    List<BlogEntry> findByCreatedLessThan(LocalDate created);

    // SELECT * FROM blog_entry WHERE content LIKE '%:search%';
    List<BlogEntry> findByContentContaining(String search);

    // Mi adjuk meg a JPQL query-t.
    // JPQL kicsit magasabb szintű, mint az SQL,
    // mivel a JPQL-ben tudunk Java típusokra (osztályokra hivatkozni).
    // Query futtatása + eredményhalmaz visszaadása listaként,
    // továbbra is generálásra kerül (továbbra is a Spring Data JPA feladata).
    @Query("SELECT b FROM BlogEntry b WHERE b.published = false")
    List<BlogEntry> findAllUnpublishedBlogEntries();

}
