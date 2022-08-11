package hu.progmatic.thymeleafpractice.repository;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogEntryRepository extends CrudRepository<BlogEntry, Long> {
    List<BlogEntry> findByCategory(String category);
}
