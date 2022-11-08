package eladmin.api.usermanage;

import api.SetRestAssured;
import common.BaseInterface;

/**
 * @ClassName QueryUserInterface
 * @Author AissEr
 * @Date 2022/11/7 19:58
 * @Version 1.0
 * @Description TODO
 **/
public class QueryUserInterface extends BaseInterface {

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
