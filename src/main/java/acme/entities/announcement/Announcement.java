package acme.entities.announcement;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends DomainEntity {
	
	// Serialisation identifier

	protected static final long serialVersionUID = 1L;

	// Attributes
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date moment;
	
	@NotBlank
	protected String title;
	
	protected AnnouncementStatus status;
	
	@NotBlank
	protected String text;
	
	@URL
	protected String info;
	
}
