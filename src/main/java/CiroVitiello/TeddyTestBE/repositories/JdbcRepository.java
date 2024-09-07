package CiroVitiello.TeddyTestBE.repositories;

import java.util.List;
import java.util.Optional;

// creo cià che JPA faceva "sotto al cofano" creando una repository che implementa il CRUD,
// riutilizzabile per tutte le eventuali entità

public interface JdbcRepository<any> {
    List<any> findAll();
    Optional<any> findById(int id);
    any save(any any);
    any updateById(any any, int id);
    void deleteById(int id);
}
