package com.chenxushao.java.basis.enums;

public class EnumTest {

	public static void main(String[] args) {
	        Customer c = Customer.VIP;
	        //enum方法使用
	        System.out.println(getCustomerType(c));
	        System.out.println(c.ordinal());


	        for ( Customer tempCustomer : Customer.values()){
	        	System.out.println(tempCustomer.name());
	        }
	        
	        System.out.println(Customer.valueOf("COMMON").name());
	        
	}

	
	private static String getCustomerType(Customer customer){
		return customer.name(); 
	}
}
