package acme.features.anonymous.workplan;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/anonymous/workplan/")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, WorkPlan> {
    @Autowired
    protected AnonymousWorkPlanServiceList serviceList;
@PostConstruct
    protected void initialise(){
        super.addBasicCommand(BasicCommand.LIST, this.serviceList);
    }
}
