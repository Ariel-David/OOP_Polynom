package Ex1;

import java.util.Iterator;

public class ComplexFunction implements complex_function{
	function left;
	function right;
	Operation op;

	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.op = Operation.None;
	}

	public ComplexFunction(String s, function cf1, function cf2) {
		this.left = cf1;
		this.right = cf2;
		this.op = getOp(s);
	}

	public ComplexFunction(Operation op, function cf1, function cf2) {
		this.left = cf1;
		this.right = cf2;
		this.op = op;
	}

	public ComplexFunction(function cf1) {
		this.left = cf1;
		this.right = null;
		this.op = Operation.None;
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
			ans = left.f(right.f(x));
			break;
		case Max:
			if(left.f(x) > right.f(x)) {
				ans = left.f(x);
			}
			else {
				ans = right.f(x);
			}
			break;
		case Min:
			if(left.f(x) < right.f(x)) {
				ans = left.f(x);
			}
			else {
				ans = right.f(x);
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		}
		return ans;
	}

	@Override
	public function initFromString(String s) {
		if(s.indexOf("(") == -1 && s.indexOf(")") == -1) {
			return new Polynom(s);
		}
		int openParen = s.indexOf("(");
		int indexSep = commaIndex(s,openParen);
		String oper = s.substring(0, openParen);
		function left = initFromString(s.substring(openParen+1,indexSep));
		function right = initFromString(s.substring(indexSep+1,s.length()-1));
		ComplexFunction answer = new ComplexFunction(oper,right,left);
		return answer;
	}

	@Override
	public function copy() {
		if(right != null) {
			return new ComplexFunction(this.op, this.left, this.right);
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
		case "none":
			op = Operation.None;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		}
		return op;
	}

	private int commaIndex(String s, int p) {
		int comma = 0;
		int paren = 1;
		int index = p + 1;
		while(index < s.length() && paren != comma) {
			if(s.charAt(index) == '('){
				paren++;
			}
			if(s.charAt(index) == ',') {
				comma++;
			}
			index++;
		}
		return index-1;
	}

	@Override
	public String toString() {
		String op = getOp().toString();
		String left = this.left.toString();
		if(this.right == null) {
			return ("(" + left + ")");
		}
		else {
			String right = this.right.toString();
			return (op + "(" + left + "," + right + ")");
		}

	}
}

