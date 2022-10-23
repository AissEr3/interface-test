package json.authorization;

import json.JsonBean;
import lombok.Data;
import utils.ReadFileUtil;
import utils.RsaUtil;
import utils.stat.RsaProperties;

import java.io.IOException;

/**
 * @ClassName LoginJSON
 * @Author AissEr
 * @Date 2022/10/16 0:01
 * @Version 1.0
 * @Description 发送登录请求的JSON格式
 **/
@Data
public class LoginJSON implements JsonBean {
    // 跳过自动生成的uuid，跳过验证码验证
    private static final String TEST_BACK_DOOR = "hhd";

    // 加密的公钥
    private static final String PUBLIC_KEY = RsaProperties.getPublicKey();

    private String code;
    private String username;
    private String password;
    private String uuid;

    private boolean isRas = false;

    public LoginJSON(){
        uuid = TEST_BACK_DOOR;
        code = TEST_BACK_DOOR;
    }

    public LoginJSON(String yamlPath) throws IOException {
        this();
        toJsonBean(yamlPath);
        toRasPassword(this.password);
    }

    public LoginJSON(String username,String password){
        this();
        this.username = username;
        this.password = password;
        toRasPassword(this.password);
    }

    public void toJsonBean(String yamlPath) throws IOException {
        ReadFileUtil.readFileToBean(yamlPath,this.getClass());
        toRasPassword(this.password);
    }

    private void toRasPassword(String password){
        try{
            this.password = RsaUtil.encryptByPublicKey(PUBLIC_KEY,password);
            isRas = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
