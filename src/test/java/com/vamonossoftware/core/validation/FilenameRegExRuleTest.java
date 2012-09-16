package com.vamonossoftware.core.validation;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paul
 */
public class FilenameRegExRuleTest {
    @Test
    public void validate() {
        FilenameRegExRule rule = new FilenameRegExRule("(?i)[a-z]+\\.xml$");
        assertFalse(rule.validate(new File("h.zip")).isEmpty());
        assertTrue(rule.validate(new File("hh.xml")).isEmpty());
        assertTrue(rule.validate(new File("hh.XML")).isEmpty());
        assertTrue(rule.validate(new File("h.XmL")).isEmpty());
        assertFalse(rule.validate(new File("asdf.xml.txt")).isEmpty());
    }

}
