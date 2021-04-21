package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{
	
	@Autowired
	ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
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
		
		request.unbind(entity, model, "title", "begin", "end", "workload");
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Task();
		result.setTitle("");
		result.setBegin(moment);
		result.setEnd(moment);
		result.setDescription("");
		result.setLink("");
		result.setIsPublic(true);
		result.setWorkload(0.00);
		result.setExecutionPeriod();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Date now =new Date();
		final Date begin = (Date) request.getModel().getAttribute("begin");
		final Date end = (Date) request.getModel().getAttribute("end");
		if(begin.before(end)) {
			errors.state(request,begin.before(end), "begin", "The date begin must be after tan the date end");
		}else if(begin.before(now)) {
			errors.state(request, begin.before(now), "begin", "The date begin must be in the future");
		}
		else if(end.before(now)) {
			errors.state(request, end.before(now), "end", "The date end must be in the future");
		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
	
		entity.setExecutionPeriod();
		this.repository.save(entity);
		
	}

}
