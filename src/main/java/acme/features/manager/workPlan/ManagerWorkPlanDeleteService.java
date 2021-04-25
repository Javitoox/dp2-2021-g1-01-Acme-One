package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkPlanDeleteService implements AbstractDeleteService<Manager, WorkPlan>{

	@Autowired
	ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request != null;
		final boolean result;
		WorkPlan workplan;
		int workplanId;
		Manager manager;
		Principal principal;
		
		workplanId=request.getModel().getInteger("id");
		workplan=this.repository.findWorkPlanById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);		
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        model.setAttribute("workload", entity.getWorkload());
        request.unbind(entity, model, "isPublic", "begin", "end", "workload","id","tasks");		
		model.setAttribute("readonly", false);

	}

	@Override
	public WorkPlan findOne(Request<WorkPlan> request) {
		int id = request.getModel().getInteger("id");
		return repository.findWorkPlanById(id);
	}

	@Override
	public void validate(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void delete(Request<WorkPlan> request, WorkPlan entity) {
		assert request != null;
		assert entity != null;
		repository.deleteById(entity.getId());
	}

}
