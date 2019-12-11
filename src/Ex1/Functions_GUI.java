package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;


public class Functions_GUI implements functions {
	private ArrayList<function> functionArray = new ArrayList<function>();	

	@Override
	public int size() {
		return functionArray.size();
	}

	@Override
	public boolean isEmpty() {
		return functionArray.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return functionArray.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return functionArray.iterator();
	}

	@Override
	public Object[] toArray() {
		return functionArray.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return functionArray.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return functionArray.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return functionArray.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return functionArray.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return functionArray.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return functionArray.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return functionArray.retainAll(c);
	}

	@Override
	public void clear() {
		functionArray.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		function f = new ComplexFunction(new Monom(Monom.ZERO));
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				functionArray.add(f.initFromString(line));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(file));
			StringBuilder sb = new StringBuilder();

			Iterator<function> it = functionArray.iterator();
			while(it.hasNext()) {

				sb.append(it.next().toString()+"\n");
			}
			pw.write(sb.toString());
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		Color[] colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		double step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;

		////////vertical lines
		StdDraw.setPenColor(Color.BLACK);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
			StdDraw.text(i+0.1,-0.7,i+"");
		}

		////////horizontal  lines
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.text(-0.5,i,i+"");
			StdDraw.text(-0.5,i,i+"");

		}

		////////x axis		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(),0, rx.get_max(), 0);

		//////// y axis	
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for(int i = 0; i < functionArray.size(); i++) {
			StdDraw.setPenColor(colors[i%colors.length]);
			for(double j = rx.get_min(); j<= rx.get_max(); j= j+step) {
				StdDraw.setPenRadius(0.005);
				StdDraw.line(j, functionArray.get(i).f(j), j+step, functionArray.get(i).f(j+step));
			}
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		try {
			FileReader read = new FileReader(json_file);
			Params parametrim = gson.fromJson(read, Params.class);
			Range rx = new Range(parametrim.Range_X[0],parametrim.Range_X[1]);
			Range ry = new Range(parametrim.Range_Y[0],parametrim.Range_Y[1]);
			drawFunctions(parametrim.Width,parametrim.Height,rx,ry,parametrim.Resolution);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
