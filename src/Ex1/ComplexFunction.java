package Ex1;

import java.util.Iterator;

public class ComplexFunction implements complex_function{
	function left;
	function right;
	Operation op;

	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.op = null;
	}
	public ComplexFunction(String s, function cf1, function cf2) {
		this.left = cf1;
		this.right = cf2;
		this.op = getOp(s);
	}

	public ComplexFunction(function cf1) {
		this.left = cf1;
		this.right = null;
		this.op = null;
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
		boolean flag = true;
		int count = 0;
		while(flag) {
			if(s.contains("(")) {
				count++;
			}
			if(s.contains(")")){
				count--;
			}
			else {
				flag = false;
				String [] Left = s.split("[,]");
				String right = s.substring(',',s.length());
			}
			int left = s.indexOf('(')+1;
			int right = s.lastIndexOf(')');
			s = s.substring(left, right);
			
		}
		return initFromString(s);
	}

	@Override
	public function copy() {
		if(right != null) {
			return new ComplexFunction(this.getOpToString(op), this.left, this.right);
		}
		else {
			return new ComplexFunction(this.left);
		}
	}

	@Override
	public void plus(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op= Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Times;
	}

	@Override
	public void div(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Divid;
	}

	@Override
	public void max(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Max;
	}

	@Override
	public void min(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Min;
	}

	@Override
	public void comp(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Comp;
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
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

	private String getOpToString(Operation op) {
		String s = new String();
		switch (op) {
		case Plus:
			s = ""+"plus";
			break;
		case Times:
			s = ""+"mul";
			break;
		case Divid:
			s = ""+"div";
			break;
		case Max:
			s = ""+"max";
			break;
		case Min:
			s = ""+"min";
			break;
		case Comp:
			s = ""+"comp";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		}
		return s;		
	}
	@Override
	public String toString() {
		String op = this.getOpToString(this.getOp());
		String left = this.left.toString();
		String right = this.right.toString();
		return ("" + op + "(" + left + "," + right + ")");
	}
}

