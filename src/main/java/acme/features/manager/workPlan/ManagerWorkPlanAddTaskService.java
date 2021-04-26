package acme.features.manager.workPlan;

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
		
		System.out.println(request.getModel().getAttribute("taskSelected"));
		
		request.bind(entity, errors);			
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
        int workplanId = request.getModel().getInteger("id");
		WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		Manager manager = workplan.getManager();
		Principal principal = request.getPrincipal();
		Boolean canDelete = manager.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= canDelete && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		//You can publish a workplan if you have created it and all tasks inside are public
		List<Task>taskList = taskRepository.findPublicTask().stream().collect(Collectors.toList());//cambiar publicas por todas
		model.setAttribute("tasksEneabled", taskList);
        model.setAttribute("canPublish", canPublish);          
        model.setAttribute("workload", entity.getWorkload());
		model.setAttribute("readonly", false);
		model.setAttribute("canDelete", true);
		
        request.unbind(entity, model, "isPublic", "begin", "end", "workload","id","tasks");					
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
		
		int workplanId = request.getModel().getInteger("id");
		WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		Manager manager = workplan.getManager();
		Principal principal = request.getPrincipal();
		Boolean canDelete = manager.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= canDelete && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		request.getModel().setAttribute("ItsMine", true);
        request.getModel().setAttribute("canPublish", canPublish);
	}

	@Override
	public void update(Request<WorkPlan> request, WorkPlan entity) {
		WorkPlan wp = repository.findWorkPlanById(entity.getId());
		
	}

}
