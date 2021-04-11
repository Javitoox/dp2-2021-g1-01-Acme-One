package acme.features.administrator.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.announcement.Announcement;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/announcement/")
public class AdministratorAnnouncementController extends AbstractController<Administrator, Announcement> {

	@Autowired
	protected AdministratorAnnouncementListAllService		listAllService;

	@Autowired
	protected AdministratorAnnouncementListRecentService	listRecentService;

	@Autowired
	protected AdministratorAnnouncementShowService			showService;

	@Autowired
	protected AdministratorAnnouncementCreateService		createService;


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ALL, BasicCommand.LIST, this.listAllService);
		super.addCustomCommand(CustomCommand.LIST_RECENT, BasicCommand.LIST, this.listRecentService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
