package acme.features.anonymous.shout;

import acme.entities.shouts.Shout;
import acme.features.spam.AnonymousSpamRepository;
import acme.features.spam.AnonymousSpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout>{
	
	// Internal state
	
	@Autowired
	protected AnonymousShoutRepository repository;

	@Autowired
	protected AnonymousSpamService spam;
	
	// AbstractCreateService<Administrator, Shout> interface

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		
		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");
		
		return result;
	}


	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		String phrase = request.getModel().getAttribute("text").toString();
		String author = request.getModel().getAttribute("author").toString();

		boolean authorSpam = spam.isItSpam(author);
		boolean phraseSpam = spam.isItSpam(phrase);


		if (phraseSpam == true && authorSpam == false) {
			errors.add("text", "Your text is considered spam, please, use a proper vocabulary ");
		}else if(phraseSpam==false && authorSpam==true){
			errors.add("author", "Your author is considered spam, please, use a proper vocabulary");
		} else if(phraseSpam==true && authorSpam==true){
			errors.add("text", "Your text is considered spam, please, use a proper vocabulary");
			errors.add("author", "Your author is considered spam, please, use a proper vocabulary");
		}
	}



	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
