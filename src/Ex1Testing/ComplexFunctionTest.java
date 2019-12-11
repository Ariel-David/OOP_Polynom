package Ex1Testing;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;

public class ComplexFunctionTest {
	ComplexFunction cf1;
	ComplexFunction cf2;
	ComplexFunction cf3;	
	Polynom p1;
	Polynom p2;
	

	@Test
	public void testF() {
		cf1 = new ComplexFunction("div",new Polynom("x^2+5.2x+6.5"),new Polynom("4x"));
		double x,check;
		x = 3;
		check = (Math.pow(x,2)+(5.2)*x+6.5)/(4*x);
		assertEquals(check, cf1.f(x));
		x = 5.2;
		check = (Math.pow(x,2)+(5.2)*x+6.5)/(4*x);
		assertEquals(check, cf1.f(x));
	}

	@Test
	public void testInitFromString() {
		String s = "plus(x^2+5.2x+6.5,x-1)";
		p1 = new Polynom("x^2+5.2x+6.5");
		p2 = new Polynom("x-1");
		cf1 = new ComplexFunction(Operation.Plus,p1,p2);
		cf2 = (ComplexFunction) cf1.initFromString(s);
		assertEquals(cf1.toString(), cf2.toString());
	}

	@Test
	public void testCopy() {
		p1 = new Polynom("x^2+5.2x+6.5");
		p2 = new Polynom("x-1");
		cf1 = new ComplexFunction(Operation.Comp,p1,p2);
		cf2 = new ComplexFunction(cf1);
		assertEquals(cf1,cf2);
	}

	@Test
	public void testPlus() {
		p1 = new Polynom("x^3+x^2-1");
		p2 = new Polynom("2x");
		cf1 = new ComplexFunction(Operation.Plus,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.plus(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testMul() {
		p1 = new Polynom("x^3+x^2-1");
		p2 = new Polynom("2x");
		cf1 = new ComplexFunction(Operation.Times,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.mul(p2);
		assertEquals(cf2.f(2), cf1.f(2));
		
		//mul by zero
		p1 = new Polynom();
		p2 = new Polynom("2x");
		cf1 = new ComplexFunction(Operation.Times,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.mul(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testDiv() {
		p1 = new Polynom("x^3+x^2-1");
		p2 = new Polynom("2x");
		cf1 = new ComplexFunction(Operation.Divid,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.div(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testMax() {
		p1 = new Polynom("x^3+x^2-1");
		p2 = new Polynom("2x");
		cf1 = new ComplexFunction(Operation.Max,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.max(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testMin() {
		p1 = new Polynom("x^3");
		p2 = new Polynom("-2x");
		cf1 = new ComplexFunction(Operation.Min,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.min(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testComp() {
		p1 = new Polynom("x^3-7x");
		p2 = new Polynom("4x^3");
		cf1 = new ComplexFunction(Operation.Comp,p1,p2);
		cf2 = new ComplexFunction(p1);
		cf2.comp(p2);
		assertEquals(cf2.f(2), cf1.f(2));
	}

	@Test
	public void testEqualsObject() {
		p1 = new Polynom("x^2");
		p2 = new Polynom("x");
		cf1 = new ComplexFunction(p1);
		cf2 = new ComplexFunction("mul",p2,p2);
		cf3 = new ComplexFunction(Operation.Divid,p1,p2);
		assertTrue(cf1.equals(cf2));
		assertFalse(cf3.equals(p2));
	}
	
	@Test
	public void testright() {
		p1 = new Polynom("3x^3-6x");
		ComplexFunction cf1 = new ComplexFunction("min",new Polynom("x^2"),p1);
		assertEquals(cf1.right(), p1);
		
	}
	
	@Test
	public void testleft() {
		p1 = new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("min",new Polynom("x^2"),p1);
		assertEquals(cf1.left(), p1);
	}
	
	@Test
	public void testgetOp() {
		Operation op = Operation.Min;
		ComplexFunction cf1 = new ComplexFunction("min",new Polynom("x^2"),p1);
		assertEquals(cf1.getOp(), op);
	}
	
	@Test
	public void testToString() {
		String s = "Times(1.0x,1.0x)";
		p2 = new Polynom("x");
		cf2 = new ComplexFunction("mul",p2,p2);
		assertEquals(s, cf2.toString());
	}

}
