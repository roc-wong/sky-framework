package org.sky.framework.test.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * Description:
 * Created by roc on 5/31/2017.
 */
public class QuotationMessageTest {

	@Test
	public void testSimpleMessage(){
		QuotationMessage.QuotationPack.Builder builder = QuotationMessage.QuotationPack.newBuilder();

		builder.setSecuType(QuotationMessage.QuotationPack.SecuType.SH_Index);
		builder.setTradingDate("2017-05-15").setSecuType(QuotationMessage.QuotationPack.SecuType.SH_Index);

		QuotationMessage.QuotationPack.Quotation.Builder quotationBuilder = QuotationMessage.QuotationPack.Quotation.newBuilder();
		QuotationMessage.QuotationPack.Quotation quotation1 = quotationBuilder.addValue("1.98").addValue("12").addValue("0.14").build();

		QuotationMessage.QuotationPack.Quotation.Builder quotationBuilder2 = QuotationMessage.QuotationPack.Quotation.newBuilder();
		QuotationMessage.QuotationPack.Quotation quotation2 = quotationBuilder2.addValue("10.98").addValue("13").addValue("0.19").build();

		builder.addQuotes(quotation1);
		builder.addQuotes(quotation2);

		QuotationMessage.QuotationPack quotationPack = builder.build();

		int length = quotationPack.getSerializedSize();
		System.out.println("The result length is " + length);
		byte[] buf = quotationPack.toByteArray();

		try {
			QuotationMessage.QuotationPack reqMessage =  QuotationMessage.QuotationPack.parseFrom(buf);
			System.out.println(reqMessage);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

	}
}
