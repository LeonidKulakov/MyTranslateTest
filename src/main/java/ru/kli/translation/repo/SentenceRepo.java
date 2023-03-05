package ru.kli.translation.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kli.translation.entity.Sentence;
@Repository
public interface SentenceRepo extends CrudRepository <Sentence , Long> {

}
