package acme.features.administrator.dashboard;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministratorDashboardController implements ApplicationContextAware {
	
	@Autowired
	protected AdministratorDashboardService service;
	
	protected ConfigurableApplicationContext context;

	// ApplicationContextAware interface --------------------------------------


	@Override
	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		assert context != null;

		this.context = (ConfigurableApplicationContext) context;
	}
	
	@GetMapping("/administrator/dashboard")
	public ModelAndView show() {

        final ModelAndView result = new ModelAndView();
		
		result.setViewName("administrator/dashboard/show");
		
		return result;
	}

}