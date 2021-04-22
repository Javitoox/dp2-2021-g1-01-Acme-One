package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task>{
	
	@Autowired
	ManagerTaskRepository repository;
	
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
		result = task.isFinalMode() ||!task.isFinalMode() && manager.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "begin", "end","description","link", "isPublic", "workload");
		model.setAttribute("finalMode", entity.isFinalMode());
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		Task result;
		result = this.repository.findOneTaskById(id);
		return result;
	}
}
