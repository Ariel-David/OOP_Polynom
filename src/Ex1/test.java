package Ex1;

public class test {
	public static void main(String[] args) {
		Polynom p = new Polynom("x^2+1");
		Monom m = new Monom("x");
		Operation op = Operation.Plus;
		ComplexFunction cf2 = new ComplexFunction(p);
	    cf2.min(m);
	    System.out.println(cf2.f(2));
	}
}
