fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    val date_range = first.compareTo(date).rangeTo(last.compareTo(date))
    val res: Boolean = if (0 in date_range) true else false
    return res
}