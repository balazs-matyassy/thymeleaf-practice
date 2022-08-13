package hu.progmatic.thymeleafpractice.repository;

import hu.progmatic.thymeleafpractice.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// https://www.baeldung.com/spring-data-jpa-pagination-sorting
// PagingAndSortingRepository<Log, Long>: okosabb (kibővíti a CrudRepository<Log, Long>-t).
// JpaRepository<Log, Long>: okosabb (kibővíti a PagingAndSortingRepository<Log, Long>-t)
// Ha van saját implementáció, akkor NEM kötelező implementálni a CrudRepository-t.
public interface LogRepository extends CrudRepository<Log, Long> {
    // SELECT * FROM log WHERE message LIKE '%:search%';
    List<Log> findByMessageContaining(String search);
}
