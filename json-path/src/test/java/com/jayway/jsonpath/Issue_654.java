package com.jayway.jsonpath;

import org.junit.Test;


/**
 * test for issue_806
 */
public class Issue_654 { //NOPMD - suppressed AtLeastOneConstructor
    // - TODO explain reason for suppression// NOPMD - suppressed ClassNamingConventions -
    //  TODO explain reason for suppression

    /**
     * CONF. configuration with FILTER_SLICE_AS_ARRAY
     */
    public static final Configuration CONF = Configuration.builder().options(Option.FILTER_AS_ARRAY).build();
    //NOPMD - suppressed FieldNamingConventions - TODO explain reason for suppression
    // NOPMD - suppressed VariableNamingConventions - TODO explain reason for suppression

    /**
     * JSON. given json
     */
    public static final String JSON = "[\n" + //NOPMD - suppressed FieldNamingConventions
            // - TODO explain reason for suppression //NOPMD - suppressed VariableNamingConventions
            //  - TODO explain reason for suppression
            "    [0, 1, 2], \n"
            + "    [3, 4, 5],\n"
            + "    [6, 7, 8],\n"
            + "    [9, 10, 11],\n"
            + "    [12, 13, 14]\n"
            + "]";


    @Test
    public void test1() { //NOPMD - suppressed JUnitTestsShouldIncludeAssert - TODO explain reason for suppression
        // find the first array that its first element is greater than 4
        Object resOri = JsonPath.parse(JSON).read("$[?(@[0] > 4)][0]"); //NOPMD - suppressed LocalVariableCouldBeFinal
        // - TODO explain reason for suppression //NOPMD - suppressed LawOfDemeter - TODO explain reason for suppression
        //NOPMD - suppressed VariableNamingConventions - TODO explain reason for suppression
        Object resNew = JsonPath.using(CONF).parse(JSON).read("$[?(@[0] > 4)][0]"); //NOPMD - suppressed LocalVariableCouldBeFinal
        // - TODO explain reason for suppression
        //NOPMD - suppressed VariableNamingConventions - TODO explain reason for suppression
        assert (resOri.toString().equals("[6,9,12]")); //NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        // NOPMD - suppressed UselessParentheses - TODO explain reason for suppression
        // origin mode will use INDEX_AT(0) operation on [6, 7, 8], [9, 10, 11], [12, 13, 14] respectively; //NOPMD - suppressed CommentSize
        // - TODO explain reason for suppression
        // NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        assert (resNew.toString().equals("[[6,7,8]]")); //NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        // NOPMD - suppressed UselessParentheses - TODO explain reason for suppression
        // new mode use INDEX_AT(0) operation on ([6, 7, 8], [9, 10, 11], [12, 13, 14])
        // NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
    }

    @Test
    public void test2() { //NOPMD - suppressed JUnitTestsShouldIncludeAssert - TODO explain reason for suppression
        // find the count of the elements whose first element is greater than 4
        Object resOri = JsonPath.parse(JSON).read("$[?(@[0] > 4)].length()"); //NOPMD - suppressed LocalVariableCouldBeFinal
        // - TODO explain reason for suppression
        //NOPMD - suppressed VariableNamingConventions - TODO explain reason for suppression
        Object resNew = JsonPath.using(CONF).parse(JSON).read("$[?(@[0] > 4)].length()"); //NOPMD - suppressed LocalVariableCouldBeFinal
        // - TODO explain reason for suppression
        //NOPMD - suppressed VariableNamingConventions - TODO explain reason for suppression
        assert (resOri.toString().equals("[3,3,3]")); //NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        // NOPMD - suppressed UselessParentheses - TODO explain reason for suppression
        // origin mode will use length() function on [6, 7, 8], [9, 10, 11], [12, 13, 14] respectively; //NOPMD - suppressed CommentSize
        // - TODO explain reason for suppression
        // NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        assert (resNew.toString().equals("[3]")); //NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
        // NOPMD - suppressed UselessParentheses - TODO explain reason for suppression
        // new mode use length() function on ([6, 7, 8], [9, 10, 11], [12, 13, 14])
        // NOPMD - suppressed LiteralsFirstInComparisons - TODO explain reason for suppression
    }
}
