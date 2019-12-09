package Ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;

public class Functions_GUI implements functions {
	private ArrayList<function> function = new ArrayList<function>();	

	@Override
	public int size() {
		return function.size();
	}

	@Override
	public boolean isEmpty() {
		return function.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return function.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return function.iterator();
	}

	@Override
	public Object[] toArray() {
		return function.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return function.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return function.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return function.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return function.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return function.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return function.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return function.retainAll(c);
	}

	@Override
	public void clear() {
		function.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		function f = new ComplexFunction(new Monom(Monom.ZERO));
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				function.add(f.initFromString(line));
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void saveToFile(String file) throws IOException {


	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
