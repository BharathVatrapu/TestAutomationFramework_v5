package com.testautomation.framework.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class JsonUtils {

    /**
     * description: Retun value from json format file
     * Author: BhaRatH
     * @param jsonObject : Json Object
     * @param structHead: Json Structurer header node
     * @param getobject: Return Json read object Key
     *

     */
    public String getValue(JSONObject jsonObject,String structHead,String getobject) {
        String returnValue=null;
        try {
            // handle a structure into the json object
            JSONObject structure = (JSONObject) jsonObject.get(structHead);
            returnValue = (String) structure.get(getobject);
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public JSONObject parseStringToJsonObject(String filePath){
        JSONObject jsonObject = null;
        try {
            // read the json file
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void getValues(JSONObject jsonObject,String structHead){
        JSONObject structure = (JSONObject) jsonObject.get(structHead);
        //returnValue = (String) structure.get(getobject);
        Set keys = structure.keySet();
        Iterator a = keys.iterator();
        while(a.hasNext()) {
            String key = (String)a.next();
            // loop to get the dynamic key
            String value = (String)structure.get(key);
            System.out.print("key : "+key);
            System.out.println(" value :"+value);
        }
    }
}
