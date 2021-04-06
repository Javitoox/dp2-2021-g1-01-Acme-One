package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task>{

	@Autowired
	AnonymousTaskRepository anonymousTaskRepository;
	
	//Check that the request is authorised
	@Override
	public boolean authorise(Request<Task> request) {
		assert request != null;
		return true;
	}

	//Select which attributes must be transferred to the model
	@Override
	public void unbind(Request<Task> request, Task entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
        request.unbind(entity, model, "title", "begin", "link");
		
	}
	
	//To return a collection of entities
	@Override
	public Collection<Task> findMany(Request<Task> request) {
		assert request != null;
        Collection<Task> result;
        result = this.anonymousTaskRepository.findTaskNotFinished();
        return result;
	}

}
