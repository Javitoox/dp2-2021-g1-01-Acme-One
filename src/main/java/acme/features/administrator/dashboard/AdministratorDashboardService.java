package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorDashboardService{
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
}
