/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import knowledgeevents.Circle;
import knowledgeevents.Square;

/**
 *
 * @author iamrp
 */
public class DroolsClient {
    
    public static void main(String[] args)
    {
        try{
                
                Random r1 = new Random();
                Random r2 = new Random();
                int count=0;
                while(true)
                {
                    Socket s = new Socket("127.0.0.1",12345);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    if (count%2 ==0)
                        oos.writeObject(new Circle(r1.nextInt(50)));
                    else
                        oos.writeObject(new Square(r1.nextInt(10),r2.nextInt(20)));
                    
                    oos.flush();
                    count++;
                    if(count >=1000)
                        count=0;
                    Thread.sleep(2000);
                    s.close();
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
