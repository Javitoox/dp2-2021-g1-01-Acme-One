package acme.features.manager.workPlan;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanAddTaskService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
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
		
		result = (manager.getUserAccount().getId() == principal.getAccountId());
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
		
	    request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");
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
	public void update(Request<WorkPlan> request, WorkPlan entity) {
		WorkPlan wp = repository.findWorkPlanById(entity.getId());
		Task task = (Task) taskRepository.findById((Integer) request.getModel().getInteger("taskSelected")).orElse(null);
		Collection<Task> ls = wp.getTasks();
		ls.add(task);
		wp.setTasks(ls);
		wp.setWorkload();
		
		repository.save(wp);
		
	}

}
