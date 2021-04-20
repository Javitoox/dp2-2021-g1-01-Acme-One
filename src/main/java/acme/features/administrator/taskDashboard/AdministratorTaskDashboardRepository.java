package acme.features.administrator.taskDashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTaskDashboardRepository extends AbstractRepository {
	
	@Query("select count(t) from Task t where t.isPublic = true")
	Integer totalNumberOfPublicTasks();
	
	@Query("select count(t) from Task t where t.isPublic = false")
	Integer totalNumberOfPrivateTasks();
	
	@Query("select count(t) from Task t where ?1 > t.end")
	Integer totalNumberOfFinishedTasks(Date now);
	
	@Query("select count(t) from Task t where ?1 <= t.end")
	Integer totalNumberOfPendingTasks(Date now);
	
	@Query("select count(t.workload) from Task t")
	Double averageNumberOfWorkload();

}
