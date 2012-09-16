package com.vamonossoftware.core.validation;

import com.vamonossoftware.core.TestUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paul
 */
public class XmlSchemaRuleTest {

    @Test
    public void validate() {
        XmlSchemaRule rule = new XmlSchemaRule(TestUtil.getFile(this,"shiporder.xsd"));
        Errors errors1 = rule.validate(TestUtil.getFile(this, "shiporder.xml"));
        assertTrue(errors1.isEmpty());

        Errors errors2 = rule.validate(TestUtil.getFile(this, "shiporder.invalid.xml"));
        assertFalse(errors2.isEmpty());

    }
}
