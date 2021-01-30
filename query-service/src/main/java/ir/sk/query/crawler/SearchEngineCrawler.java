package ir.sk.query.crawler;

import ir.sk.query.model.Query;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/30/2021.
 */

public interface SearchEngineCrawler {
    Query search(String searchTerm, Integer resultCount) throws Exception;
}
