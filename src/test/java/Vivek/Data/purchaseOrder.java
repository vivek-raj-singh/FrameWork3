package Vivek.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
public class purchaseOrder {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//Vivek//Data//purchaseData.json"));
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;

    }
}
