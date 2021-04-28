package acme.features.manager.workPlan;

import java.util.Collection;
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
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanRemoveTaskService implements AbstractUpdateService<Manager, WorkPlan>{
	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		System.out.println(request.getModel().getAttribute("taskId"));
		System.out.println(request.getModel().getAttribute("workplanId"));

		request.bind(entity, errors);			
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
		
        request.unbind(entity, model, "isPublic", "begin", "end", "workload","id","tasks");					
	}

	@Override
	public WorkPlan findOne(Request<WorkPlan> request) {
		int id = request.getModel().getInteger("workplanId");
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
		Integer idTask = request.getModel().getInteger("taskId");
		Collection<Task> ls = wp.getTasks().stream().filter(x->x.getId()!=idTask).collect(Collectors.toList());
		wp.setTasks(ls);
		wp.setWorkload();
		
		repository.save(wp);
		
	}

}
