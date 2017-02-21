package com.andrewpanasyuk.divisionPeriod;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class ColumnDivision {
	private final String VERTICAL_SPLITTER = "|";
	private final String HORIZONTAL_SPLITTER = "-";
	private final String KNOT_SPLITTER = "+";
	private final String EMPTY_POSITION = " ";

	public String getFullDivisionColumn(ArrayList<String> history) {
		String dividend = history.get(0);
		String partOfDividend = history.get(3);
		int shift = Math.max(partOfDividend.length(), history.get(4).length());
		if (dividend.contains("-")) {
			shift++;
		}
		String resultColumn = getHeadColumn(history, shift);
		for (int i = 5; i < history.size() - 1; i += 2) {
			shift++;
			if (Integer.valueOf(history.get(i)) >= Integer.valueOf(history
					.get(1))) {
				resultColumn += StringUtils.leftPad(history.get(i), shift)
						+ "\n";
				resultColumn += StringUtils.leftPad(history.get(i + 1), shift)
						+ "\n";
				resultColumn += StringUtils.leftPad((StringUtils.repeat(
						HORIZONTAL_SPLITTER, history.get(i).length())), shift)
						+ "\n";
			}
		}
		resultColumn += StringUtils.leftPad(history.get(history.size() - 1),
				shift);
		return resultColumn;
	}

	private String getHeadColumn(ArrayList<String> history, int shift) {
		String dividend = history.get(0);
		String divisor = history.get(1);
		String quotient = history.get(2);
		String partOfDividend = history.get(3);
		String firstDeduction = history.get(4);
		int sizePlaceForDividend = Math.max(dividend.length(),
				firstDeduction.length());
		String headScheme = StringUtils.rightPad(dividend, shift) + VERTICAL_SPLITTER + divisor
				+ "\n";
		headScheme += StringUtils.leftPad(firstDeduction, shift)
				+ StringUtils.repeat(EMPTY_POSITION, sizePlaceForDividend
						- shift) + KNOT_SPLITTER
				+ StringUtils.repeat(HORIZONTAL_SPLITTER, quotient.length())
				+ "\n";
		int lengthHorizontalSeparator = Math.max(partOfDividend.length(), firstDeduction.length());
		headScheme += StringUtils.leftPad(
				(StringUtils.repeat(HORIZONTAL_SPLITTER,
						lengthHorizontalSeparator)), shift);
		headScheme += StringUtils.repeat(EMPTY_POSITION, sizePlaceForDividend
				- shift)
				+ VERTICAL_SPLITTER + quotient + "\n";
		return headScheme;
	}
}
