package com.vamonossoftware.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CollectionUtilTest {

    @Test
    public void testFirst() {
        Assert.assertNull(CollectionUtil.first(null));
        Assert.assertEquals("A", CollectionUtil.first(Arrays.asList(new String[]{"A", "B", "C"})));
        Assert.assertNull(CollectionUtil.first(Arrays.asList(new String[]{null, "B", "C"})));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[]", CollectionUtil.toString(null));
        Assert.assertEquals("[A,B,C]", CollectionUtil.toString(new String[]{"A", "B", "C"}));
        Assert.assertEquals("[null,B,C]", CollectionUtil.toString(new String[]{null, "B", "C"}));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(0, CollectionUtil.size((Collection)null));
        Assert.assertEquals(0, CollectionUtil.size(new ArrayList(),new ArrayList()));
        Assert.assertEquals(3, CollectionUtil.size(Arrays.asList(new String[]{"A", "B", "C"})));
        Assert.assertEquals(5, CollectionUtil.size(
                Arrays.asList(new String[]{"A", "B", "C"}),
                Arrays.asList(new String[]{"A", "B"})
                ));

    }
}
