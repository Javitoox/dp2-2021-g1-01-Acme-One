package acme.features.authenticated;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {
    @Query("select a from Announcement a where a.id = ?1")

    Announcement findOneAnnouncementById(int id);

    @Query("select a from Announcement a where a.moment >= =1")
    Collection<Announcement> findRecentAnnouncements(Date deadline);
}
