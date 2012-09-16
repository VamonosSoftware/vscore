package com.vamonossoftware.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

/**
 *
 * @author paul
 */
public class XmlDiffIgnore implements DifferenceListener {

    private List<String> xpaths = new ArrayList<String>();
    private List<String> values = new ArrayList<String>();


    public void setXpaths(List<String> xpaths) {
        this.xpaths = xpaths;
    }

    public void addXpath(String xpath) {
        this.xpaths.add(xpath);
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    
    public int differenceFound(Difference difference) {
        if (ignore(difference)) {
            return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
        }
        return RETURN_ACCEPT_DIFFERENCE;
    }

    public void skippedComparison(Node arg0, Node arg1) {
        // do nothing
    }

    String removeIndexes(String xpath) {
        String[] paths = StringUtils.split(xpath, '/');
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].contains("[")) {
                paths[i] = StringUtils.left(paths[i], paths[i].indexOf("["));
            }
        }
        return StringUtils.join(paths, '/');
    }

    boolean ignore(Difference difference) {
        return ignoreXpath(difference.getControlNodeDetail().getXpathLocation())
                || ignoreValue(difference.getControlNodeDetail().getValue());
    }

    boolean ignoreXpath(String xpath) {
        if (xpath != null) {
            String path = removeIndexes(xpath);
            for (String x : xpaths) {
                if (path.contains(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean ignoreValue(String value) {
        return (values.contains(value));
    }
}

