package com.chenxushao.guava.joiner;
import java.util.Arrays;

import com.google.common.base.Joiner;

public class JoinerDemo3 {
   public static void main(String args[]){
      JoinerDemo3 tester = new JoinerDemo3();
      tester.testJoiner();	
   }

   private void testJoiner(){
      System.out.println(Joiner.on(",")
         .skipNulls()
         .join(Arrays.asList(1,2,3,4,5,null,6)));
   }
}