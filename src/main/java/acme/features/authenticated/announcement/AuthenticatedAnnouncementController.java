package acme.features.authenticated.announcement;

import acme.entities.announcement.Announcement;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/authenticated/announcement/")
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {
    @Autowired
    protected AuthenticatedAnnouncementListService listService;

    @Autowired
    protected AuthenticatedAnnouncementShowService showService;

    @PostConstruct
    protected void initialise(){
        super.addBasicCommand(BasicCommand.LIST, this.listService);
        super.addBasicCommand(BasicCommand.SHOW, this.showService);
    }
}
