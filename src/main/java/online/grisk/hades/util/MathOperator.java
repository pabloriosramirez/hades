package online.grisk.hades.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class MathOperator {

	// OPERATOR: '>='
	public static int gratherEqualThan(String argument) {
		Operator gteq = new Operator(">=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] >= values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

	// OPERATOR: '>'
	public static int gratherThan(String argument) {
		Operator gteq = new Operator(">", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] > values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

	// OPERATOR: '<'
	public static int lessThan(String argument) {
		Operator gteq = new Operator("<", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] < values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

	// OPERATOR: '<='
	public static int lessEqualThan(String argument) {
		Operator gteq = new Operator("<=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] <= values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

	// OPERATOR: '=='
	public static int equalThan(String argument) {
		Operator gteq = new Operator("==", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] == values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

	// OPERATOR: '!='
	public static int notEqualThan(String argument) {
		Operator gteq = new Operator("!=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

			@Override
			public double apply(double[] values) {
				if (values[0] != values[1]) {
					return 1d;
				} else {
					return 0d;
				}
			}
		};

		Expression e = new ExpressionBuilder(argument).operator(gteq).build();

		return (int) e.evaluate();
	}

}
