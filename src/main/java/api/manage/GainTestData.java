package api.manage;

import utils.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GainTestData
 * @Author AissEr
 * @Date 2022/10/30 19:32
 * @Version 1.0
 * @Description TODO
 **/
public class GainTestData implements TestData<List<Map<String,Object>>> {
    private File testDataFile;
    private List<Map<String,Object>> testData;

    public GainTestData(String filePath){
        testDataFile = new File(PathUtil.realPath(filePath));
    }

    public GainTestData(File file){
        testDataFile = file;
    }

    @Override
    public List<Map<String, Object>> getTestData() {
        loadTestData();
        return testData;
    }

    private void loadTestData(){
        if(testData != null){
            try {
                testData = (List<Map<String,Object>>)MAPPER.readValue(testDataFile, CONVERT_TYPE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
