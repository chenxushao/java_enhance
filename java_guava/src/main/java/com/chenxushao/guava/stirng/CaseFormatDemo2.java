package com.chenxushao.guava.stirng;
import com.google.common.base.CaseFormat;

public class CaseFormatDemo2 {
   public static void main(String args[]){
      CaseFormatDemo2 tester = new CaseFormatDemo2();
      tester.testCaseFormat();
   }

   private void testCaseFormat(){
//      String data = "test_data";
      System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
      System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
      System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
   }
}