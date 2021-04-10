package acme.feactures.administrator.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAnnouncementListRecentService implements AbstractListService<Administrator, Announcement>{
	
	@Autowired
	protected AdministratorAnnouncementRepository repository;
	
	@Override
    public boolean authorise(final Request<Announcement> request) {
        assert  request != null;
        return true;
    }

	@Override
    public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "title", "moment", "status", "text", "info");
    }

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;
        Collection<Announcement> result;
        Calendar calendar;
        Date deadline;
        
        calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        deadline=calendar.getTime();
        
        result = this.repository.findRecentAnnouncements(deadline);
        return result;
	}
}
