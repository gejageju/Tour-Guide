import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*; 
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;

class appli implements ActionListener
{   String loc,des,mode;
    public void actionPerformed(ActionEvent e){ 
       if(e.getSource()==submit)
       {   JLabel disp=new JLabel("Fetching data...");
           disp.setBounds(50,300,150,20);
           f.add(disp);
           try{
           FileOutputStream fout=new FileOutputStream("appdata.txt");
           System.out.println("Pressed");
          
         
           loc=city_list[sp.getSelectedIndex()];
           fout.write(loc.getBytes());
           fout.write('\n');
           des=city_list[dest.getSelectedIndex()];
           fout.write(des.getBytes());
           fout.write('\n');
           mode=  mode_list[ optionlist.getSelectedIndex()];
           fout.write(mode.getBytes());
           double loc_data[]=fetch_data(loc);
           double des_data[]=fetch_data(des);
         /*  for (int i=0;i<2;i++)
           {
               System.out.println(loc_data[i]);
               System.out.println(des_data[i]);
           }  */
           
           Routes r=new Routes(loc_data[0],loc_data[1],des_data[0],des_data[1]); //In ex4.java
           System.out.println(r.calc());

           System.out.println("Done");
              }
          catch(Exception ex){}
       } 
    }
    
    double[] fetch_data(String s)
    {   System.out.println("HEllo");
        String key;
        double lat,lon;
        double val[]=new double[2];
        try{
       
        //File file= new File("coord.txt");
        FileReader f=new FileReader("coord.txt");
        BufferedReader fin= new BufferedReader(f);
        while( (key=fin.readLine())!=null)
        {  
            if(key.equals(s))
            {
                lat=Double.parseDouble(fin.readLine());
                System.out.println(lat);
                val[0]=lat;
                lon=Double.parseDouble(fin.readLine());
                System.out.println(lon);
                val[1]=lon;
            } 
        }
        fin.close();
        }
        catch(Exception x)
        {}
        return val;
    }
    
    JButton submit = new JButton("Calculate"); 
    JFrame f;
    JComboBox optionlist;
    String[] mode_list={"Car","Train","Bus","Motor Bike"};
    String[] city_list =new String[48];
    JComboBox sp;
    JComboBox dest;
    File file;
    BufferedReader br;

   appli() 
    {  
    f= new JFrame("Label Example");  
    JLabel l1,l2,l3,l4;  
    
    l1=new JLabel("Enter Start point: ");  
    l1.setBounds(50,25, 150,20);  
    
    l2=new JLabel("Enter destination: ");  
    l2.setBounds(50,75, 150,20);  
    f.add(l1); f.add(l2);  
    
    
    l3=new JLabel("Select Mode of Transport: ");  
    l3.setBounds(50,125, 150,20);
    f.add(l3);

    
    optionlist= new JComboBox(mode_list);
    optionlist.setBounds(50,150,150,20);
    optionlist.setSelectedIndex(0);
    f.add(optionlist);
    optionlist.addActionListener(this);
    
    
    submit.addActionListener(this);
    submit.setBounds(300,300,150,30);
    f.add(submit);



    try{
        file = new File("CityData.txt");
        br= new BufferedReader(new FileReader(file));
        String st;
        int i=0;
        while ((st = br.readLine()) != null)
         {   city_list[i]=st;
             i++;
         }  

    }
    catch(Exception enn){}
    
    
    sp=new JComboBox(city_list);  
    sp.setBounds(50,50, 150,20);
    sp.setEditable(true); 
    f.add(sp);
    sp.addActionListener(this);  
    
    dest=new JComboBox(city_list);  
    dest.setBounds(50,100, 150,20);
    dest.setEditable(true); 
    f.add(dest);
    //dest.ActionListener(this);
   
    f.setSize(500,500);  
    f.setLayout(null);  
    f.setVisible(true);  
     
    }
}

class Routes{
double lat1,long1,lat2,long2,dist;
Routes(double a,double b,double c,double d)
{
lat1=a;
long1=b;
lat2=c;
long2=d;
}
 double deg2rad(double deg) {
 return (deg * Math.PI / 180.0);
 }
 double rad2deg(double rad) {
 return (rad * 180.0 / Math.PI);
 }
 double calc()
 {
 double long_diff = long1 - long2;
 double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(long_diff));
 dist = Math.acos(dist); //acos->cos inverse
 dist = rad2deg(dist);
 dist = dist * 60 * 1.1515*1.609344;
 return Math.round(dist);
 }
}
class app{
    public static void main(String args[]) 
    {
        new appli();
    }
}
    

    
 
   
    
