package api.configure;

import api.ApiObject;
import utils.ReadFileUtil;

import java.io.IOException;

/**
 * @ClassName BaseConfigure
 * @Author AissEr
 * @Date 2022/10/20 22:14
 * @Version 1.0
 * @Description TODO
 **/
public class BaseConfigure extends GeneralConfigure{
    private static final String DEFAULT_APPLICATION_FILE_PATH = "test:application/application-test.yaml";

    private static BaseConfigure instance = new BaseConfigure();

    public static String APPLICATION_FILE_PATH;

    private BaseConfigure(){

    }

    public BaseConfigure getInstance(){
        return instance;
    }

    @Override
    protected void initApplicationMap(){
        String realPath = DEFAULT_APPLICATION_FILE_PATH;
        if(APPLICATION_FILE_PATH != null || !APPLICATION_FILE_PATH.equals("")){
            realPath = APPLICATION_FILE_PATH;
        }
        try {
            applicationMap = ReadFileUtil.readYamlToMap(realPath);
        }catch (IOException e){
           e.printStackTrace();
        }
    }

    @Override
    public void configure(){
        String[] items = ApiObject.getConfigurationItem();
        for(String item : items){

        }
    }

    @Override
    protected void initDefaultLoginMessage() {

    }

    private void initBase(){

    }

    private void initDefaultHeaders(){

    }

    private void initDefaultCookies(){

    }
}
