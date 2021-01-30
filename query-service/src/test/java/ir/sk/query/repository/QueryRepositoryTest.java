package ir.sk.query.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sad.kayvanfar on 11/18/2020.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // Spring boot mock
public class QueryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QueryRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
