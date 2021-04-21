package acme.entities.workPlan;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

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
	
    //	Derived attributes
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