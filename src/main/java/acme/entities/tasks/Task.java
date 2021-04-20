package acme.entities.tasks;

import java.beans.Transient;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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
	
	//	Derived attributes

	public Period getExecutionPeriod(){
		return Period.between(this.begin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
			this.end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	// Relationships

}
