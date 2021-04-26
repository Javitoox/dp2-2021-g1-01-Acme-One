package acme.features.manager.task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Task t WHERE t.manager.userAccount.username = ?1 ")
	Collection<Task> findAllManagerTasks(String manager);

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("select w from WorkPlan w join w.tasks t where t.id = ?1")
	List<WorkPlan> findWorkPlansByTaskId(int id);
	
	@Query("select count(w) from WorkPlan w join w.tasks t where t.id = ?1 and w.isPublic = false")
	int findPivateWorkPlansByTaskId(int id);


}