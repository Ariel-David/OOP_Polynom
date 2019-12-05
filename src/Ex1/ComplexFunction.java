package Ex1;

import java.util.Iterator;

public class ComplexFunction implements complex_function{
	function left;
	function right;
	Operation op;

	public ComplexFunction(String s, function cf1, function cf2) {
		left = cf1;
		right = cf2;
		op = getOp(s);
	}

	public ComplexFunction(function cf1) {
		left = cf1;
		right = null;
		op = null;
	}

	@Override
	public double f(double x) {
		double ans = 0;
		switch (this.op) {
		case Plus:
			ans =  left.f(x) + right.f(x);
			break;
		case Divid:
			ans = left.f(x) / right.f(x);
			break;
		case Times:
			ans = left.f(x) * right.f(x);
			break;
		case Comp:
			ans = 0;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		}

		return ans;

	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		this.left= this.copy();
		this.right = f1;
		this.op= Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		return op;
	}

	private Operation getOp(String s) {
		Operation op;
		switch (s) {
		case "plus":
			op = Operation.Plus;
			break;
		case "mul":
			op = Operation.Times;
			break;
		case "div":
			op = Operation.Divid;
			break;
		case "max":
			op = Operation.Max;
			break;
		case "min":
			op = Operation.Min;
			break;
		case "comp":
			op = Operation.Comp;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		}
		return op;
	}

}
