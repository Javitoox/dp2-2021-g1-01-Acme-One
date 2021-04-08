package acme.features.anonymous.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/workplan/")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, WorkPlan> {

    @Autowired
    protected AnonymousWorkPlanListService anonymousWorkPlanListService;

    @PostConstruct
    protected void initialise() {
        super.addBasicCommand(BasicCommand.LIST, this.anonymousWorkPlanListService);
    }
}

