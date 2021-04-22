package acme.entities.workPlan;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{
	
	// Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
		
	// Attributes
	
	protected Boolean isPublic;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date begin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	@ManyToMany(fetch = FetchType.EAGER)
	protected Collection<@Valid Task> tasks;
	
	@Positive
	protected double workload;
	
	protected double executionPeriod;
	
    //	Derived attributes
	
	public void setExecutionPeriod() {
		this.executionPeriod = (double) (this.end.getTime() - this.begin.getTime()) / (1000 * 3600);
	}
	
	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	public double getWorkload() {
		return this.tasks.stream().mapToDouble(Task::getWorkload).sum();
	}
}