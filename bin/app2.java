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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



// Homepage
class homepage implements ActionListener 
{   
    //Class members
    JComboBox op;
    String[] city_list=new String[48];
    BufferedReader br;
    File file;
    String dest;
    JFrame f= new JFrame("Tour Guide");
    JLabel heading1,heading2,prompt;
    JButton go=new JButton("GO");
    
    
    //Constructor
    homepage()
    {

        f.setSize(1000,1000);
        f.setVisible(true);
        f.setLayout(null);
        f.setVisible(true);
        
        
        //Reading city names for the dropdown list
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


        //Welcome to travel guide
        heading1= new JLabel(" WELCOME TO ");
        heading1.setHorizontalAlignment(JLabel.CENTER);
        heading1.setBounds(0,0,1000,200);
        heading1.setFont(new Font("Serif", Font.PLAIN, 75));
        f.add(heading1);

        heading2= new JLabel("TOUR GUIDE");
        heading2.setHorizontalAlignment(JLabel.CENTER);
        heading2.setBounds(0,100,1000,200);
        heading2.setFont(new Font("Serif", Font.PLAIN, 75));
        f.add(heading2);
        
        //Choose destination
        prompt=new JLabel("Choose Destination ");
        prompt.setHorizontalAlignment(JLabel.CENTER);
        prompt.setBounds(0,250,1000,300);
        prompt.setFont(new Font("Serif", Font.PLAIN, 45));
        f.add(prompt);
       
        //Dropdown list
        op=new JComboBox(city_list);
        op.setBounds(300,500,400,75);
        op.setEditable(true); 
        System.out.println("HEllo");
        op.addActionListener(this); 
        op.setFont(new Font("Serif", Font.PLAIN, 35));
        f.add(op);

        // GO button
        go.setBounds(800,650,100,50);
        f.add(go);
        go.addActionListener(this);
        
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent e)
    {
          if(e.getSource()==go)
          {
              dest=city_list[op.getSelectedIndex()];
              System.out.println(dest);
              f.dispose();
              new Infopage(dest,city_list);

          }
    }
}

 //Infopage
 class Infopage implements ActionListener
 {
     JFrame g;
     String city;
     JLabel name;
     JTextArea info; 
     JButton travel;
     String[] city_list;
     BufferedReader br;
     File file;
     String text;

     Infopage(String destination, String[] city_list)
     {   this.city_list=city_list;
         g= new JFrame("Tour Guide");
         g.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         g.setSize(1000,1000);
         g.setVisible(true);
         g.setLayout(null);
         g.setVisible(true);
         //g.getContentPane().setBackground(Color.BLACK);
         city=destination;
         
         //Heading
         name= new JLabel("A Little about "+city+"'s History");
         name.setBounds(30,30,900,100);
         name.setFont(new Font("Serif", Font.PLAIN, 50));
         //name.setForeground(Color.WHITE);
         g.add(name);

         //Travel Button
         travel= new JButton("Travel");
         travel.setFont(new Font("Serif", Font.PLAIN, 30));
         travel.setBounds(650,750,250,100);
         g.add(travel);
         travel.addActionListener(this);

         //Reading paragraph
         try{
            file = new File("destdat.txt");
            br= new BufferedReader(new FileReader(file));
            String st;
            int i=0;
            while ((st = br.readLine()) != null)
             {   
                 if(st.equals(city))
                {   text=br.readLine();
                    System.out.println(text);
                    break;
                }
                 
             }  
    
        }
            catch(Exception enn){}
        
            //blitting paragraph
            info=new JTextArea(text);
            info.setBounds(35,150,900,500);
            info.setLineWrap(true);
            info.setBackground(g.getContentPane().getBackground());
            info.setFont(new Font("Serif", Font.PLAIN, 20));
            g.add(info);

         


         
         //g.getContentPane().add(new JPanelWithBackground("heart.png"));
         
     }

     public void actionPerformed(ActionEvent ae)
     {
          if(ae.getSource()==travel)
          {
              g.dispose();
              new travelpage(city_list,city);
          }
     }
 }

 class travelpage implements ActionListener{
    JFrame t;
    JComboBox box;
    JComboBox transp;
    JLabel prompt1;
    JLabel prompt2;
    String[] city_list;
    String[] mode_list={"Airplane","Car","Train","Bus","Motor Bike"};
    JButton done;
    JLabel dash;
    String dest,loc;
    JLabel pic;
    travelpage(String[] city_list,String city){

        //setting up window
         dest=city;
         this.city_list=city_list;
         t= new JFrame("Tour Guide");
         t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         t.setSize(1000,1000);
         t.setVisible(true);
         t.setLayout(null);
         t.setVisible(true);

         //Displaying map
         //MyCanvas m=new MyCanvas(dest);
         pic=new JLabel();
         pic.setBounds(425,50,500,500);
         
         ImageIcon img= new ImageIcon("D:/Java/Travel_guide/Tour_giude/images/"+city+".jpg");
         pic.setIcon(img);
         t.add(pic);
         //current location drop down list
         box= new JComboBox(city_list);
         box.setBounds(50,200,300,55);
         box.setEditable(true); 
         System.out.println("HEllo");
         box.addActionListener(this); 
         box.setFont(new Font("Serif", Font.PLAIN, 27));
         t.add(box);

        // mode of transport list
         transp=new JComboBox(mode_list);
         transp.setBounds(50,400,300,55);
         transp.setEditable(true); 
         System.out.println("HEllo");
         transp.addActionListener(this); 
         transp.setFont(new Font("Serif", Font.PLAIN, 27));
         t.add(transp);

        // Your curr loc prompt
        prompt1=new JLabel("Your current Location");
        prompt1.setFont(new Font("Serif", Font.PLAIN, 27));
        prompt1.setBounds(50,125,300,75);
        t.add(prompt1);
       
        //Transp mode prompt
        prompt2=new JLabel("Mode of Transportation");
        prompt2.setFont(new Font("Serif", Font.PLAIN, 27));
        prompt2.setBounds(50,325,300,75);
        t.add(prompt2);

        //Done button
        done=new JButton("Done");
        done.setFont(new Font("Serif", Font.PLAIN, 30));
        done.setBounds(50,500,100,50);
        t.add(done);
        done.addActionListener(this);
  
        //Line
        dash= new JLabel("___________________________________________________________________________________________________________________________________________");
        dash.setBounds(5,575,1000,50);
        t.add(dash);

        //t.add(m);

    }

    //Fetching lattitude and longitude details
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


    public void actionPerformed(ActionEvent w)
    {
        if(w.getSource()==done)
        {
           

            loc=city_list[box.getSelectedIndex()];
            System.out.println(loc+dest);

            //Calculating distance
            double loc_data[]=fetch_data(loc);
            double des_data[]=fetch_data(dest);
            Routes r=new Routes(loc_data[0],loc_data[1],des_data[0],des_data[1]); //In ex4.java
            double value=r.calc();

            //Estimated distance
            System.out.println("Done");
            JLabel dist=new JLabel("Estimated distance: "+value+ "kms.");
            dist.setBounds(50,675,600,75);
            dist.setFont(new Font("Serif", Font.PLAIN, 37));
            t.add(dist);

            //Estimated distance
            System.out.println("Done");
            JLabel dur=new JLabel("Estimated duration: "+value+ "kms.");
            dur.setBounds(50,750,600,75);
            dur.setFont(new Font("Serif", Font.PLAIN, 37));
            t.add(dur);

        }
    }
 }

   
class MyCanvas extends Canvas{  
    String city;
    Image image;
    BufferedImage bufferedImage;
    MyCanvas(String s)
    {
        city=s;
        try{
            bufferedImage = ImageIO.read(new File("D:/Java/Travel_guide/Tour_giude/images/"+city+".jpg"));
            image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            
            System.out.println("My canvas yo yo ");
            repaint();
            }
            catch(Exception a){} 
    }
    public void paint(Graphics g) {  
  
        g.drawImage(image, 100,100,this);  
        System.out.println("paint");
    }  
        
  
} 


class app2{
    public static void main(String args[])
    {
        new homepage();
        
    }

}