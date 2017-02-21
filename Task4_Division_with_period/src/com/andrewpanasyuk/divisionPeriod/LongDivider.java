package com.andrewpanasyuk.divisionPeriod;

import java.util.ArrayList;


public class LongDivider {
	private ColumnDivision columnDivision;

	public LongDivider() {
		columnDivision = new ColumnDivision();
	}

	public String showLongDivision(int dividend, int divisor) {
		ArrayList<String> historyDivision = createDivisionHistory(dividend,
				divisor);
		String divisionScheme = columnDivision
				.getFullDivisionColumn(historyDivision);
		return divisionScheme;
	}

	private ArrayList<String> createDivisionHistory(int dividend, int divisor) {
		String rest = "";
		String quotient = "";
		String dividendByString = String.valueOf(dividend);

		ArrayList<String> partsOfDividends = partitionDividend(dividend,
				Math.abs(divisor));

		ArrayList<String> historyDivision = new ArrayList<>();
		for (int i = 0; i < partsOfDividends.size(); i++) {
			int pieceOfDividend = Integer.valueOf(rest
					+ partsOfDividends.get(i));
			int quotientByInt = pieceOfDividend / Math.abs(divisor);
			int deduction = quotientByInt * Math.abs(divisor);
			rest = String.valueOf(pieceOfDividend - deduction);
			quotient += String.valueOf(quotientByInt);
			historyDivision.add(String.valueOf(pieceOfDividend));
			historyDivision.add(String.valueOf(deduction));
		}
		if (!rest.equals("0")) {
			ArrayList<String> historyOfRemainderDivision = createHistoryOfRemainderDivision(
					rest, divisor);
			quotient += "." + historyOfRemainderDivision.get(0);
			historyOfRemainderDivision.remove(0);
			historyDivision = bindHistories(historyDivision, historyOfRemainderDivision);
		} else {
			historyDivision.add(rest);
		}
		if (setCorrectSign(dividend, divisor) < 0) {
			quotient = "-" + quotient;
		}
		historyDivision.add(0, quotient);
		historyDivision.add(0, String.valueOf(divisor));
		historyDivision.add(0, dividendByString);
		return historyDivision;
	}
	
	private ArrayList<String> partitionDividend(int dividend, int divisor) {
		if (divisor > dividend) {

		}
		String dividendByString = String.valueOf(Math.abs(dividend));
		String firstPartOfDividend = findPieceOfDividend(Math.abs(dividend),
				Math.abs(divisor));
		int lastIndex = firstPartOfDividend.length();
		ArrayList<String> partsOfDividents = new ArrayList<>();
		char[] dividendChars = dividendByString.toCharArray();
		partsOfDividents.add(firstPartOfDividend);
		for (int i = lastIndex; i < dividendChars.length; i++) {
			String nextPart = Character.toString(dividendChars[i]);
			partsOfDividents.add(nextPart);
		}
		return partsOfDividents;
	}
	
	private ArrayList<String> createHistoryOfRemainderDivision(
			String dividendByString, int divisor) {
		ArrayList<String> arrayDividends = new ArrayList<>();
		ArrayList<String> historyOfRestDivision = new ArrayList<>();
		String quotient = "";
		arrayDividends.add(dividendByString);
		historyOfRestDivision.add(dividendByString + "0");
		int maxIndexAfterPoint = 0;
		while (!dividendByString.equals("0") || maxIndexAfterPoint >= 10) {
			int dividendByInt = Integer.valueOf(dividendByString + "0");
			int quotientByInt = dividendByInt / Math.abs(divisor);
			int deduction = quotientByInt * Math.abs(divisor);
			dividendByString = String.valueOf(dividendByInt - deduction);
			quotient += String.valueOf(quotientByInt);
			historyOfRestDivision.add(String.valueOf(dividendByInt));
			historyOfRestDivision.add(String.valueOf(deduction));
	
			if (arrayDividends.contains(dividendByString)) {
				int startIndex = arrayDividends.indexOf(dividendByString);
				quotient = quotient.substring(0, startIndex) + "("
						+ quotient.substring(startIndex) + ")";
				historyOfRestDivision.add(dividendByString);
				historyOfRestDivision.set(0, quotient);
				return historyOfRestDivision;
	
			}
			arrayDividends.add(dividendByString);
			maxIndexAfterPoint++;
		}
		historyOfRestDivision.add(dividendByString);
		historyOfRestDivision.set(0, quotient);
		return historyOfRestDivision;
	
	}

	private ArrayList<String> bindHistories (ArrayList<String> historyDivision, ArrayList<String> historyOfRemainderDivision){
		if (historyDivision.get(1).equals("0")) {
			historyDivision.remove(1);
			historyOfRemainderDivision.remove(0);
			historyDivision.addAll(1, historyOfRemainderDivision);
		} else {
			historyDivision.addAll(historyOfRemainderDivision);
		}
	return historyDivision;
	}

	private String findPieceOfDividend(int dividend, int divisor) {
		String pieceOfDividend = "";
		for (Character dividendChars : String.valueOf(dividend).toCharArray()) {
			pieceOfDividend += dividendChars.toString();
			if (Integer.valueOf(pieceOfDividend) >= divisor) {
				return pieceOfDividend;
			}
		}
		return pieceOfDividend;
	}

	private int setCorrectSign(int dividend, int divisor) {
		int factor = 1;
		if (dividend < 0) {
			factor *= -1;
		}
		if (divisor < 0) {
			factor *= -1;
		}
		return factor;
	}

}
