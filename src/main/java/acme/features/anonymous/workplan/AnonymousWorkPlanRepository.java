package acme.features.anonymous.workplan;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {

    @Query("select w from WorkPlan w where w.isPublic = true")
    Collection<WorkPlan> findPublicWorkPlans();

}
