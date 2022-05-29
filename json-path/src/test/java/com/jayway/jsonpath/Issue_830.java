// CS304 (manually written) Issue link: https://github.com/json-path/JsonPath/issues/830

package com.jayway.jsonpath;

import net.minidev.json.JSONArray;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Test for Issue 830
 */

public class Issue_830 { //NOPMD - suppressed AtLeastOneConstructor //NOPMD - suppressed ClassNamingConventions

    /**
     * The json String to test
     */

    private static final String JSON = "{\n"
            + "    \"some\": {\n"
            + "        \"nested\": {\n"
            + "            \"path\": [\n"
            + "                {\n"
            + "                    \"id\": 1,\n"
            + "                    \"name\": \"one\",\n"
            + "                    \"data\": {\n"
            + "                        \"field\": \"value\"\n"
            + "                    }\n"
            + "                },\n"
            + "                {\n"
            + "                    \"id\": 2,\n"
            + "                    \"name\": \"two\",\n"
            + "                    \"data\": {\n"
            + "                        \"needlessly\": {\n"
            + "                            \"nested\": {\n"
            + "                                \"field\": \"value\"\n"
            + "                            }\n"
            + "                        }\n"
            + "                    }\n"
            + "                }\n"
            + "            ]\n"
            + "        }\n"
            + "    }\n"
            + "}";

    /**
     * test for the new feature
     */
    // CS304 (manually written) Issue link: https://github.com/json-path/JsonPath/issues/830
    @Test
    public void testFeature1() {
        // create a InputSteam based on the json String
        try {
            final InputStream inputStream = new ByteArrayInputStream(JSON.getBytes("UTF-8"));
            final DocumentContext documentContext = JsonPath.parse(inputStream);
            // read the JsonPath to get JSONArray
            final JSONArray items = documentContext.read("$['some']['nested']['path'][*]"); //NOPMD - suppressed LawOfDemeter
            // New Feature: Use JsonPath to parse the JSONArray Object
            final List<DocumentContext> contextList = JsonPath.parse(items);
            final List<String> ans = new ArrayList<>();
            for (final DocumentContext context: contextList) {
                // For each item, we can also read JsonPath
                ans.add(context.read("$['data']").toString()); //NOPMD - suppressed LawOfDemeter
            }
            assertThat(ans.toString()).isEqualTo("[{field=value}, {needlessly={nested={field=value}}}]"); //NOPMD - suppressed LawOfDemeter
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace
        }

    }

    /**
     * test for the new feature
     */
    // CS304 (manually written) Issue link: https://github.com/json-path/JsonPath/issues/830
    @Test
    public void testFeature2() {
        // create a InputSteam based on the json String
        try {
            final InputStream inputStream = new ByteArrayInputStream(JSON.getBytes("UTF-8"));
            final DocumentContext documentContext = JsonPath.parse(inputStream);
            // read the JsonPath to get JSONArray
            final JSONArray items = documentContext.read("$['some']['nested']['path'][*]"); //NOPMD - suppressed LawOfDemeter
            // New Feature: Use JsonPath to parse the JSONArray Object
            final List<DocumentContext> contextList = JsonPath.parse(items);
            final List<String> ans = new ArrayList<>();
            for (final DocumentContext context: contextList) {
                // For each item, we can also read JsonPath
                ans.add(context.read("$['name']").toString()); //NOPMD - suppressed LawOfDemeter
            }
            assertThat(ans.toString()).isEqualTo("[one, two]"); //NOPMD - suppressed LawOfDemeter
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace
        }

    }
}
