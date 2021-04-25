package acme.features.manager.workPlan;

import java.util.ArrayList;
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
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkPlanCreateService implements AbstractCreateService<Manager, WorkPlan>{

	@Autowired
	ManagerWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository; //cambiar por el de manu
	
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request!=null;
		return true;
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
		request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","manager");
		model.setAttribute("ItsMine", true);
		List<Task>taskList = taskRepository.findPublicTask().stream().collect(Collectors.toList());//cambiar publicas por todas
		model.setAttribute("tasksEneabled", taskList);
		
	}

	@Override
	public WorkPlan instantiate(Request<WorkPlan> request) {
		assert request != null;

		Manager manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		WorkPlan workPlan = new WorkPlan();
		workPlan.setManager(manager);
		workPlan.setTasks(new ArrayList<Task>());
		
		return workPlan;
	}

	@Override
	public void validate(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert errors != null;
		assert entity != null;

	}

	@Override
	public void create(Request<WorkPlan> request, WorkPlan entity) {
		assert request!= null;
		assert entity!= null;
		entity.setWorkload();
		entity.setExecutionPeriod();
		this.repository.save(entity);
		
		
		
	}

}
