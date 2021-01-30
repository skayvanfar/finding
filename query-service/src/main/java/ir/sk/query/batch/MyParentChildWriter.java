package ir.sk.query.batch;

import ir.sk.query.model.Query;
import ir.sk.query.repository.QueryRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
public class MyParentChildWriter implements ItemWriter<Query> {

    @Autowired
    QueryRepository queryRepository;

    public MyParentChildWriter() {
    }

    @Override
    public void write(List<? extends Query> list) {
        for (Query query : list) {
            ExampleMatcher contactMatcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
            queryRepository.save(query);

            if (query.getSpelling() != null) {
                Query newQuery = new Query(query);
                boolean state = queryRepository.exists(Example.of(newQuery, contactMatcher));
                if (!state)
                    queryRepository.save(newQuery);
            }
        }
    }

}
