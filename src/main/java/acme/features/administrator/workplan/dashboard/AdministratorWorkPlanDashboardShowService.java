package acme.features.administrator.workplan.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.workplan.dashboard.WorkplanDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWorkPlanDashboardShowService implements AbstractShowService<Administrator, WorkplanDashboard> {

	@Autowired
	AdministratorWorkPlanDashboardRepository administratorWorkPlanDashboardRepository;
	
	@Override
	public boolean authorise(Request<WorkplanDashboard> request) {
		assert request != null;
        return true;
	}

	@Override
	public void unbind(Request<WorkplanDashboard> request, WorkplanDashboard entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "totalNumberOfPublicWorkplans", "totalNumberOfPrivateWorkplans");
	}

	@Override
	public WorkplanDashboard findOne(Request<WorkplanDashboard> request) {
		assert request != null;
		WorkplanDashboard result;
		
		Integer totalNumberOfPublicWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfPublicWorkplans();
		Integer totalNumberOfPrivateWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfPrivateWorkplans();
		
		result = new WorkplanDashboard();
		result.setTotalNumberOfPublicWorkplans(totalNumberOfPublicWorkplans);
		result.setTotalNumberOfPrivateWorkplans(totalNumberOfPrivateWorkplans);
		
		return result;
	}

}
