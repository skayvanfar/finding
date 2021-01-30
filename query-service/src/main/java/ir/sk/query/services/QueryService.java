package ir.sk.query.services;

import ir.sk.query.repository.QueryRepository;
import ir.sk.query.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sad.kayvanfar
 */
@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    public Query retrieve(String queryString) {
        return queryRepository.findByQuery(queryString);
    }
}
