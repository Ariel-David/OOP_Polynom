package Ex1Testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.Monom;
import Ex1.Polynom;

public class PolynomTest {
	String Ok[];
	String NotOk[];
	Polynom p1, p2, p3;
	
	@BeforeEach
	void setUp() throws Exception {
		Ok = new String [] {"x^2", "3x^2+5-3x","25*x^7-3*x^2+3","x","-5","5x-x^3-5-x"};
		NotOk = new String[] {"+x","5a, xx, x^-2", "8**2, 9x--6x^2", "6x^"};
	}
	
	@Test
	public void testPolynom() {
		p1 = new Polynom();
		assertTrue(p1.isZero());
		assertEquals("0", p1.toString());
	}

	@Test
	public void testPolynomString() {
		//Test equal
		p1 = new Polynom("2x^2+3x^4+3x+2");
		String str ="3.0x^4 + 2.0x^2 + 3.0x + 2.0";
		assertEquals(str.toString(),p1.toString());

		//Test not equal
		p2 = new Polynom("2x^10+3x^4+3x+2");
		String str2 ="3.0x^4 + 2.0x^2 + 3.0x +2.0";
		assertNotEquals(str2.toString(),p2.toString());
	}

	@Test
	public void testF() {
		p1 = new Polynom("x^2+5-x^3");
		double x,check;
		x = 3;
		check = Math.pow(x, 2)+5-Math.pow(x, 3);
		assertEquals(check, p1.f(x));
		x = 5.2;
		check = Math.pow(x, 2)+5-Math.pow(x, 3);
		assertEquals(check, p1.f(x));
	}

	@Test
	public void testAddPolynom_able() {
		//Test equal
		p1 = new Polynom("2x^2+3x^4+3x+2");
		p2 = new Polynom("4x^7+23x^4+9x+2");
		p1.add(p2);
		assertEquals(p1.toString(),"4.0x^7 + 26.0x^4 + 2.0x^2 + 12.0x + 4.0");

		//Test Not equal
		Polynom p3 = new Polynom("2x^2+3x^4+3x+2+2");
		Polynom p4 = new Polynom("4x^7+23x^4+9x+2");
		p3.add(p4);
		assertNotEquals(p1.toString(),"4.0x^7 +26.0x^4 +2.0x^2 + 12.0x + 4.0");

	}

	@Test
	public void testAddMonom() {
		p1 = new Polynom("x^2-4");
		p1.add(new Monom(5,2));
		assertEquals("6.0x^2 -4.0",p1.toString());
		p1.add(new Monom(7.3,4));
		assertEquals("7.3x^4 + 6.0x^2 -4.0",p1.toString());
	}

	@Test
	public void testSubstract() {
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom("-5x^3-15+7x^2");
		Polynom p3	= new Polynom("-5x^3-15+7x^2");
		p2.substract(p3);
		assertEquals(""+p1,""+p2);
		assertEquals("0",""+p2);

		//Subtraction of empty polynom - checks if it's the same polynom
		p1 = new Polynom("-5x^3-15+7x^2");
		p2 = new Polynom("-5x^3-15+7x^2");
		p3	= new Polynom();
		p2.substract(p3);
		assertEquals(""+p1,""+p2);

		//sub polynom
		p1 = new Polynom("5x^3-4x^2-3x+20");
		p2 = new Polynom("3x^2+5-3x");
		p3	= new Polynom("5x^3+15-7x^2");
		p1.substract(p2);
		assertEquals(""+p1,""+p3);
	}

	@Test
	public void testMultiplyPolynom_able() {
		p1 = new Polynom();
		p2 = new Polynom("-5x^3-15+7x^2");
		p3	= new Polynom();
		p2.multiply(p3);
		assertEquals(""+p1,""+p2);

		//multiplying zero's
		p2.multiply(p3);
		assertEquals(""+p1,""+p2);

		//multiplying 2 polynoms
		p1 = new Polynom("-30x^5+18x^4-25x^2+15x");
		p2 = new Polynom("-5x^2+3x");
		p3	= new Polynom("6x^3+5");
		p2.multiply(p3);
		assertEquals(""+p1,""+p2);
	}

	@Test
	public void testIsZero() {
		Polynom p = new Polynom();
		Polynom p0 = new Polynom("3x^2+5x");
		Polynom p55 = new Polynom("3x^2+5x");
		p55.substract(p0);
		assertEquals("0", p55.toString());
		assertEquals("0", p.toString());
		 
	}

	@Test
	public void testRoot() {
		p1 = new Polynom("-5x^2+25x+5");
		try 
		{
			p1.root(6, 8, 0.001);
			fail("same side of X");
		}
		catch(Exception e)
		{
			assertTrue(true);
		}
		double x = p1.root(4, 8, 0.001);
		assertEquals(5, Math.round(x));
	}

	@Test
	public void testDerivative() {
		p1 = new Polynom("-15x^2+14x");
		p2 = new Polynom("-5x^3+7x^2-15");
		p2 = (Polynom) p2.derivative();
		assertEquals(""+p1,""+p2);

		//derivative of an empty polynom
		p1 = new Polynom();
		p2 = new Polynom();
		p2.derivative();
		assertEquals(""+p1,""+p1);

		p1 = new Polynom();
		p2 = new Polynom("5+25+35+14");
		assertEquals(""+p1,""+p2.derivative());
	}

	@Test
	public void testArea() {
		//Test equal
		p1 = new Polynom("-x^2+4") ;
		p1.area(-4, 0, 0.001);
		assertEquals("-1.0x^2 + 4.0", p1.toString());
		
		//Test Not equal
		p2 = new Polynom("-x^2+4+2") ;
		p2.area(-4, 0, 0.001);
		assertNotEquals("-1.0x^2 + 4.0", p2.toString());
	}

	@Test
	public void testIteretor() {
		p1 = new Polynom("3x^2");
		Iterator<Monom> it = p1.iteretor();
		Monom p = it.next();
		assertEquals(3,p.get_coefficient());
		assertEquals(2,p.get_power());
	}

	@Test
	public void testMultiplyMonom() {
		//Test equal
		p1 = new Polynom("4x^6+2.5x^2-7");
		Monom m = new Monom(3,6);
		p1.multiply(m);
		assertEquals("12.0x^12 + 7.5x^8 -21.0x^6",p1.toString());
		
		//Test zero
		p2 = new Polynom("4x^6+2.5x^2-7");
		Monom m1 = new Monom();
		p1.multiply(m1);
		assertEquals("0",p1.toString());
	}

	@Test
	public void testToString() {
		for(int i=0; i<Ok.length; i++) {
			try {
				p1 = new Polynom(Ok[i]);
				p2  = new Polynom(""+p1);
				assertEquals(""+p1, ""+p2);
			}
			catch(Exception e) {
				fail("Should Not get exception!!!");
			}
		}
	}

	@Test
	public void testInitFromString() {
		String s = "2.0x^4 + 6.2x -8.0";
		p1 = new Polynom("2x^4+6.2-8");
		p2 = new Polynom();
		p3 = (Polynom) p2.initFromString(s);
		assertEquals(p3.toString(),s);
	}

	@Test
	public void testPolynomPolynom() {
		p1 = new Polynom("x+4");
		p2 = new Polynom(p1);
		assertEquals(p1.toString(), p2.toString());
	}

	@Test
	public void testCopy() {
		p1 = new Polynom("x+4");
		p2 = (Polynom) p1.copy();
		assertEquals(p1.toString(), p2.toString());
	}

}
