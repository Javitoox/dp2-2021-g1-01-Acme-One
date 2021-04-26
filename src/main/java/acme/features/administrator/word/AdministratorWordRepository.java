package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Word;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWordRepository extends AbstractRepository{

	@Query("select w from Word w")
    Collection<Word> findAllWords();
	
}
