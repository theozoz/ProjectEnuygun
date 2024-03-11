package com.enuygun.utilies;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.gauge.Gauge;
import com.enuygun.model.ElementInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.fail;

public class LocatorUtility {
    String directoryPath = "src/test/resources/elements";
    ConcurrentMap<String, Object> elementListMap;
    private List<JsonObject> jsonDataList = new ArrayList<>();

    LocatorUtility() {
        setInitMap(getJsonFilePath());
    }
    public void setInitMap(List<File> fileList) {

        List<ElementInfo> elementInfoList = null;
        elementListMap = new ConcurrentHashMap<String, Object>();
        File file = null;
        Gson gson = new Gson();
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();


        for (int i = 0; i < fileList.size(); i++) {
            file = fileList.get(i);
            int fileNameListIndex = i;
            try {
                elementInfoList = gson.fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> setElementListMap(elementInfo, fileNameListIndex));
            }catch (FileNotFoundException e){
                Gauge.writeMessage(String.valueOf(e));
            }

        }
    }

    public List<File> getJsonFilePath() {

        File path = new File(directoryPath);
        File[] files = path.listFiles();
        List<File> jsonFiles = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    jsonFiles.add(file);
                }
            }
        }

        return jsonFiles;
    }

    private void setElementListMap(ElementInfo elementInfo, int fileNameListIndex) {

        elementInfo.setFileNameIndex(fileNameListIndex);
        elementListMap.put(elementInfo.getKey(), elementInfo);
    }

    public ElementInfo findElementInfoByKey(String key) {

        if (!elementListMap.containsKey(key)) {
            fail(key + " Element with name not found among existing elements in json files. Please check..");
        }
        return (ElementInfo) elementListMap.get(key);
    }

}


