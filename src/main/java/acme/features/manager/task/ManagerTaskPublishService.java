package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskPublishService implements AbstractUpdateService<Manager, Task>{
	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected SpamService spam;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		final boolean result;
		Task task;
		int taskId;
		Manager manager;
		Principal principal;
		
		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = !task.getIsPublic() && manager.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "begin", "end","description");
		request.unbind(entity, model, "link", "isPublic", "workload");
		model.setAttribute("readonly", false);
	}

	@Override
	public Task findOne(final Request<Task> request) {
		Task task;
		int taskId;
		
		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		return task;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Date now =new Date();
		final Date begin = entity.getBegin();
		final Date end = entity.getEnd();
		
		final boolean titleSpam = this.spam.isItSpam(entity.getTitle());
		final boolean descripcionSpam = this.spam.isItSpam(entity.getDescription());
		
		
		if(!errors.hasErrors("begin")) {
			errors.state(request, end.after(begin), "begin", "manager.task.form.error.must-be-before-end");
		} 
		if(!errors.hasErrors("begin")) {
			errors.state(request, begin.after(now), "begin", "manager.task.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, end.after(now), "end", "manager.task.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("begin")&&!errors.hasErrors("end")) {
			entity.setExecutionPeriod();
			final double periodo = entity.getExecutionPeriod(); 
			if(!errors.hasErrors("workload")) {
			errors.state(request, periodo>entity.getWorkload(), "workload", "manager.task.form.error.must-be-less-than-work-period");
			errors.state(request, periodo>entity.getWorkload(), "workload", "("+periodo+")");
			}
		}
		final int ent = (int) entity.getWorkload();
		final double dec = entity.getWorkload() - ent;
		if(!errors.hasErrors("workload")) {
			errors.state(request, 0.59>=dec, "workload", "manager.task.form.error.decimal-must-be-under-60");
		}
		if(!errors.hasErrors("title")) {
			errors.state(request, titleSpam==false, "title", "manager.task.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, descripcionSpam==false, "description", "manager.task.form.error.spam");
		}
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		entity.setIsPublic(true);
		this.repository.save(entity);
		
	}
}
