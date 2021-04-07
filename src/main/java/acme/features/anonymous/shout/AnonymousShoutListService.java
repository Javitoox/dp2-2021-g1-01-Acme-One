package acme.features.anonymous.shout;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
public class AnonymousShoutListService  implements AbstractListService<Anonymous, Shout> {

    @Autowired
    AnonymousShoutRepository repository;

    @Override
    public boolean authorise(final Request<Shout> request) {
        assert  request != null;
        return true;
    }

    @Override
    public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "author", "text", "moment");
    }

	@Override
    public Collection<Shout> findMany(final Request<Shout> request) {
        assert request != null;
        Collection<Shout> result;
        result = this.repository.findMany();
        return result;
    }
}
