package org.sky.framework.test.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * Description:
 * Created by roc on 5/31/2017.
 */
public class UserTest {


	@Test
	public void testSimpleMessage(){
		User.LogonReqMessage.Builder builder = User.LogonReqMessage.newBuilder();

		User.LogonReqMessage logonReqMessage = builder.setAcctID(1000).setPasswd("roc123456").setStatus(User.UserStatus.ONLINE).build();

		int length = logonReqMessage.getSerializedSize();
		System.out.println("The result length is " + length);
		byte[] buf = logonReqMessage.toByteArray();


		try {
			User.LogonReqMessage reqMessage =  User.LogonReqMessage.parseFrom(buf);
			System.out.println(reqMessage);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

	}


}
