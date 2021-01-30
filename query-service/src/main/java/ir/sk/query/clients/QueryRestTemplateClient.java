package ir.sk.query.clients;

import ir.sk.query.crawler.SearchEngineCrawler;
import ir.sk.query.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 11/19/2020.
 */
@Component
public class QueryRestTemplateClient {

    private final static int RESULT_COUNT = 11;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SearchEngineCrawler crawler;

    public Query getQuery(String queryString) throws Exception {

        Query query;
        query = crawler.search(queryString, RESULT_COUNT);
        query.setQuery(queryString);

        return query;
    }

}

