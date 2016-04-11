package org;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import knowledgeevents.Circle;
import knowledgeevents.Square;

public class DroolsWithoutDynamicRules {

	public static void main(String[] args) {
		 try{
             
             Random r1 = new Random();
             Random r2 = new Random();
             System.out.println("File:"+JDrools.class.getResource("JDrools.class").getPath().replace("/target/classes/org/JDrools.class", "/src/main/java/org/basic.drl"));
             Drooler drools = new Drooler(new String[]{JDrools.class.getResource("JDrools.class").getPath().replace("/target/classes/org/JDrools.class", "/src/main/java/org/basic.drl")});
             int count=0;
             while(true)
             {
                 
                 if (count%2 ==0)
                     drools.getKsession().execute(new Circle(r1.nextInt(50)));
                 else
                	 drools.getKsession().execute(new Square(r1.nextInt(10),r2.nextInt(20)));
                 count++;
             }
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }


	}

}
