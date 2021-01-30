package ir.sk.query.repository;

import ir.sk.query.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Created by sad.kayvanfar
 */
@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    Query findByQuery(String queryString);
}
