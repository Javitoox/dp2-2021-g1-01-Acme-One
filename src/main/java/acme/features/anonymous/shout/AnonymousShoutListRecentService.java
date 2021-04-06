package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListRecentService implements AbstractListService<Anonymous, Shout>{

	@Autowired
    AnonymousShoutRepository repository;
	
	@Override
	public boolean authorise(Request<Shout> request) {
		 assert  request != null;
	     return true;
	}

	@Override
	public void unbind(Request<Shout> request, Shout entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "author","text", "moment");
		
	}

	@Override
	public Collection<Shout> findMany(Request<Shout> request) {
		assert request != null;
        Collection<Shout> result;
        Date date = new Date(); //Obtenemos la fecha de hoy y le restamos un mes para obtener los shouts más recientes.
        if(date.getMonth()==1) {
        	date.setMonth(12);
        }else {
        	date.setMonth(date.getMonth()-1);
        }
        result = this.repository.findManyRecent(date);
        return result;
	}

}
