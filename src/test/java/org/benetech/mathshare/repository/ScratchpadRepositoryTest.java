package org.benetech.mathshare.repository;

import org.benetech.mathshare.model.entity.Scratchpad;
import org.benetech.mathshare.model.mother.ScratchpadUtils;
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
public class ScratchpadRepositoryTest {

    @Autowired
    private ScratchpadRepository scratchpadRepository;

    @Test
    public void shouldSaveScratchpad() {
        Scratchpad scratchPadToSave = ScratchpadUtils.createValidInstance();
        scratchpadRepository.save(scratchPadToSave);
        Scratchpad scratchpadFromDB = scratchpadRepository.findAll().get(0);
        Assert.assertEquals(scratchPadToSave.getContent(), scratchpadFromDB.getContent());
    }
}