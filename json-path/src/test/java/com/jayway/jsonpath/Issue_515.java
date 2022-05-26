package com.jayway.jsonpath;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.fail;

/**
 * test for issue 515
 */
//CS304 (manually written) Issue link: https://github.com/json-path/JsonPath/issues/515 //NOPMD - suppressed CommentSize

public class Issue_515 { //NOPMD - suppressed AtLeastOneConstructor //NOPMD - suppressed ClassNamingConventions
    @Test
    public void documentContextSerializable1() { //NOPMD - suppressed CommentRequired
        // construct a json
        final String json = "{\n"
                + "    \"store\": [\n"
                + "        {\n"
                + "            \"books\": [\n"
                + "                {\n" //NOPMD - suppressed AvoidDuplicateLiterals
                + "                    \"category\": \"reference\",\n"
                + "                    \"author\": \"Nigel Rees\",\n"
                + "                    \"title\": \"Sayings of the Century\",\n"
                + "                    \"price\": 8.95\n"
                + "                }\n"
                + "            ],\n"
                + "            \"address\": [\n"
                + "                {\n"
                + "                    \"city\": \"New York\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"city\": \"Paris\"\n"
                + "                }\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"books\": [\n"
                + "                {\n"
                + "                    \"category\": \"fiction\",\n"
                + "                    \"author\": \"Evelyn Waugh\",\n"
                + "                    \"title\": \"Sword of Honour\",\n"
                + "                    \"price\": 12.99\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        final DocumentContext originContext = JsonPath.parse(json);
        boolean equal = true; //NOPMD - suppressed DataflowAnomalyAnalysis
        try {
            //write into a file and read it
            final FileOutputStream fos = new FileOutputStream("obj.txt"); //NOPMD - suppressed AvoidDuplicateLiterals //NOPMD - suppressed CloseResource //NOPMD - suppressed AvoidFileStream
            final ObjectOutputStream outputStream = new ObjectOutputStream(fos); //NOPMD - suppressed CloseResource
            outputStream.writeObject(originContext);
            outputStream.close();
            fos.close();
            final FileInputStream ios = new FileInputStream("obj.txt"); //NOPMD - suppressed CloseResource //NOPMD - suppressed AvoidFileStream
            final ObjectInputStream inputStream = new ObjectInputStream(ios); //NOPMD - suppressed CloseResource
            final DocumentContext inputObj = (DocumentContext) inputStream.readObject();
            final Field[] inputFields = inputObj.getClass().getDeclaredFields(); //NOPMD - suppressed LawOfDemeter
            final Field[] originFields = originContext.getClass().getDeclaredFields(); //NOPMD - suppressed LawOfDemeter
            ios.close();
            inputStream.close();
            if (inputFields.length == originFields.length) {
                for (int i = 0; i < inputFields.length; i++) {
                    inputFields[i].setAccessible(true);
                    originFields[i].setAccessible(true);
                    if (!inputFields[i].get(inputObj).equals(originFields[i].get(originContext))) { //NOPMD - suppressed LawOfDemeter
                        equal = false; //NOPMD - suppressed DataflowAnomalyAnalysis
                        break;
                    }
                }
            } else {
                equal = false; //NOPMD - suppressed DataflowAnomalyAnalysis
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
            fail("fail");
        } finally {
            final File file = new File("obj.txt");
            final boolean del = file.delete();
            if (!del) {
                equal = false;
            }
        }
        Assert.assertTrue("file delete", equal);
    }

    @Test
    public void documentContextSerializable2() { //NOPMD - suppressed CommentRequired
        // construct an emtpy json
        final String json = "{}"; //NOPMD - suppressed AvoidFinalLocalVariable
        final DocumentContext originContext = JsonPath.parse(json);
        boolean equal = true; //NOPMD - suppressed DataflowAnomalyAnalysis //NOPMD - suppressed DataflowAnomalyAnalysis
        try {
            final FileOutputStream fos = new FileOutputStream("obj.txt"); //NOPMD - suppressed CloseResource //NOPMD - suppressed AvoidFileStream
            final ObjectOutputStream outputStream = new ObjectOutputStream(fos); //NOPMD - suppressed CloseResource
            outputStream.writeObject(originContext);
            outputStream.close();
            fos.close();
            final FileInputStream ios = new FileInputStream("obj.txt"); //NOPMD - suppressed CloseResource //NOPMD - suppressed AvoidFileStream
            final ObjectInputStream inputStream = new ObjectInputStream(ios); //NOPMD - suppressed CloseResource
            final DocumentContext inputObj = (DocumentContext) inputStream.readObject();
            final Field[] inputFields = inputObj.getClass().getDeclaredFields(); //NOPMD - suppressed LawOfDemeter
            final Field[] originFields = originContext.getClass().getDeclaredFields(); //NOPMD - suppressed LawOfDemeter
            ios.close();
            inputStream.close();
            if (inputFields.length == originFields.length) {
                for (int i = 0; i < inputFields.length; i++) {
                    inputFields[i].setAccessible(true);
                    originFields[i].setAccessible(true);
                    if (!inputFields[i].get(inputObj).equals(originFields[i].get(originContext))) { //NOPMD - suppressed LawOfDemeter
                        equal = false; //NOPMD - suppressed DataflowAnomalyAnalysis
                        break;
                    }
                }
            } else {
                equal = false; //NOPMD - suppressed DataflowAnomalyAnalysis
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
            fail("fail");
        } finally {
            final File file = new File("obj.txt");
            final boolean del = file.delete();
            if (!del) {
                equal = false;
            }
        }
        Assert.assertTrue("file delete", equal);
    }
}
