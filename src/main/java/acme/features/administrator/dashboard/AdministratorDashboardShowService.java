package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, List<String>>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	// AbstractShowService<Administrator, Task> interface --------------

	@Override
	public boolean authorise(final Request<List<String>> request) {
		assert  request != null;
		
        return true;
	}

	@Override
	public void unbind(final Request<List<String>> request, final List<String> entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model);
		model.setAttribute("title", entity.get(0));
	}

	@Override
	public List<String> findOne(final Request<List<String>> request) {
		assert request != null;
		
		List<String> result;
		Collection<Task> tasks;
		
		result = new ArrayList<>();
		tasks = this.repository.findAllTasks();
		for(final Task t: tasks) {
			result.add(t.getTitle());
		}
		
		return result;
	}

}
