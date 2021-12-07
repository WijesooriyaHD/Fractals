

		/* ....................E/17/407......................*/
		/*....... Wijesooriya H.D. ................... */

import java.lang.Math; //import inbuild math class

// class to do the arithmetic operations between complex numbers
public class ComplexNumber {
	
	// variable declarations 
	private double real;  // real part of the complex number
	private double complex; // imaginary part of the complex number
	

	public ComplexNumber() {  // constructor
	
		// generate 0+0i
		real = 0.0;
		complex = 0.0;
	}
	
	
	// constructor overloading
	public ComplexNumber(double real, double complex) {
	
		this.real = real;  // set the real part
		this.complex = complex; // set the imaginary part
	}
	
	

	// method to square a complex number
	public ComplexNumber square() {
	
		double real_part = this.real*this.real - this.complex*this.complex;// get the real part
		double complex_part = 2*this.real*this.complex;//get the imaginary part
		return new ComplexNumber(real_part,complex_part); // return the new complex number
	}
	
	
	
	// method to do the addition
	public void add(ComplexNumber complex_number) {
	
		this.real = this.real + complex_number.real;// generate the real part
		this.complex = this.complex + complex_number.complex;//generate the imaginary part
	}
	
	
	// method to get the magnitude of a complex number
	public double modulus() {
	
		return Math.sqrt(Math.pow(this.real,2) + Math.pow(this.complex,2));
	}
	
	
}


	


