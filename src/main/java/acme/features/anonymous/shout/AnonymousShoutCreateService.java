package acme.features.anonymous.shout;

import acme.entities.shouts.Shout;
import acme.features.spam.AnonymousSpamRepository;
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
	protected AnonymousSpamRepository spam;
	
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

	public List<String> removeSpaces(List<String> s){
		List<String> result = new ArrayList<>();
		for(int i=0; i<s.size();i++){
			String a = s.get(i).replace(" ", "");
			result.add(a);
		}
		return result;
	}
	public boolean isItSpam(String phrase){
		boolean result;
		List<String> spamWords = spam.findMany().stream().map(x-> x.getWord()).collect(Collectors.toList());
		List<String> spamList = removeSpaces(spamWords); // con esto tenemos todas las palabras spam sin espacio
		List<String> wordList = new ArrayList<String>(Arrays.asList(phrase.split(" "))); // con esto tenemos todas las palabras de la frase dividida
		Double threshold=0.;
		for(String a : wordList){ //empezamos a comparar una a una las palabras de la frase con las de la lista de spam
			String c = a.toLowerCase();
			for(String b: spamList){
				String d = b.toLowerCase();
				if(c.equals(d)){ //si la palabra coincide con una de la lista de spam
					threshold += ((1./wordList.size())*100); //le sumamos al threshold su tanto % correspondiente conforme a la longitud de la frase
				}else {
					threshold += 0.;
				}
			}
		}
		if(threshold<=10.){
			result = false;
		}else{
			result= true;
		}
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		String phrase = request.getModel().getAttribute("text").toString();
		String author = request.getModel().getAttribute("author").toString();

		boolean authorSpam = isItSpam(author);
		boolean phraseSpam = isItSpam(phrase);


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
