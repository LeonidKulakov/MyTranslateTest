package ru.kli.translation.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kli.translation.entity.Word;

public interface WordRepo extends CrudRepository<Word, Long> {
}
