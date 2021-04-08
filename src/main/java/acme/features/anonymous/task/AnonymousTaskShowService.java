package acme.features.anonymous.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	@Autowired
	AnonymousTaskRepository anonymousTaskRepository;
	
	@Override
	public boolean authorise(Request<Task> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<Task> request, Task entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("workload", entity.getWorkload());
		request.unbind(entity, model, "title", "begin", "end", "description", "link", "isPublic");
	}

	@Override
	public Task findOne(Request<Task> request) {
		assert request != null;
		Task result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.anonymousTaskRepository.findTaskById(id);

		return result;
	}

}
