package ir.sk.query.batch;

import ir.sk.query.crawler.GoogleCrawler;
import ir.sk.query.crawler.SearchEngineCrawler;
import ir.sk.query.model.Query;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Query> reader() {
        return new FlatFileItemReaderBuilder<Query>()
                .name("queryItemReader")
                .resource(new ClassPathResource("queries.txt"))
                .delimited()
                .names(new String[]{"query"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Query>() {{
                    setTargetType(Query.class);
                }})
                .build();
    }

    @Bean
    public QueryProcessor processor() {
        return new QueryProcessor();
    }

    @Bean
    public ItemWriter writer() {
        return new MyParentChildWriter();
    }

    @Bean
    public Job importQueryJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("importQueryJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    /**
     * Multi-threaded Step (single process)
     *
     * @return
     */
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step step(ItemWriter writer, @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("step")
                .<Query, Query> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean
    public SearchEngineCrawler searchEngineCrawler() {
        return new GoogleCrawler();
    }

}
