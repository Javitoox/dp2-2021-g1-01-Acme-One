package acme.features.authenticated.announcement;

import acme.entities.announcement.Announcement;
import acme.features.authenticated.AuthenticatedAnnouncementRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedAnnouncementShowService implements AbstractShowService<Authenticated, Announcement> {

    @Autowired
    protected AuthenticatedAnnouncementRepository repository;

    @Override
    public boolean authorise(Request<Announcement> request) {
        assert request!=null;
        return true;
    }

    @Override
    public void unbind(Request<Announcement> request, Announcement entity, Model model) {
    assert request!=null;
    assert entity!=null;
    assert model!=null;

    request.unbind(entity,model, "title", "moment", "status", "text", "info");
    }

    @Override
    public Announcement findOne(Request<Announcement> request) {
        assert request!=null;

        Announcement result;
        int id;

        id = request.getModel().getInteger("id");
        result = this.repository.findOneAnnouncementById(id);

        return result;
    }
}
