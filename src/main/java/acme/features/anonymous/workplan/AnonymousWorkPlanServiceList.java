package acme.features.anonymous.workplan;

import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AnonymousWorkPlanServiceList implements AbstractListService<Anonymous, WorkPlan> {

    @Autowired
    protected AnonymousWorkPlanRepository repo;

    @Override
    public boolean authorise(Request<WorkPlan> request) {
        assert request != null;
        return true;
    }
    @Override
    public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "begin", "end");
    }

    @Override
    public Collection<WorkPlan> findMany(Request<WorkPlan> request) {
        assert request != null;
        Collection<WorkPlan> workplans;
        workplans = this.repo.findPublicWorkPlans();
        return workplans;
    }
}
