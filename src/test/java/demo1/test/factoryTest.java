package demo1.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wyq.demo.service.AwardReq;
import org.wyq.demo.service.AwardRes;
import org.wyq.demo.service.PrizeController;
import org.wyq.demo.serviceByFactory.ICommodity;
import org.wyq.demo.serviceByFactory.StoreFactory;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"all"})
public class factoryTest {

    private Logger logger = LoggerFactory.getLogger(factoryTest.class);
    @Test
    public void test_awardToUser() {

        PrizeController prizeController = new PrizeController();

        System.out.println("\r\n模拟发放优惠券测试\r\n");
        // 模拟发放优惠券测试
        AwardReq req01 = new AwardReq();
        req01.setuId("10001");
        req01.setAwardType(1);
        req01.setAwardNumber("EGM1023938910232121323432");
        req01.setBizId("791098764902132");
        AwardRes awardRes01 = prizeController.awardToUser(req01);

        logger.info("请求参数：{}", JSON.toJSON(req01));
        logger.info("测试结果：{}", JSON.toJSON(awardRes01));

        System.out.println("\r\n模拟方法实物商品\r\n");
        // 模拟方法实物商品
        AwardReq req02 = new AwardReq();
        req02.setuId("10001");
        req02.setAwardType(2);
        req02.setAwardNumber("9820198721311");
        req02.setBizId("1023000020112221113");
        req02.setExtMap(new HashMap<String, String>() {{
            put("consigneeUserName", "谢飞机");
            put("consigneeUserPhone", "15200292123");
            put("consigneeUserAddress", "吉林省.长春市.双阳区.XX街道.檀溪苑小区.#18-2109");
        }});

        AwardRes awardRes02 = prizeController.awardToUser(req02);
        logger.info("请求参数：{}", JSON.toJSON(req02));
        logger.info("测试结果：{}", JSON.toJSON(awardRes02));

        System.out.println("\r\n第三方兑换卡(爱奇艺)\r\n");
        AwardReq req03 = new AwardReq();
        req03.setuId("10001");
        req03.setAwardType(3);
        req03.setAwardNumber("AQY1xjkUodl8LO975GdfrYUio");

        AwardRes awardRes03 = prizeController.awardToUser(req03);
        logger.info("请求参数：{}", JSON.toJSON(req03));
        logger.info("测试结果：{}", JSON.toJSON(awardRes03));

    }
    @Test
    public void test() throws Exception {
        StoreFactory storeFactory = new StoreFactory();
        ICommodity commodityService1 = storeFactory.getCommodityService(1);
        ICommodity commodityService2 = storeFactory.getCommodityService(2);
        ICommodity commodityService3 = storeFactory.getCommodityService(3);

        commodityService1.sendCommodity("10001", "EGM1023938910232121323432",
                "791098764902132", null);


        Map<String,String> extMap = new HashMap<String,String>();
        extMap.put("consigneeUserName", "谢⻜机");
        extMap.put("consigneeUserPhone", "15200292123");
        extMap.put("consigneeUserAddress", "吉林省.⻓春市.双阳区.XX街道.檀溪苑⼩ 区.#18-2109");
        commodityService2.sendCommodity("10001","9820198721311","102300002011222 1113", extMap);

        commodityService3.sendCommodity("10001","AQY1xjkUodl8LO975GdfrYUio",null,null);
    }

}
