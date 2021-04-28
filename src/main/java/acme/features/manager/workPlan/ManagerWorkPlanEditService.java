package acme.features.manager.workPlan;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanEditService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Autowired
	protected SpamService spam;
	
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
        
        int workplanId = request.getModel().getInteger("id");
		WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		Manager manager = workplan.getManager();
		Principal principal = request.getPrincipal();
		Boolean canDelete = manager.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= canDelete && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		//You can publish a workplan if you have created it and all tasks inside are public
        
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
		
		final Date now =new Date();
		final Date begin = entity.getBegin();
		final Date end = entity.getEnd();
		
		final boolean titleSpam = this.spam.isItSpam(entity.getTitle());
			
		if(!errors.hasErrors("begin")) {
			errors.state(request, end.after(begin), "begin", "manager.workplan.form.error.must-be-before-end");
		} 
		if(!errors.hasErrors("begin")) {
			errors.state(request, begin.after(now), "begin", "manager.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, end.after(now), "end", "manager.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, begin.before(end), "end", "manager.workplan.form.error.must-be-after-begin");
		} 
		if(!errors.hasErrors("title")) {
			errors.state(request, titleSpam==false, "title", "manager.workplan.form.error.spam");
		}
		
		
		int workplanId = request.getModel().getInteger("id");
		WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		Manager manager = workplan.getManager();
		Principal principal = request.getPrincipal();
		Boolean canDelete = manager.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= canDelete && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		List<Task>taskList = repository.findTasksAvailable(manager.getId(), workplanId).stream().filter(x->!workplan.getTasks().contains(x)).collect(Collectors.toList());//cambiar publicas por todas
		request.getModel().setAttribute("tasksEneabled", taskList);
		request.getModel().setAttribute("ItsMine", true);
        request.getModel().setAttribute("canPublish", canPublish);
        request.getModel().setAttribute("tasks", workplan.getTasks());

	}

	@Override
	public void update(Request<WorkPlan> request, WorkPlan entity) {
		WorkPlan wp = repository.findWorkPlanById(entity.getId());
		wp.setEnd(entity.getEnd());
		wp.setBegin(entity.getBegin());
		wp.setTitle(entity.getTitle());
		wp.setExecutionPeriod();
		repository.save(wp);
	}

}
