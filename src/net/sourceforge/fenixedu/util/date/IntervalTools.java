/**
 * 
 */
package net.sourceforge.fenixedu.util.date;

import java.util.Comparator;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.YearMonthDay;

/**
 * Utility class with methods for manipulation of time Intervals.
 * 
 * In every method, when a null value is provided, the corresponding time value
 * is set to Long.MIN or Long.MAX respectively.
 * 
 * The Interval's generated by the class, when used with arguments that only
 * specify Dates, assume the Interval starts at 00:00 on the starting day, and
 * end at 00:00 of the ending day.
 * 
 * @author Joao Carvalho (joao.pedro.carvalho@ist.utl.pt)
 * 
 */
@SuppressWarnings("deprecation")
public class IntervalTools {

    public static Interval getInterval(DateTime startDate, DateTime endDate) {
	long start = startDate == null ? Long.MIN_VALUE : startDate.getMillis();
	long end = endDate == null ? Long.MAX_VALUE : endDate.getMillis();

	return new Interval(start, end);
    }

    public static Interval getInterval(LocalDate startDate, LocalDate endDate) {
	long start = startDate == null ? Long.MIN_VALUE : startDate.toDateMidnight().getMillis();
	long end = endDate == null ? Long.MAX_VALUE : endDate.toDateMidnight().toDateTime().withTime(23, 59, 59, 999).getMillis();

	return new Interval(start, end);
    }

    @Deprecated
    public static Interval getInterval(YearMonthDay startDate, YearMonthDay endDate) {
	long start = startDate == null ? Long.MIN_VALUE : startDate.toDateMidnight().getMillis();
	long end = endDate == null ? Long.MAX_VALUE : endDate.toDateMidnight().toDateTime().withTime(23, 59, 59, 999).getMillis();

	return new Interval(start, end);
    }

    public static Interval getInterval(Date startDate, Date endDate) {
	long start = startDate == null ? Long.MIN_VALUE : startDate.getTime();
	long end = endDate == null ? Long.MAX_VALUE : endDate.getTime();

	return new Interval(start, end);
    }

    @Deprecated
    public static YearMonthDay getStartYMD(Interval interval) {
	long startTime = interval.getStartMillis();
	return startTime == Long.MIN_VALUE ? null : new YearMonthDay(startTime);
    }

    @Deprecated
    public static YearMonthDay getEndYMD(Interval interval) {
	long endTime = interval.getEndMillis();
	return endTime == Long.MAX_VALUE ? null : new YearMonthDay(endTime);
    }

    public static LocalDate getStartLocalDate(Interval interval) {
	long startTime = interval.getStartMillis();
	return startTime == Long.MIN_VALUE ? null : new LocalDate(startTime);
    }

    public static LocalDate getEndLocalDate(Interval interval) {
	long endTime = interval.getEndMillis();
	return endTime == Long.MAX_VALUE ? null : new LocalDate(endTime);
    }

    @Deprecated
    public static Interval intervalWithStart(Interval originalInterval, YearMonthDay day) {
	long millis = day.toDateMidnight().getMillis();
	return new Interval(millis, originalInterval.getEndMillis());
    }

    @Deprecated
    public static Interval intervalWithEnd(Interval originalInterval, YearMonthDay day) {
	long millis = day.toDateMidnight().toDateTime().withTime(23, 59, 59, 999).getMillis();
	return new Interval(originalInterval.getStartMillis(), millis);
    }

    public static Interval intervalWithStart(Interval originalInterval, LocalDate day) {
	long millis = day.toDateMidnight().getMillis();
	return new Interval(millis, originalInterval.getEndMillis());
    }

    public static Interval intervalWithEnd(Interval originalInterval, LocalDate day) {
	long millis = day.toDateMidnight().toDateTime().withTime(23, 59, 59, 999).getMillis();
	return new Interval(originalInterval.getStartMillis(), millis);
    }

    public static Interval intervalWithStart(Interval interval, Date date) {
	long millis = date == null ? Long.MIN_VALUE : date.getTime();
	return new Interval(millis, interval.getEndMillis());
    }

    public static Interval intervalWithEnd(Interval interval, Date date) {
	long millis = date == null ? Long.MAX_VALUE : date.getTime();
	return new Interval(interval.getStartMillis(), millis);
    }

    public static Comparator<Interval> COMPARATOR_BY_START_DATE = new Comparator<Interval>() {

	@Override
	public int compare(Interval i0, Interval i1) {
	    return i0.getStart().compareTo(i1.getStart());
	}

    };

}
