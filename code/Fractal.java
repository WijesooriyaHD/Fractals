

			/* ............  E/17/407 ........................*/
			/*....... Wijesooriya H.D. ................... */


import java.awt.*; // import some inbuild classes
import java.awt.event.*; 
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;
import java.awt.Color;
import java.lang.Math; 


public class Fractal extends JPanel { // inherit JPanel
	
	// variable declaration
	public static double REAL,COMPLEX;
    private int width, height; 
    public static double up_real,low_real,up_complex,low_complex,CJ_real,CJ_complex;
    public static int iteration;
	public static int mandelbrot ;
	public static int julia;
	float Saturation = 1f;
	public static int inSet;//variable to check whether a point is in the set or not

	
    public Fractal(int width, int height) { //constructor
	// set the panel size 
	this.width  = width; 
	this.height = height; 
	setPreferredSize(new Dimension(width, height)); 
	}

   

    //method to plot the pattern with colours
    public void paintComponent(Graphics g) { 
	
    	//call paintComponent from parent class
    	super.paintComponent(g); 
    	Graphics2D g2 = (Graphics2D) g;

    	// checking for the Mandelbrot set
    	if(mandelbrot==1) {
    		
    		// loop through the pixels of the frame
    		for(int x=0;x<800;x++){
        		for(int y=0;y<800;y++){
        			
        			inSet=1; //assign 1 to inSet
        			
        			//mapping a point to the region of intersect 
        			REAL=low_real+(((up_real-low_real)*x)/800);
        			COMPLEX=up_complex-(((up_complex-low_complex)*y)/800);
        			
        			//creating  new objects from the class ComplexNumber
        			ComplexNumber c=new ComplexNumber(REAL,COMPLEX); // c 
        			ComplexNumber oldz=new ComplexNumber(); //oldz
        			ComplexNumber zn=new ComplexNumber(0,0);//initial zn value z0=0+0i
        			
        			int count;//counter variable
        			for(count=0;count<iteration;count++) { //do the given number of iterations for a point
        				
        				oldz=zn; //assign zn to oldz
        				zn=zn.square(); //square the value of zn , (zn=zn**2)
        				zn.add(c);  // add c to zn**2 , (zn=zn**2+c)
        				
        				if(oldz.modulus()>2) { //check the magitude of oldz 
        				
        					inSet=0;//assign 0 to  inSet ,point is not in the set
        					break; //break the loop
        							//point is not in the set
        					
        				}
        			}
        			
        			//handle the brightness of the colour depending on the iteration
        			float Brightness = count<iteration ? 1f : 0;
    				float Hue = (count%256)/255.0f;
    			
            		//inSet=0 if the point is not in the set	
            		if(inSet==0){
            			
            				g2.setColor( Color.getHSBColor(Hue, Saturation, Brightness));//set colour
            				g2.setStroke(new BasicStroke(1.2f));//handle the thickness of the line
            				g2.draw(new Line2D.Double(x, y, x, y));//colour the pixel 
            				
            		}else{
            			//if the point is in the set assign black
            				g2.setColor(Color.BLACK);
            				g2.setStroke( new BasicStroke(1.2f));
            				g2.draw(new Line2D.Double(x, y, x, y));
            			
            		}
    	
        			
        		}
    		}
    	}
    	
    	//check whether the given set is Julia or not
    	if(julia==1) {
    		//create the object C
    		ComplexNumber C=new ComplexNumber(CJ_real,CJ_complex);
    		
    		//loop through the pixels
    		for(int x=0;x<800;x++){
        		for(int y=0;y<800;y++){
        			
        			inSet=1; //assign 1 to inSet
        			
        			//mapping a point to the region of intersect
        			REAL=low_real+(((up_real-low_real)*x)/800);
        			COMPLEX=up_complex-(((up_complex-low_complex)*y)/800);
        			
        			//create the object zn
        			ComplexNumber zn=new ComplexNumber(REAL,COMPLEX); 
        			ComplexNumber oldz=new ComplexNumber(); //oldz
        			
        			int count;//counter variable
        			for(count=0;count<iteration;count++) {
        				
        				oldz=zn; //assign zn to oldz
        				zn=zn.square(); //square the value of zn (zn=zn**2)
        				zn.add(C);  // add C to zn**2 (zn=zn**2+C)
        				
        				if(oldz.modulus()>2) {//check whether the point is in the set or not
        					//if |oldz|>2 then it is not in the set
        					inSet=0;//assign 0 to inSet ,point is not in the set
        					break;//break the loop
        					
        				}
        				
        				
        			}

        			//assign brightness based on the number of iterations
        			float Brightness = count<iteration ? 1f : 0;
    				float Hue = (count%256)/255.0f;
    			
            		// inSet=0	if the point is not in the set
            		if(inSet==0){
            			//colour a pixel
            				g2.setColor( Color.getHSBColor(Hue, Saturation, Brightness));
            				g2.setStroke( new BasicStroke(1.2f));
            				g2.draw(new Line2D.Double(x, y, x, y));
            				
            		}else{
            			//if the point is in the set assign black to the pixel
            				g2.setColor(Color.BLACK);
            				g2.setStroke( new BasicStroke(1.2f));
            				g2.draw(new Line2D.Double(x, y, x, y));
            				
            		}
        		}
    		}
    		
    		
    	}

    }

    /*..................................................................................*/
	
	public static void main (String [] args) { // main method
		
		//initialize some variables
		
		// variables to check the set
		mandelbrot =0; 
		julia=0;
		
	// initializing default values for the region of intersect 
		
		up_real=1; //  upper bound of the real range
		low_real=-1; // lower bound of the real range
		up_complex=1; //upper bound of the complex range
		low_complex=-1;// lower bound of the complex range 
		
		// default value for C (for Julia set C=-0.4+0.6i)
		CJ_real=-0.4;  //real part of C in julia set
		CJ_complex=0.6; // complex part of C in julia set
		
		iteration=1000; // default number of iterations
		
		
		
		// handling arguments
		
		if(args[0].equals("Mandelbrot")) {  //check the set
			
			// if the set is "Mandelbrot" assign 1 to variable mandelbrot
			mandelbrot=1;
			
			//check the size of the given arguments
			
			if(args.length==5 || args.length==6) {
				
				low_real=Double.parseDouble(args[1]); //lower bound of the real range
				up_real=Double.parseDouble(args[2]); //upper bound of the real range
				
				low_complex=Double.parseDouble(args[3]);//lower bound of the complex range
				up_complex=Double.parseDouble(args[4]);//upper bound of the complex range
				
				//check whether if the number of iterations is given or not
				if(args.length==6) {
					iteration=Integer.parseInt(args[5]); // change the value of number of iterations
				}
				
			}
			
			// check whether the arguments are given correctly
			if( args.length==3 || args.length==4) {
				System.out.println("Invalid");//print an error message
				System.exit(0);//terminate the program
			}
			
			//handling argumnets when length is 2
			
				
			if((args.length==2) && (Integer.parseInt(args[1])<0)) {
				
				System.out.println("Invalid");
				System.exit(0);
				
			}else if((args.length==2) && (Integer.parseInt(args[1])>0)) {
				iteration=Integer.parseInt(args[1]);
			}
			
			
		}else if(args[0].equals("Julia")) {//checking for the Julia set
			
			//assign 1 to julia if the set is "Julia"
			julia=1;
			
			// check the size of the given arguments
			if(args.length==3 || args.length==4) {
				
				CJ_real=Double.parseDouble(args[1]); //get the real part of C
				CJ_complex=Double.parseDouble(args[2]); //get the complex part of the C
				
				//check whether if the number of iterations is given or not
				if(args.length==4) {
					iteration=Integer.parseInt(args[3]);// change the value of number of iterations
				}
				
			}
			
			//handling argumnets when length is 2
			if((args.length==2) && Integer.parseInt(args[1])<0){
				
				System.out.println("Invalid");
				System.exit(0);
				
			}else if((args.length==2) && (Integer.parseInt(args[1])>0)) {
				iteration=Integer.parseInt(args[1]);
			}
			
		}
		
		//variable to store the frame name
		String frameName =null;
		
		// if the given set is "Mandelbrot" set the frame name as "Mandelbrot Set"
		if(mandelbrot==1) {
			frameName="Mandelbrot Set";
		}
		
		// if the given set is "Julia" set the frame name as "Julia Set"
		if(julia==1) {
			frameName="Julia Set";
		}
			
		//check whether the set name is valid or not
		if(julia==0 && mandelbrot==0) {
			//if the given set is not valid
			System.out.println("Give the correct set name");
			System.exit(0);//terminate the program
		}
		
		// create a frame
		JFrame frame = new JFrame(frameName); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		// set the content of the frame as one of this panel
		//frame with 800x800 pixels
		frame.setContentPane(new Fractal(800,800)); 

		//settings for the frame
		frame.pack(); 
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	
	
	}	
	
}
