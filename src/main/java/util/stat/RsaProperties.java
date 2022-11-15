package util.stat;

import util.MapUtil;
import util.ReadFileUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName RsaProperties
 * @Author AissEr
 * @Date 2022/10/11 23:27
 * @Version 1.0
 * @Description TODO
 **/
public class RsaProperties {
    private static String privateKey;
    private static String publicKey;

    /**
     * 自动读取配置文件的私钥和公钥
     * 方便调用并进行测试
     */
    static {
        try{
            Map yaml = ReadFileUtil.readYamlToMap("test:application/ras.yaml");
            privateKey = (String) MapUtil.readMapByPoint(yaml,"ras.private_key");
            publicKey = (String) MapUtil.readMapByPoint(yaml,"ras.public_key");
        }catch (IOException e){
            privateKey = null;
            publicKey = null;
            e.printStackTrace();
        }
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static void setPublicKey(String publicKey) {
        RsaProperties.publicKey = publicKey;
    }
}
