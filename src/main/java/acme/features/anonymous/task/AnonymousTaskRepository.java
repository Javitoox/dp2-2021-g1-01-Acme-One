package acme.features.anonymous.task;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository {
	
	@Query("select t from Task t where t.isPublic = 1")
    Collection<Task> findPublicTask();

	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int id);

}
