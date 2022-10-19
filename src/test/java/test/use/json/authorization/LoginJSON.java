package test.use.json.authorization;

import lombok.Data;
import test.use.config.RsaProperties;
import test.use.utils.RsaUtil;

import java.io.Serializable;

/**
 * @ClassName LoginJSON
 * @Author AissEr
 * @Date 2022/10/16 0:01
 * @Version 1.0
 * @Description 发送登录请求的JSON格式
 **/
@Data
public class LoginJSON implements Serializable {
    // 跳过自动生成的uuid，跳过验证码验证
    private static final String TEST_BACK_DOOR = "hhd";

    // 加密的公钥
    private static final String PUBLIC_KEY = RsaProperties.getPublicKey();

    private String code;
    private String username;
    private String password;
    private String uuid;

    public LoginJSON(){
        uuid = TEST_BACK_DOOR;
        code = TEST_BACK_DOOR;
    }

    public LoginJSON(String username,String password){
        this();
        String rasPassword = null;
        try{
            rasPassword = RsaUtil.encryptByPublicKey(PUBLIC_KEY,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.username = username;
        this.password = rasPassword;
    }

    public LoginJSON(String username,String password,String code){
        this(username, password);
        this.code = code;
    }
}
