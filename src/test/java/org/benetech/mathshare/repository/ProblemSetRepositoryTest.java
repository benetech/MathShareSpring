package org.benetech.mathshare.repository;

import org.benetech.mathshare.model.entity.ProblemSet;
import org.benetech.mathshare.model.mother.ProblemSetUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL, replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProblemSetRepositoryTest {

    @Autowired
    private ProblemSetRepository problemSetRepository;

    @Test
    public void shouldSaveProblemSet() {
        problemSetRepository.save(ProblemSetUtils.createValidInstance());
        ProblemSet problemSetFromDB = problemSetRepository.findAll().get(0);
        Assert.assertEquals(ProblemSetUtils.DEFAULT_EDIT_CODE, problemSetFromDB.getEditCode());
    }

    @Test
    public void shouldFindByEditCode() {
        problemSetRepository.save(ProblemSetUtils.createValidInstance());
        ProblemSet problemSet = problemSetRepository.findOneByEditCode(ProblemSetUtils.DEFAULT_EDIT_CODE);
        Assert.assertEquals(ProblemSetUtils.DEFAULT_EDIT_CODE, problemSet.getEditCode());
    }
}
