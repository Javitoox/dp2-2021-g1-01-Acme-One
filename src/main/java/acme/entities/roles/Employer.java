package acme.entities.roles;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Employer extends UserRole {
    protected static final long serialVersionUID = 1L;

    @NotBlank
    protected String company;

    @NotBlank
    protected String sector;


}
