package acme.features.manager.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/work-plan/")
public class ManagerWorkPlanController extends AbstractController<Manager, WorkPlan> {
	
	@Autowired
	protected ManagerWorkPlanListService managerWorkPlanListService;
	
	@Autowired
	protected ManagerWorkPlanShowService managerWorkPlanShowService;
	
	@Autowired
	protected ManagerWorkPlanCreateService managerWorkPlanCreateService;
	
	@Autowired
	protected ManagerWorkPlanDeleteService managerWorkPlanDeleteService;
	
	@Autowired 
	protected ManagerWorkPlanPublishService managerWorkPlanPublishService;
	
	@Autowired 
	protected ManagerWorkPlanEditService managerWorkPlanEditService;
	
	@Autowired
	protected ManagerWorkPlanAddTaskService managerWorkPlanAddTaskService;
	
	@Autowired
	protected ManagerWorkPlanRemoveTaskService managerWorkPlanRemoveTaskService;
	
	@Autowired
	protected ManagerWorkPlanPrivatizeService managerWorkPlanPrivatizeService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.managerWorkPlanListService);
		super.addBasicCommand(BasicCommand.SHOW, this.managerWorkPlanShowService);
		super.addBasicCommand(BasicCommand.CREATE, this.managerWorkPlanCreateService);
		super.addBasicCommand(BasicCommand.DELETE,this.managerWorkPlanDeleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.managerWorkPlanEditService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.managerWorkPlanPublishService);
		super.addCustomCommand(CustomCommand.ADD_TASK, BasicCommand.UPDATE, this.managerWorkPlanAddTaskService);
		super.addCustomCommand(CustomCommand.REMOVE_TASK, BasicCommand.UPDATE, this.managerWorkPlanRemoveTaskService);
		super.addCustomCommand(CustomCommand.PRIVATIZE, BasicCommand.UPDATE, this.managerWorkPlanPrivatizeService);
	}


}
