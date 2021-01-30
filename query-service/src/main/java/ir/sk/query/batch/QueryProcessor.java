package ir.sk.query.batch;

import ir.sk.query.clients.QueryRestTemplateClient;

import ir.sk.query.model.Query;
import ir.sk.query.repository.QueryRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
public class QueryProcessor implements ItemProcessor<Query, Query> {

    @Autowired
    QueryRestTemplateClient queryRestTemplateClient;

    @Autowired
    QueryRepository queryRepository;

    @Override
    public Query process(final Query query) throws Exception {
        Query query1 = null;
        ExampleMatcher contactMatcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        boolean state = queryRepository.exists(Example.of(query, contactMatcher));
        if(!state)
            query1 = queryRestTemplateClient.getQuery(query.getQuery());

        return query1;
    }

}
