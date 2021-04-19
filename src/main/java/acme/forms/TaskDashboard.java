package acme.forms;

import java.io.Serializable;
import java.time.Period;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfPublicTasks;
	Integer						totalNumberOfPrivateTasks;
	Integer						totalNumberOfFinishedTasks;
	Integer						totalNumberOfPendingTasks;
	Period						averageNumberOfTaskExecutionPeriod;
	Period						deviationOfTaskExecutionPeriod;
	Period						minTaskExecutionPeriod;
	Period						maxTaskExecutionPeriod;
	Double						averageNumberOfWorkload;
	Double						deviationOfWorkload;
	Double						minWorkload;
	Double						maxWorkload;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
