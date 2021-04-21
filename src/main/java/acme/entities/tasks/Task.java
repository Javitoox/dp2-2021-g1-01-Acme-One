package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
public class Task extends DomainEntity {

	// Serialisation identifier

	protected static final long serialVersionUID = 1L;

	// Attributes

	@NotBlank
	@Length(max = 80)
	protected String title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date begin;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date end;

	@NotBlank
	@Length(max = 500)
	protected String description;

	@URL
	protected String link;

	@NotNull
    protected Boolean isPublic;
	
	@NotNull
	@Positive
	protected double workload;
	
	protected double executionPeriod;
	
	//	Derived attributes

	public void setExecutionPeriod() {
		this.executionPeriod = (double) (this.end.getTime() - this.begin.getTime()) / (1000 * 3600);
	}

	
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;

}
