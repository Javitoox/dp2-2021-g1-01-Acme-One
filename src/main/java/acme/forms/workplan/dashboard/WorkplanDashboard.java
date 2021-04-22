package acme.forms.workplan.dashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplanDashboard implements Serializable{

	protected static final long	serialVersionUID	= 1L;
	
	Integer totalNumberOfPublicWorkplans;
	Integer totalNumberOfPrivateWorkplans;
	Integer totalNumberOfFinishedWorkplans;
	Integer totalNumberOfNonFinishedWorkplans;
	Double averageNumberOfPeriods;
	Double deviationOfExecutionPeriods;
	Double minimumNumberOfPeriods;
	Double maximumNumberOfPeriods;
	Double averageNumberOfWorkloads;
	Double deviationOfWorkloads;
	Double minimumNumberOfWorkloads;
	Double maximumNumberOfWorkloads;

}
