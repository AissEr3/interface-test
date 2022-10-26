package api.configure;

import api.ApiObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import utils.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName InterfaceConfigure
 * @Author AissEr
 * @Date 2022/10/26 20:54
 * @Version 1.0
 * @Description TODO
 **/
public class InterfaceConfigure extends GeneralConfigure{
    private static ObjectMapper mapper = new YAMLMapper();
    private String configureFilePath;

    public InterfaceConfigure(){}

    public InterfaceConfigure(String path){
        configureFilePath = path;
    }

    @Override
    public void configure(ApiObject object) {

    }

    @Override
    protected void initApplicationMap() {
        try{
            applicationMap = mapper.readValue(new File(PathUtil.realPath(configureFilePath)),
                    new HashMap<String,Object>().getClass());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
