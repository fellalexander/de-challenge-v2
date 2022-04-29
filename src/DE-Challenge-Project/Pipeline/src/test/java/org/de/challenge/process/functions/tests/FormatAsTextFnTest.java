package org.de.challenge.process.functions.tests;

import org.de.challenge.domain.Stats;
import org.de.challenge.process.functions.FormatAsTextFn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatAsTextFnTest {
    @Test
    public void FormatAsTextFnTest(){
        FormatAsTextFn formatAsTextFn = new FormatAsTextFn("Title");
        Stats stats= new Stats("team");
        assertEquals("Title: team",formatAsTextFn.apply(stats));

    }
}
