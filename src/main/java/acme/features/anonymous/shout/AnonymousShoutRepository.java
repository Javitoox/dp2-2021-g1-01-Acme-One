package acme.features.anonymous.shout;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
    @Query("select s from Shout s")
    Collection<Shout> findMany();
}



