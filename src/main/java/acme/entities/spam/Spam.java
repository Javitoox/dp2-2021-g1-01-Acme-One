package acme.entities.spam;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;


@Entity
@Getter
@Setter

public class Spam extends DomainEntity {
    protected static final long serialVersionUID = 1L;

    @OneToMany
    private Collection<Word> spamWords;


    protected double threshold;
//Definimos el threshold (umbral)que debe tener el spam para hacer getThreshold y compararlo con el actual, por defecto siempre debera estar a 10%
}
