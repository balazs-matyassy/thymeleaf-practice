package hu.progmatic.thymeleafpractice.repository;

import hu.progmatic.thymeleafpractice.model.BlogEntry;
import org.springframework.data.repository.CrudRepository;

public interface BlogEntryRepository extends CrudRepository<BlogEntry, Long> {
}
