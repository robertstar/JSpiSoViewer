/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

//import static org.junit.Assert.*;
import client.ThreadedUDPClient;
import data.Connection;
import data.Packet;
import data.PacketHandler;
import data.UID;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import server.ThreadedUDPServer;

import java.util.*;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;

import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class Test {

	private static ThreadedUDPClient apo_26100;
        private static ThreadedUDPClient apo_26010;
        
        static JTable  table1;
        
        static int pkt;
        
        static double PITCH;
        static double sign;
        static double tempd;
        //static byte val;
	
	// Заголовки столбцов
        private static Object[] columnsHeader = new String[] {  "№", 
                                                                "Модуль РСКПА-1 №1", 
                                                                "Модуль РСКПА-1 №2", 
                                                                "Модуль РСКПА-1 №3", 
                                                                "Модуль РСКПА-1 №4", 
                                                                "Модуль РСКПА-1 №5", 
                                                                "Модуль РСКПА-1 №6", 
                                                                "Модуль РСКПА-1 №7", 
                                                                "Модуль ДДиТ №1", 
                                                                "Модуль ДДиТ №2", 
                                                                "Модуль ДДиТ №3"
        };
        
        // Данные для таблиц
        private static Object[][] array = new String[][] {  {  "1", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "2", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "3", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "4", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "5", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "6", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "7", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "8", "", "", "", "", "", "", "", "", "", "" },
                                                            {  "9", "", "", "", "", "", "", "", "", "", "" },
                                                            { "10", "", "", "", "", "", "", "", "", "", "" },
                                                            { "11", "", "", "", "", "", "", "", "", "", "" },
                                                            { "12", "", "", "", "", "", "", "", "", "", "" },
                                                            { "13", "", "", "", "", "", "", "", "", "", "" },
                                                            { "14", "", "", "", "", "", "", "", "", "", "" },
                                                            { "15", "", "", "", "", "", "", "", "", "", "" },
                                                            { "16", "", "", "", "", "", "", "", "", "", "" },
                                                            { "17", "", "", "", "", "", "", "", "", "", "" },
                                                            { "18", "", "", "", "", "", "", "", "", "", "" },
        };

        public static void main(String[] args) {
		
            
            // create and configure the window
            JFrame window = new JFrame();
            window.setTitle("Программа просмотра параметров СПИ_СО: IP[192.168.5.3]");
            window.setSize(1750, 350);
            
            
            // Простая таблица
            table1 = new JTable(array, columnsHeader);
            table1.getColumnModel().getColumn(0).setMinWidth(30);
            table1.getColumnModel().getColumn(0).setMaxWidth(30); 
            
            table1.getColumnModel().getColumn(8).setMinWidth(120);
            table1.getColumnModel().getColumn(8).setMaxWidth(120); 
            
            table1.getColumnModel().getColumn(9).setMinWidth(120);
            table1.getColumnModel().getColumn(9).setMaxWidth(120); 
            
            table1.getColumnModel().getColumn(10).setMinWidth(120);
            table1.getColumnModel().getColumn(10).setMaxWidth(120); 
            
            //DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            //centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            //centerRenderer.setVerticalAlignment( JLabel.CENTER );
            //table1.setDefaultRenderer(String.class, centerRenderer);
            
            
            //DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
            //rendar.setHorizontalAlignment(JLabel.CENTER);
            //table1.getColumnModel().getColumn(0).setCellRenderer(rendar); 
            DefaultTableCellRenderer r = (DefaultTableCellRenderer) table1.getDefaultRenderer(String.class);
            //Выравнивание по горизонтали и вертикали 
            r.setHorizontalAlignment(JLabel.CENTER);
            // r.setVerticalAlignment(JLabel.TOP);
            

            Box contents = new Box(BoxLayout.Y_AXIS);
            contents.add(new JScrollPane(table1));


            window.setLayout(new BorderLayout());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Create our server and client intances
            //server = new ThreadedUDPServer(1337);
            apo_26100 = new ThreadedUDPClient("192.168.5.1", 25702);
            //apo_26010 = new ThreadedUDPClient("127.0.0.1", 26010);
                
            Date date = new Date();
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS"); 
		

		

            apo_26100.receive(new PacketHandler() {

                    @Override
                    public void process(Packet packet) {

                            byte[] data = packet.getData();
                            //pkt = ((data[3] << 24)) | ((data[2] << 16)) | ((data[1] << 8)) | (data[0]   &0x00FF);
                            //table1.setValueAt(Integer.toString(pkt), 0, 1);

                            int num=4;
                            
                            for(int j=0, c=0; j < 18; j++, c++) {
                            for(int i=0, t=1; i < 7; i++, t++) {
                                //*****************************************************************************************************//
                                //PITCH
                                String str  = Integer.toHexString(data[num] & 0xFF); 
                                int val_int=0;
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }

                                switch (val_int) {
                                    case 0: 
                                        PITCH = 0;
                                        sign  = 1;
                                        break;
                                    case 1:
                                        PITCH = 100;
                                        sign  = 1;
                                        break;
                                    case 2: 
                                        PITCH = 200;
                                        sign  = 1;
                                        break;
                                    case 3: 
                                        PITCH = 300;
                                        sign  = 1;
                                        break;  
                                    case 10:
                                        PITCH = 0;
                                        sign  = -1;
                                        break;
                                    case 11:
                                        PITCH = 100;
                                        sign  = -1;
                                        break;
                                    case 12: 
                                        PITCH = 200;
                                        sign  = -1;
                                        break;
                                    case 13:
                                        PITCH = 300;
                                        sign  = -1;
                                        break;
                                    default:
                                        break;
                                }

                                str  = Integer.toHexString(data[num+1] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = (PITCH + tempd);
                                str  = Integer.toHexString(data[num+2] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = sign * (PITCH + (tempd/100));
                                String result1 = String.format("%.2f",PITCH);
                                
                                //*****************************************************************************************************//
                                //ROLL
                                str  = Integer.toHexString(data[num+3] & 0xFF); 
                                val_int=0;
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }

                                switch (val_int) {
                                    case 0: 
                                        PITCH = 0;
                                        sign  = 1;
                                        break;
                                    case 1:
                                        PITCH = 100;
                                        sign  = 1;
                                        break;
                                    case 2: 
                                        PITCH = 200;
                                        sign  = 1;
                                        break;
                                    case 3: 
                                        PITCH = 300;
                                        sign  = 1;
                                        break;  
                                    case 10:
                                        PITCH = 0;
                                        sign  = -1;
                                        break;
                                    case 11:
                                        PITCH = 100;
                                        sign  = -1;
                                        break;
                                    case 12: 
                                        PITCH = 200;
                                        sign  = -1;
                                        break;
                                    case 13:
                                        PITCH = 300;
                                        sign  = -1;
                                        break;
                                    default:
                                        break;
                                }

                                str  = Integer.toHexString(data[num+4] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = (PITCH + tempd);
                                str  = Integer.toHexString(data[num+5] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = sign * (PITCH + (tempd/100));
                                String result2 = String.format("%.2f",PITCH);
                                
                                //*****************************************************************************************************//
                                //HEADING
                                str  = Integer.toHexString(data[num+6] & 0xFF); 
                                val_int=0;
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }

                                switch (val_int) {
                                    case 0: 
                                        PITCH = 0;
                                        sign  = 1;
                                        break;
                                    case 1:
                                        PITCH = 100;
                                        sign  = 1;
                                        break;
                                    case 2: 
                                        PITCH = 200;
                                        sign  = 1;
                                        break;
                                    case 3: 
                                        PITCH = 300;
                                        sign  = 1;
                                        break;  
                                    case 10:
                                        PITCH = 0;
                                        sign  = -1;
                                        break;
                                    case 11:
                                        PITCH = 100;
                                        sign  = -1;
                                        break;
                                    case 12: 
                                        PITCH = 200;
                                        sign  = -1;
                                        break;
                                    case 13:
                                        PITCH = 300;
                                        sign  = -1;
                                        break;
                                    default:
                                        break;
                                }

                                str  = Integer.toHexString(data[num+7] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = (PITCH + tempd);
                                str  = Integer.toHexString(data[num+8] & 0xFF); 
                                try {
                                        val_int = Integer.parseInt(str.equals(" ") ? "0" : str) ;
                                  }catch(NumberFormatException e){
                                    e.printStackTrace() ; 
                                  }
                                tempd = val_int;
                                PITCH = sign * (PITCH + (tempd/100));
                                String result3 = String.format("%.2f",PITCH);
                                
                                table1.setValueAt(result1 + "\u00B0  " + result2 + "\u00B0  "+ result3 + "\u00B0  ", c, t);
                                num = num + 9;  
                            }
                                
                                //*****************************************************************************************************//
                                //DD1
                                int val_t=0, msb=0;
                                int temp=0;
                                msb = data[num];
                                //String str0  = Integer.toHexString(data[num] & 0xFF); 
                                //System.out.println("str0: " + str0);
                                //String str1  = Integer.toHexString(data[num+1] & 0xFF); 
                                //System.out.println("str1: " + str1);
                                //System.out.println("msb: " + msb);
                                //System.out.println("lsb: " + lsb);
                                
                                temp = ((data[num+1] & 0xF0) >> 4)*625;
                                //System.out.println("temp: " + temp);
                                int pressure=0;
                                pressure = ((data[num+2] << 8)) | (data[num+3]);
                                
                                String t1 = String.format("%d",msb);
                                String t2 = String.format("%d",temp/100);
                                String p1 = String.format("%d",pressure);
                                table1.setValueAt(t1+"."+t2 + "\u2103  " + p1, c, 8);
                                
                                //*****************************************************************************************************//
                                //DD2
                                temp=0;
                                msb  = data[num+4];
                                temp = ((data[num+5] & 0xF0) >> 4)*625;
                                pressure = 0;
                                pressure = ((data[num+6] << 8)) | (data[num+7]);
                                t1 = String.format("%d",msb);
                                t2 = String.format("%d",temp/100);
                                p1 = String.format("%d",pressure);
                                table1.setValueAt(t1+"."+t2 + "\u2103  " + p1, c, 9);
                                
                                                                //*****************************************************************************************************//
                                //DD3
                                temp=0;
                                msb  = data[num+8];
                                temp = ((data[num+9] & 0xF0) >> 4)*625;
                                pressure = 0;
                                pressure = ((data[num+10] << 8)) | (data[num+11]);
                                t1 = String.format("%d",msb);
                                t2 = String.format("%d",temp/100);
                                p1 = String.format("%d",pressure);
                                table1.setValueAt(t1+"."+t2 + "\u2103  " + p1, c, 10);
                            
                            num = num + 12; // 3 DDT
                            }
                            
                            


                            //*************************************************************************************************************************//
                            //Console
                            Date date = new Date();
                            System.out.println("[TIME:"+ sdf.format(date) +"]" + "[RX:25702]" +"[Len:" + data.length + "]" + "[DATA:" + byteToHexString(data) +"]");       
                    }

            });
            window.getContentPane().add(contents,BorderLayout.NORTH);
            window.setVisible(true);
            window.setLocationRelativeTo(null);
	}
        
// tests if bit is set in value
public static boolean isSet(byte value, int bit){
   return (value&(1<<bit))!=0;
}         

public static String byteToHexString(byte bytes[]) {
        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
          retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
                           .substring(1) + " ");
        }
        return retString.toString();
    }
	
//	public void reply(Packet packet) {
//		server.broadcast(new String(packet.toString()).getBytes());
//	}

}

















                            
                            
//                            int num = data[5];
//                            String str = Integer.toHexString(num);
//                            System.out.println("DATA3:" + str);
//                            int foo = Integer.parseInt(str);
//                            System.out.println("DATA4:" + foo);
                            
//                            if(isSet(data[4],7)){
//                                PITCH = -1.0 * PITCH;
//                            }
//                            
//                            if(isSet(data[4],0)){
//                                
//                            }
//                            if(isSet(data[4],1)){
//                                
//                            }

                            //sdf.format(date) +



                            //System.out.println("DATA:" + byteToHexString(data));
                            //String data = new String(packet.getData()).trim();
                            //System.out.println(data.trim());


//                apo_26010.receive(new PacketHandler() {
//
//			@Override
//			public void process(Packet packet) {
//
//                                byte[] data = packet.getData();
//
//                                //sdf.format(date) +
//                                Date date = new Date();
//                                System.out.println("[TIME:"+ sdf.format(date) +"]" + "[RX:26010]" +"[Len:" + data.length + "]" + "[DATA:" + byteToHexString(data) +"]");
//                                //System.out.println("DATA:" + byteToHexString(data));
//				//String data = new String(packet.getData()).trim();
//				//System.out.println(data.trim());
//			}
//			
//		});
		
		//client.send("CON".getBytes());


            // Set up a handler for receiving the packets
//            server.receive(new PacketHandler() {
//
//                    @Override
//                    public void process(Packet packet) {
//                            String data = new String(packet.getData()).trim();
//
//                            if(data.equals("CON")) {
//                                    ThreadedUDPServer.CLIENTS.add(packet.getConnection());
//                                    reply(new Packet("OK".getBytes(), packet.getAddr(), packet.getPort()));
//                            }
//                    }
//
//            });