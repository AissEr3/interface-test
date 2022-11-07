package eladmin.api.usermanage;

import api.SetRestAssured;
import base.BaseInterface;
import base.InterfaceTest;

import java.util.Map;

/**
 * @ClassName QueryUserInterface
 * @Author AissEr
 * @Date 2022/11/7 19:58
 * @Version 1.0
 * @Description TODO
 **/
public class QueryUserInterface extends BaseInterface<Map<String, Object>> {

    public QueryUserInterface(String interfaceConfigureFilePath){
        super(interfaceConfigureFilePath);
    }

    @Override
    protected void configureGiven() {
        given = SetRestAssured.startSet()
                .defaultContentType()
                .defaultHeaders()
                .defaultCookies()
                .endSet();
    }
}
