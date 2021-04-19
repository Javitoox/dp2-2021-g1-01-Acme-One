package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanListService  implements AbstractListService<Manager, WorkPlan>{

    @Autowired
    ManagerWorkPlanRepository managerWorkPlanRepository;
	
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
	     assert request != null;
	     assert entity != null;
	     assert model != null;
	     
	     request.unbind(entity, model,  "isPublic", "begin", "end", "tasks");

	}

	@Override
	public Collection<WorkPlan> findMany(Request<WorkPlan> request) {
		assert request!=null;
		return managerWorkPlanRepository.getAllWorkPlans();
	}

}
