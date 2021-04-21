package acme.features.spam;

import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnonymousSpamService implements AbstractListService<Anonymous, Spam> {

    @Autowired
    AnonymousSpamRepository spam;

    @Override
    public boolean authorise(Request<Spam> request) {
        assert  request != null;
        return true;
    }

    @Override
    public void unbind(Request<Spam> request, Spam entity, Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "word");
    }

    @Override
    public Collection<Spam> findMany(Request<Spam> request) {
        assert request != null;
        Collection<Spam> result;
        result = this.spam.findMany();
        return result;
    }
}
