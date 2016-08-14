package com.chenxushao.java.basis.enums;




/******************************************************************************
 * 
 * <p>Description: sap接口类型</p> 
 * Project: job
 * Package: cn.com.gome.train.constant
 *    File: SapInterfaceType.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年10月9日 下午5:33:22
 * 
 *****************************************************************************/
public enum SapInterfaceType {
	
	PositiveSapReceipt(1,"正向SAP收款"),
	NegativeSapReceipt(2,"逆向SAP收款"),
	PositiveSapIncome(3,"正向SAP收入"),
	NegativeSapIncome(4,"逆向SAP收入"),
	SapIncomeCallback(5,"SAP收入回调");
	
	public final Integer TYPE;
	public final String DESC;
	
	 private SapInterfaceType(Integer type, String desc) {
		TYPE = type;
		DESC = desc;
	}
	 
	public static SapInterfaceType convert(Integer type) {
			if(type==null){
				return null;
			}
			SapInterfaceType[] types = SapInterfaceType.values();
			for (SapInterfaceType sapInterfaceType : types) {
				if (type.equals(sapInterfaceType.TYPE)) {
					return sapInterfaceType;
				}
			}
			//Default
			return null;
		}
}