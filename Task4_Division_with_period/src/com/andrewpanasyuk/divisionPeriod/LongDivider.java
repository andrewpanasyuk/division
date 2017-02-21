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
		
		for (String partsOfDividend : partsOfDividends) {
            int pieceOfDividend = Integer.valueOf(rest
                    + partsOfDividend);
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
			historyDivision = joinHistories(historyDivision, historyOfRemainderDivision);
		} else {
			historyDivision.add(rest);
		}
		
		
		if (checkCorrectSign(dividend, divisor) < 0) {
			quotient = "-" + quotient;
		}
		historyDivision.add(0, quotient);
		historyDivision.add(0, String.valueOf(divisor));
		historyDivision.add(0, dividendByString);
		return historyDivision;
	}
	
	private ArrayList<String> partitionDividend(int dividend, int divisor) {
		String dividendByString = String.valueOf(Math.abs(dividend));
		String firstPartOfDividend = findPieceOfDividend(Math.abs(dividend),
				Math.abs(divisor));
		int lastIndex = firstPartOfDividend.length();
		ArrayList<String> partsOfDividends = new ArrayList<>();
		char[] dividendChars = dividendByString.toCharArray();
		partsOfDividends.add(firstPartOfDividend);
		for (int i = lastIndex; i < dividendChars.length; i++) {
			String nextPart = Character.toString(dividendChars[i]);
			partsOfDividends.add(nextPart);
		}
		return partsOfDividends;
	}
	
	private ArrayList<String> createHistoryOfRemainderDivision(
			String dividendByString, int divisor) {
		ArrayList<String> listDividends = new ArrayList<>();
		ArrayList<String> historyOfRestDivision = new ArrayList<>();
		String quotient = "";
		listDividends.add(dividendByString);
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
	
			if (listDividends.contains(dividendByString)) {
				int startIndex = listDividends.indexOf(dividendByString);
				quotient = quotient.substring(0, startIndex) + "("
						+ quotient.substring(startIndex) + ")";
				historyOfRestDivision.add(dividendByString);
				historyOfRestDivision.set(0, quotient);
				return historyOfRestDivision;
	
			}
			listDividends.add(dividendByString);
			maxIndexAfterPoint++;
		}
		historyOfRestDivision.add(dividendByString);
		historyOfRestDivision.set(0, quotient);
		return historyOfRestDivision;
	
	}

	private ArrayList<String> joinHistories (ArrayList<String> historyDivision, ArrayList<String> historyOfRemainderDivision){
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

	private int checkCorrectSign(int dividend, int divisor) {
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
