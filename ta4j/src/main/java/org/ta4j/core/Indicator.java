/*
  The MIT License (MIT)

  Copyright (c) 2014-2017 Marc de Verdelhan, Ta4j Organization & respective authors (see AUTHORS)

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal in
  the Software without restriction, including without limitation the rights to
  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core;

import org.ta4j.core.num.Num;

import java.io.Serializable;

/**
 * Indicator over a {@link TimeSeries time series}.
 * <p/p>
 * For each index of the time series, returns a value of type <b>T</b>.
 *
 * @param <T> the type of returned value (Double, Boolean, etc.)
 */
public interface Indicator<T> extends Serializable {

    /**
     * @param index the bar index
     * @return the value of the indicator
     */
    T getValue(int index);

	/**
	 * @return the related time series
	 */
	TimeSeries getTimeSeries();

	/**
	 * @return  the {@link Num Num extending class} for the given {@link Number}
	 */
	Num numOf(Number number);

	 /**
	 * Returns all values from an {@link Indicator} as a Array of Doubles. The
	 * returned doubles could have a minor loss of precise, if {@link Indicator}
	 * was based on {@link Num Num}.
	 *
	 * @param ref the indicator
	 * @param index the index
	 * @param barCount the barCount
	 * @return array of double within the barCount
	 */
	static Double[] toDouble(Indicator<Num> ref, int index, int barCount) {

		Double[] all = new Double[barCount];

		for (int i = Math.max(0, index - barCount + 1); i <= index; i++) {
			Num number = ref.getValue(index);
			all[i] = number.doubleValue();
		}

		return all;
	}

}
