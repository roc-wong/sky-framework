package org.sky.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 *
 * @author roc
 * @date 2018/01/09
 */
public class JPushServiceTest {

    protected static final Logger LOG = LoggerFactory.getLogger(JPushServiceTest.class);

    private static final String APP_KEY = "78cddf9f1379524da3f0cb00";
    private static final String MASTER_SECRET = "e34d26dc12a1c6317e9c8f42";

    protected static final String GROUP_MASTER_SECRET = "b11314807507e2bcfdeebe2e";
    protected static final String GROUP_PUSH_KEY = "2c88a01e073a0fe4fc7b167c";

    public static final String ALERT = "JPush Test - alert";
    public static final String MSG_CONTENT = "JPush Test - msgContent";

    public static final String REGISTRATION_ID1 = "0900e8d85ef";
    public static final String REGISTRATION_ID2 = "0a04ad7d8b4";
    public static final String REGISTRATION_ID3 = "18071adc030dcba91c0";

    @Test
    public void testNettyClient() {

        ClientConfig clientConfig = ClientConfig.getInstance();

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        /*String authCode = ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET);
        ApacheHttpClient apacheHttpClient = new (authCode, null, clientConfig);
        jpushClient.getPushClient().setHttpClient(apacheHttpClienApacheHttpClientt);
        */
        PushPayload payload = buildTestPayload();
        try {
            PushResult result = jpushClient.sendPush(payload);
            int status = result.getResponseCode();
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public PushPayload buildTestPayload() {
        String title = "hello world, roc";
        String subTitle = "低头前行了这么久，我只是在找一个抬头的机会";
        String body = "人这一生，浪费了太多的时间在毫无意义的事情上，担忧、抱怨、埋怨、比较……";

        IosAlert iosAlert = IosAlert.newBuilder()
                .setTitleAndBody(title, subTitle, body)
                .setTitleLoc("").build();

        //ios notification
        IosNotification iosNotification = IosNotification.newBuilder()
                .setAlert(iosAlert).incrBadge(1)
                .setSound("happy").build();

        Notification notification = Notification.newBuilder().addPlatformNotification(iosNotification).build();

        PushPayload pushPayload = PushPayload
                .newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(
                        Audience.newBuilder()
                                .addAudienceTarget(
                                        AudienceTarget.alias("HB303644"))
                                .build())
                .setNotification(notification)
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();

        return pushPayload;
    }

}