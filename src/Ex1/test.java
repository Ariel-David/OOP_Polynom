package Ex1;

public class test {
	public static void main(String[] args) {
		Polynom p = new Polynom("x^2+1");
		Monom m = new Monom("x");
		ComplexFunction cf = new ComplexFunction("plus",p,m);
		cf.plus(p);
		System.out.println(cf);
		ComplexFunction cf2 = new ComplexFunction();
		cf2.initFromString("plus(plus(1.0x^2 + 1.0,1.0x),1.0x^2 + 1.0)");

	}
}
