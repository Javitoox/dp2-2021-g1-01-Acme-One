package acme.features.manager.workPlan;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.services.SpamService;

@Service
public class ManagerWorkPlanEditService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Autowired
	protected SpamService spam;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
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
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);			
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
		
	    request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		final int id = request.getModel().getInteger("id");
		return this.repository.findWorkPlanById(id);
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Date now =new Date();
		final Date begin = entity.getBegin();
		final Date end = entity.getEnd();
		
		final boolean titleSpam = this.spam.isItSpam(entity.getTitle());
			
		if(!errors.hasErrors("begin") && !errors.hasErrors("end")) {
			errors.state(request, end.after(begin), "begin", "manager.workplan.form.error.must-be-before-end");
		} 
		if(!errors.hasErrors("begin")) {
			errors.state(request, begin.after(now), "begin", "manager.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, end.after(now), "end", "manager.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("begin") && !errors.hasErrors("end")) {
			errors.state(request, begin.before(end), "end", "manager.workplan.form.error.must-be-after-begin");
		} 
		if(!errors.hasErrors("title")) {
			errors.state(request, !titleSpam,  "title", "manager.workplan.form.error.spam");
		}
		
		
		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		final Manager manager = workplan.getManager();
		final Principal principal = request.getPrincipal();
		final Boolean itsMine = manager.getUserAccount().getId() == principal.getAccountId();
		final Boolean canPublish= itsMine && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		
		List<Task>taskList = this.repository.findTasksAvailable(manager.getId(), workplanId).stream().filter(x->!workplan.getTasks().contains(x)).collect(Collectors.toList());
		if(workplan.getIsPublic())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
		
		request.getModel().setAttribute("ItsMine", itsMine);
        request.getModel().setAttribute("canPublish", canPublish);
        request.getModel().setAttribute("tasks", workplan.getTasks());
		request.getModel().setAttribute("tasksEneabled", taskList);

		final Collection<Task> ls = workplan.getTasks();
		errors.state(request, ((end.getTime() - begin.getTime()) / (1000 * 3600)) 
			>= (ls.stream().mapToDouble(Task::getExecutionPeriod).sum()), "executionPeriod", 
			"manager.workplan.form.addTask.error.executionPeriod");
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findWorkPlanById(entity.getId());
		wp.setEnd(entity.getEnd());
		wp.setBegin(entity.getBegin());
		wp.setTitle(entity.getTitle());
		wp.setExecutionPeriod();
		this.repository.save(wp);
	}

}
