package acme.entities.spam;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter

public class Spam extends DomainEntity {
    protected static final long serialVersionUID = 1L;

    @NotBlank
    protected String word;


    protected Integer threshold;
//Definimos el threshold (umbral)que debe tener el spam para hacer getThreshold y compararlo con el actual, por defecto siempre debera estar a 10%
}
