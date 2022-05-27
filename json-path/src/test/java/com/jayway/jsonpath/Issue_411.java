package com.jayway.jsonpath;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for Issue 411
 */
//CS304 Issue link: https://github.com/json-path/JsonPath/issues/411
public class Issue_411 { //NOPMD - suppressed AtLeastOneConstructor
    /**
     * Test whether the index work
     */
    @Test
    public void testIndex1() {
        // Using two description in different layer
        final String json = "{\n"
                + "\"description\":\"out\",\n"
                + "\"inner\":{\n"
                + "\"description\":\"inner\"\n"
                + "}\n"
                + "}";
        final Object result = JsonPath.parse(json).read("$..description[1]"); //NOPMD - suppressed LawOfDemeter
        Assert.assertEquals("Wrong output", "[\"inner\"]", result.toString()); //NOPMD - suppressed LawOfDemeter
    }

    /**
     * Another test case to test whether the index work
     */
    @Test
    public void testIndex2() {
        // Using two description in different layer and different type
        final String json = "{\n"
                +
                "\"description\":[\"1\",\"2\"],\n"
                +
                "\"map\":\"go\",\n"
                +
                "\"inner\":{\n"
                +
                "\"description\":\"inner\"\n"
                +
                "}\n"
                +
                "}";
        final Object result = JsonPath.parse(json).read("$..description[0]"); //NOPMD - suppressed LawOfDemeter
//        System.out.println(result);
        Assert.assertEquals("Wrong output", "[[\"1\",\"2\"]]", result.toString()); //NOPMD - suppressed LawOfDemeter
    }
}
