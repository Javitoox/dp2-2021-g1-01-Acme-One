package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Task t")
	Collection<Task> findAllTasks();

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneTaskById(int id);

}
