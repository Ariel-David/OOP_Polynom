package Ex1;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonomTest {

	@Test
	public void testMonomDoubleInt() {
		boolean flag = true;
		double a = 0;
		int b = 0;
		int counter = 0;
		while(flag == true && counter < 20) {
			for(int i=0; i<20; i++) { // Ok for
				try {
					Monom m1 = new Monom(a,b);
					assertEquals(b,m1.get_power());
					if(a != m1.get_coefficient()) {
						flag = false;
					}
					else {
						counter++;
					}
					a = Math.floor(Math.random()*1000)/100;
					b = (int)(Math.random()*10);
				}
				catch(Exception e) {
					fail("Should not het exception!!!");
				}
			}
			try {
				Monom m2 = new Monom(-5,-5);
				fail("Should get exception!!!");
			}
			catch(Exception e) {

			}
		}
	}

	@Test
	public void testMonomMonom() {
		Monom m1 = new Monom(5,3);
		Monom m2 = new Monom(5,5);
		Monom m3 = new Monom(m2);
		assertNotEquals(m1,m2);
		assertEquals(m1.get_power(),m1.get_power());
		assertNotEquals(m3.get_power(),m1.get_power());
	}

	@Test
	public void testGet_coefficient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet_power() {
		fail("Not yet implemented");
	}

	@Test
	public void testDerivative() {
		//Test Not Equal
		Monom m1 = new Monom(2,2);
		Monom d1 = m1.derivative();
		System.out.println(d1.toString());
		assertNotEquals("4.0x^23",d1.toString());

		//Test Equal
		Monom m0 = new Monom(2,2);
		Monom d2 = m0.derivative();
		System.out.println(d2.toString());
		assertEquals("4.0x",d2.toString());
	}

	@Test
	public void testF() {
		//Test Equal
		Monom m1 = new Monom(2,2);
		double actual = m1.f(3);
		double expected = 2*Math.pow(3, 2);
		assertTrue(expected == actual);

		//Test Not Equal
		Monom m2= new Monom(2,2);
		double actual2 =m2.f(3);
		double expected2 =3*Math.pow(3, 2); //this change to 3
		assertNotEquals(expected2, actual2);
	}

	@Test
	public void testIsZero() {
		Monom m0 = new Monom(0,0);
		Monom m1 = new Monom();
		assertEquals(m0.toString(), m1.toString());
	}

	@Test
	public void testMonomString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		//Test Equal
		Monom m1= new Monom(2,2);
		Monom m2= new Monom(3,2);
		Monom m3= new Monom(5,2);
		try{
			m1.add(m2);
			assertEquals(m3.toString(), m1.toString());
		}
		catch(Exception e) 
		{
			System.out.println("error input, enter new monoms and try again");
		}

		//should be fail
		Monom m4 = new Monom(-5,3);
		Monom m5 = new Monom(-15,2);
		try {
			m4.add(m5);
			fail("not the same power!");
		}
		catch(Exception e)
		{
			assertTrue(true);
		}

		//Test Not Equal
		Monom m6 = new Monom(2,2);
		Monom m7 = new Monom(3,2);
		Monom m8 = new Monom(4,2);//givven wronn resolte
		try{
			m6.add(m7);
			assertNotEquals(m8.toString(), m6.toString());
		}
		catch(Exception e) 
		{
			System.out.println("error input, enter new monoms and try again");
		}
	}

	@Test
	public void testMultipy() {
		//Test Equal
		//multiplying
		Monom m1 = new Monom(-5,3);
		Monom m2 = new Monom(4,2);
		Monom m3 = new Monom(-20,5);
		m1.multipy(m2);
		assertEquals(""+m3,""+m1);

		//multiplying zero Monom
		m1 = new Monom(0,0);
		m2.multipy(m1);
		assertEquals("0",""+m1);

		//Test Not Equal
		Monom m4 = new Monom(10,2);
		Monom m5 = new Monom(5,2);
		Monom m6 = new Monom(10,4);

		m4.multipy(m5);
		assertNotEquals(m6.toString(), m4.toString());
	}

	@Test
	public void testSubstract() {
		//Test Equal
		Monom m1= new Monom(10,2);
		Monom m2= new Monom(5,2);
		Monom m3= new Monom(5,2);
		try{
			m1.substract(m2);
			assertEquals(m3.toString(), m1.toString());
		}
		catch(Exception e) 
		{
			System.out.println("error input, enter new monoms and try again");
		}
		//Test Not Equal
		Monom m4= new Monom(20,2);
		Monom m5= new Monom(5,2);
		Monom m6= new Monom(5,2);
		try{
			m1.substract(m5);
			assertNotEquals(m6.toString(), m4.toString());
		}
		catch(Exception e) 
		{
			System.out.println("error input, enter new monoms and try again");
		}

	}

	@Test
	public void testEqualsMonom() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitFromString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

}
