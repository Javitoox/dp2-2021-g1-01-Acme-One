package acme.features.manager.workPlan;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository {

	@Query("select w from WorkPlan w where (w.isPublic=1 or w.manager.id =?1)")
	public Collection<WorkPlan>getAllWorkPlans(int id);
	
	@Query("select w from WorkPlan w where w.id =?1")
	public WorkPlan findWorkPlanById(int id);

	@Query("select m from Manager m where m.id = ?1")
	public Manager findOneManagerById(int activeRoleId);

	@Query("select t from Task t where (t.isPublic=1 or t.manager.id =?1)")
	public Collection<Task> findTasksAvailable(int id, int idWP);
}
