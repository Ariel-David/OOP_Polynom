package Ex1Testing;

import java.io.IOException;
import java.util.Iterator;
import org.junit.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;

public class Functions_GUITest {
	private functions data = FunctionsFactory();

	@Test
	public void testInitFromFile() throws IOException {
		String file = "function_file.txt";
		data.saveToFile(file);
		int w = 1200, h = 600, res = 800;
		Range rx = new Range(-9, 11);
		Range ry = new Range(-3, 7);
		Functions_GUI fg = new Functions_GUI();
		fg.initFromFile("function_file.txt");
		fg.drawFunctions(w,h,rx,ry,res);	
	}

	@Test
	public void testSaveToFile() {
		String file = "function_file.txt";
		String file2 = "function_file2.txt";

		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

	}

	@Test
	public void testDrawFunctionsIntIntRangeRangeInt() {
		Functions_GUI fg = new Functions_GUI();
		fg.add( new Polynom("X^3"));
		fg.add( new Polynom("-8x^2+2x+6"));
		fg.add( new Polynom("x^3+2x-1"));
		fg.add( new Polynom("x"));
		fg.add( new Polynom("-3x-4"));
		fg.add( new Polynom("x^2"));
		fg.add( new ComplexFunction(Operation.Times,new Polynom("5X+1"),new Polynom("x")));
		fg.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	@Test
	public void testDrawFunctionsString() throws IOException {
		Functions_GUI fg = new Functions_GUI();
		fg.add( new ComplexFunction(Operation.Divid,new Polynom("3x"),new Polynom("x^2-2")));
		fg.add( new ComplexFunction(Operation.Divid,new Polynom("1"),new Polynom("x^2-2")));
		fg.add( new ComplexFunction(Operation.Plus,new Polynom("3x"),new Polynom("x^2-2")));
		fg.add(new Polynom("5X+1"));
		fg.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}

}
