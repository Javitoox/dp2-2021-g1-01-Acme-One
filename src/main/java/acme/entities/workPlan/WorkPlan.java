package acme.entities.workPlan;

import acme.entities.roles.Manager;
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
	private Collection<@Valid Task> tasks;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Manager manager;
	
	
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

	public void setWorkload() {
		this.workload = this.tasks.stream().mapToDouble(Task::getWorkload).sum();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkPlan other = (WorkPlan) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!isPublic.equals(other.isPublic))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}


	
	
}