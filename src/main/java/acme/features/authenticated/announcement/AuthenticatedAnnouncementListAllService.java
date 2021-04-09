package acme.features.authenticated.announcement;

import acme.entities.announcement.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
public class AuthenticatedAnnouncementListAllService implements AbstractListService<Authenticated,Announcement> {

    @Autowired
    protected AuthenticatedAnnouncementRepository repository;

    @Override
    public boolean authorise(Request<Announcement> request) {
        assert request != null;
        return true;
    }

    @Override
    public void unbind(Request<Announcement> request, Announcement entity, Model model) {
        assert request!=null;
        assert entity!=null;
        assert model!=null;

        request.unbind(entity, model, "title","moment","status","text","info");

    }

    @Override
    public Collection<Announcement> findMany(Request<Announcement> request) {
        assert request !=null;
        Collection<Announcement> result = this.repository.findAllAnnouncements();
        return result;

    }

}
