package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import acme.components.CustomCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.announcement.Announcement;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/announcement/")
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {

    @Autowired
    protected AuthenticatedAnnouncementListService listService;

    @Autowired
    protected AuthenticatedAnnouncementListAllService listAllService;
    @Autowired
    protected AuthenticatedAnnouncementShowService showService;

    @PostConstruct
    protected void initialise() {
        super.addBasicCommand(BasicCommand.LIST, this.listService);
        super.addCustomCommand(CustomCommand.LIST_ALL, BasicCommand.LIST, this.listAllService);
        super.addBasicCommand(BasicCommand.SHOW, this.showService);

    }
}