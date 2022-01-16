package spring.ezservice.providers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.ezservice.providers.models.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog,Long> {
}
