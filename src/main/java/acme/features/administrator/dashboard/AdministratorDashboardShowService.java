package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Task>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	// AbstractShowService<Administrator, Task> interface --------------

	@Override
	public boolean authorise(final Request<Task> request) {
		assert  request != null;
        return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		return null;
	}

}
