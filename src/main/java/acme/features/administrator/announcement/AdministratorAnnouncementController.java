package acme.features.administrator.announcement;

import acme.components.CustomCommand;
import acme.entities.announcement.Announcement;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/administrator/announcement/")
public class AdministratorAnnouncementController extends AbstractController<Administrator, Announcement> {

	@Autowired
	protected AdministratorAnnouncementListService listRecentService;
	
	@Autowired
	protected AdministratorAnnouncementShowService showService;

	@Autowired
	protected AdministratorAnnouncementListAllService listAllService;

	@PostConstruct
	protected void initialise() {
		super.addCustomCommand(CustomCommand.LIST_RECENT,BasicCommand.LIST, this.listRecentService);
		super.addCustomCommand(CustomCommand.LIST_ALL,BasicCommand.LIST, this.listAllService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		
	}
}
