package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{

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
        
        int workplanId=request.getModel().getInteger("id");
		WorkPlan workplan=this.managerWorkPlanRepository.findWorkPlanById(workplanId);
		Manager manager = workplan.getManager();
		Principal principal = request.getPrincipal();
		Boolean canDelete = manager.getUserAccount().getId() == principal.getAccountId();
        model.setAttribute("workload", entity.getWorkload());
        request.unbind(entity, model, "isPublic", "begin", "end", "workload","id","tasks");
        model.setAttribute("readonly", true);
        model.setAttribute("canDelete", canDelete);
	
	}

	@Override
	public WorkPlan findOne(Request<WorkPlan> request) {
		assert request != null;
        WorkPlan result;
        int id;
        id = request.getModel().getInteger("id");
        result = this.managerWorkPlanRepository.findWorkPlanById(id);

        return result;
	}

}
