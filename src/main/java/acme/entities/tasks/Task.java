package acme.entities.tasks;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.util.Date;

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
	
	//	Derived attributes
	public double  getWorkload(){
		return (double) (this.end.getTime() - this.begin.getTime()) / (1000 * 3600) ;
	}

	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Task other = (Task) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

	// Relationships

}
