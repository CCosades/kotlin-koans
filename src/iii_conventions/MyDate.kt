package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return (year - other.year) * 367 + (month - other.month) * 32 + (dayOfMonth - other.dayOfMonth)
    }

}

operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var current = start
        override fun hasNext() = (current <= endInclusive)
        override fun next() : MyDate {
            val next = current
            current = current.nextDay()
            return next
        }
    }
}
