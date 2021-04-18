package acme.features.administrator.workplan.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWorkPlanDashboardRepository extends AbstractRepository{

	@Query("select count(w) from WorkPlan w where w.isPublic = 1")
	Integer totalNumberOfPublicWorkplans();
	
	@Query("select count(w) from WorkPlan w where w.isPublic = 0")
	Integer totalNumberOfPrivateWorkplans();


}
