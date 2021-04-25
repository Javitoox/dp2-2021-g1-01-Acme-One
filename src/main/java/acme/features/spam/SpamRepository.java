package acme.features.spam;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SpamRepository extends AbstractRepository {

    @Query("select s from Spam s")
    Spam findOne();
}
