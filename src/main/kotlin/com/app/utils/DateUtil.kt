package com.app.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{

        val FORMAT_YEAR = "yyyy"
        val FORMAT_MONTH = "MM"
        val FORMAT_DAY = "dd"
        val FORMAT_HOUR = "HH"
        val FORMAT_MINUTE = "mm"
        val FORMAT_SECOND = "ss"
        val FORMAT_FULL_DEFAULT = "yyyyMMddHHmmss"
        val FORMAT_FULL_1 = "yyyy-MM-dd HH:mm:ss"
        val FORMAT_FULL_2 = "yyyy년 MM월 dd일 HH시 mm분 ss초"
        val FORMAT_DATE_DEFAULT = "yyyyMMdd"
        val FORMAT_DATE_1 = "yyyy-MM-dd"
        val FORMAT_DATE_2 = "yyyy년 MM월 dd일"
        val YEAR = Calendar.YEAR
        val MONTH = Calendar.MONTH
        val DAY = Calendar.DAY_OF_MONTH
        val HOUR = Calendar.HOUR_OF_DAY
        val MINUTE = Calendar.MINUTE
        val SECOND = Calendar.SECOND

        val date: Date
            /**
             * 현재 시간에 해당하는 Date 반환
             * @return    Date
             */
            get() = Date()

        /**
         * 입력된 날짜 문자열을 기준으로 Date 반환
         * 입력되는 날짜값 문자열 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * @param date    :    날짜값 문자열
         * @return    Date
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getDate(date: String): Date {
            return getDate(date, getDateFormat(date))
        }

        /**
         * 입력된 날짜 문자열을 기준으로 Date 반환
         * @param date        :    날짜값 문자열
         * @param format    :    입력된 날짜값에 해당하는 날짜포멧
         * @return    Date
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getDate(date: String?, format: String?): Date {
            return SimpleDateFormat(format).parse(date)
        }

        val dateFullString: String
            /**
             * 현재 시간을 문자열 14자리로 반환한다. => yyyyMMddHHmmss
             * @return    String
             */
            get() = getDateString(Date())

        val dateString: String
            /**
             * 현재 시간을 문자열 8자리로 반환한다. => yyyyMMdd
             * @return    String
             */
            get() = dateFullString.substring(0, 8)

        /**
         * 입력된 Date 객체에 해당하는 시간을 문자열 14자리로 반환한다. => yyyyMMddHHmmss
         * @param date    :    Date 객체
         * @return    String
         */
        fun getDateString(date: Date?): String {
            return getDateString(date, FORMAT_FULL_DEFAULT)
        }

        /**
         * 입력된 Calendar 객체에 해당하는 시간을 문자열 14자리로 반환한다. => yyyyMMddHHmmss
         * @param calendar    :    Calendar 객체
         * @return    String
         */
        fun getDateString(calendar: Calendar): String {
            return getDateString(calendar, FORMAT_FULL_DEFAULT)
        }

        /**
         * 현재 시간을 입력된 날짜 포멧에 맞춰 문자열로 반환한다.
         * @param format    :    날짜 포멧
         * @return
         */
        fun getDateString(format: String?): String {
            return getDateString(Date(), format)
        }

        /**
         * 입력된 Calendar 객체에 해당하는 시간을 입력된 날짜 포멧에 맞춰 문자열로 반환한다.
         * @param calendar    :    Calendar 객체
         * @param format    :    날짜 포멧
         * @return    String
         */
        fun getDateString(calendar: Calendar, format: String?): String {
            return getDateString(calendar.time, format)
        }

        /**
         * 입력된 Date 객체에 해당하는 시간을 입력된 날짜 포멧에 맞춰 문자열로 반환한다.
         * @param date        :    Date 객체
         * @param format    :    날짜 포멧
         * @return    String
         */
        fun getDateString(date: Date?, format: String?): String {
            return SimpleDateFormat(format).format(date)
        }

        /**
         * 입력된 날짜 문자열을 기준으로 Calendar 객체를 반환한다.
         * @param date    :    날짜 문자열
         * @return    Calendar
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getCalendar(date: String): Calendar {
            return getCalendar(date, getDateFormat(date))
        }

        /**
         * 입력된 날짜 문자열을 기준으로 Calendar 객체를 반환한다.
         * @param date        :    날짜 문자열
         * @param format    :    입력된 날짜의 날짜 포멧
         * @return    Calendar
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getCalendar(date: String?, format: String?): Calendar {
            return getCalendar(getDate(date, format))
        }

        /**
         * 입력된 Date 객체를 기준으로 Calendar 객체를 반환한다.
         * @param date    :    Date 객체
         * @return    Calendar
         */
        fun getCalendar(date: Date?): Calendar {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar
        }

        /**
         * 현재 날짜에서 가감한 날짜를 가져온다(yyyyMMdd 형식)
         * 반환 날짜 포멧 : yyyyMMdd
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    String
         */
        fun getAddDayString(value: Int): String {
            return getAddDayFullString(value).substring(0, 8)
        }

        /**
         * 입력된 날짜에서 가감한 날짜를 가져온다
         * 입력되는 날짜값 문자열 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * 반환되는 날짜 포멧은 입력된 날짜 포멧에 따른다.
         * @param date    :    가감할 기준 날짜값 문자열(최소 일 자리까지는 있어야함 예:yyyyMMdd, yyyy-MM-dd HH, yyyyMMddHHmm, yyyy년 MM월 dd일 HH시 ss초)
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    String
         */
        @Throws(ParseException::class)
        fun getAddDayString(date: String, value: Int): String {
            return getAddDayFullString(date, value)
        }

        /**
         * 입력된 Date객체에서 가감한 날짜를 가져온다.
         * 반환 날짜 포멧 : yyyyMMdd
         * @param date    :    Date 객체
         * @param value    :    가감할 일 수
         * @return
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDayString(date: Date?, value: Int): String {
            return getAddDayFullString(date, value).substring(0, 8)
        }

        /**
         * 입력된 Calendar 객체에서 가감한 날짜를 가져온다.
         * 반환 날짜 포멧 : yyyyMMdd
         * @param calendar    :    Calendar 객체
         * @param value        :    가감할 일 수
         * @return
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDayString(calendar: Calendar, value: Int): String {
            return getAddDayFullString(calendar, value).substring(0, 8)
        }

        /**
         * 현재 날짜에서 가감한 날짜를 가져온다
         * 반환 날짜 포멧 : yyyyMMddHHmmss
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    String
         */
        fun getAddDayFullString(value: Int): String {
            return getAddDateString(date, DAY, value)
        }

        /**
         * 입력된 날짜에서 가감한 날짜를 가져온다
         * 입력되는 날짜값 문자열 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * 반환되는 날짜 포멧은 입력된 날짜 포멧에 따른다.
         * @param date    :    가감할 기준 날짜값 문자열(최소 일 자리까지는 있어야함 예:yyyyMMdd, yyyy-MM-dd HH, yyyyMMddHHmm, yyyy년 MM월 dd일 HH시 ss초)
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    String
         */
        @Throws(ParseException::class)
        fun getAddDayFullString(date: String, value: Int): String {
            return getAddDateString(date, DAY, value)
        }

        /**
         * 입력된 Date 객체에서 가감한 날짜를 가져온다
         * 반환 날짜 포멧 : yyyyMMddHHmmss
         * @param date    :    Date 객체
         * @param value    :    가감할 일 수
         * @return    String
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDayFullString(date: Date?, value: Int): String {
            return getAddDateString(date, DAY, value)
        }

        /**
         * 입력된 Calendar 객체에서 가감한 날짜를 가져온다
         * 반환 날짜 포멧 : yyyyMMddHHmmss
         * @param calendar    :    Calendar 객체
         * @param value        :    가감할 일 수
         * @return    String
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDayFullString(calendar: Calendar, value: Int): String {
            return getAddDateString(calendar, DAY, value)
        }

        /**
         * 현재 날짜에서 가감한 Date 를 가져온다
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    Date
         */
        fun getAddDayDate(value: Int): Date {
            return getAddDate(date, DAY, value)
        }

        /**
         * 현재 날짜에서 가감한 Calendar 를 가져온다
         * @param value    :    현재 날짜에서 가감할 일 수
         * @return    Calendar
         */
        fun getAddDayCalendar(value: Int): Calendar {
            return getAddCalendar(date, DAY, value)
        }

        /**
         * 입력된 날짜값을 기준으로 해당 타입의 값을 가감하여 반환한다. 반환된 날짜 패턴은 입력된 패턴과 동일하게 리턴된다.
         * 참고로 입력된 날짜값들 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * @param date    :    기준 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    String
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDateString(date: String, type: Int, value: Int): String {
            return getAddDateString(date, type, value, getDateFormat(date))
        }

        /**
         * 입력된 Date 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 반환된 날짜 패턴은 기본 14자리(yyyyMMddHHmmss)의 문자열로 리턴된다.
         * @param date    :    Date 객체
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    String
         */
        fun getAddDateString(date: Date?, type: Int, value: Int): String {
            return getAddDateString(date, type, value, FORMAT_FULL_DEFAULT)
        }

        /**
         * 입력된 Calendar 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 반환된 날짜 패턴은 기본 14자리(yyyyMMddHHmmss)의 문자열로 리턴된다.
         * @param calendar    :    Calendar
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    String
         */
        fun getAddDateString(calendar: Calendar, type: Int, value: Int): String {
            return getAddDateString(calendar, type, value, FORMAT_FULL_DEFAULT)
        }

        /**
         * 입력된 날짜값을 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 반환되는 날짜의 패턴은 마지막 인자에 입력된 패턴에 맞춰 반환한다.
         * 참고로 입력되는 날짜값 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * @param date            :    기준 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param type            :    가감할 타입
         * @param value            :    가감할 값
         * @param returnFormat    :    리턴할 날짜 포멧
         * @return    String
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDateString(date: String, type: Int, value: Int, returnFormat: String?): String {
            return getDateString(getAddDate(date, type, value), returnFormat)
        }

        /**
         * 입력된 Date 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 반환되는 날짜의 패턴은 마지막 인자에 입력된 패턴에 맞춰 반환한다.
         * @param date            :    Date 객체
         * @param type            :    가감할 타입
         * @param value            :    가감할 값
         * @param returnFormat    :    리턴할 날짜 포멧
         * @return    String
         */
        fun getAddDateString(date: Date?, type: Int, value: Int, returnFormat: String?): String {
            return getDateString(getAddDate(date, type, value), returnFormat)
        }

        /**
         * 입력된 Calendar 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 반환되는 날짜의 패턴은 마지막 인자에 입력된 패턴에 맞춰 반환한다.
         * @param calendar        :    Date 객체
         * @param type            :    가감할 타입
         * @param value            :    가감할 값
         * @param returnFormat    :    리턴할 날짜 포멧
         * @return    String
         */
        fun getAddDateString(calendar: Calendar, type: Int, value: Int, returnFormat: String?): String {
            return getDateString(getAddDate(calendar, type, value), returnFormat)
        }

        /**
         * 입력된 날짜값을 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 참고로 입력되는 날짜값 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * @param date    :    기준 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    Date
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddDate(date: String, type: Int, value: Int): Date {
            return getAddDate(getDate(date), type, value)
        }

        /**
         * 입력된 Date 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * @param date    :    Date 객체
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    Date
         */
        fun getAddDate(date: Date?, type: Int, value: Int): Date {
            return getAddDate(getCalendar(date), type, value)
        }

        /**
         * 입력된 Calendar 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * @param calendar    :    Calendar 객체
         * @param type        :    가감할 타입
         * @param value        :    가감할 값
         * @return    Date
         */
        fun getAddDate(calendar: Calendar, type: Int, value: Int): Date {
            calendar.add(type, value)
            return calendar.time
        }

        /**
         * 입력된 날짜값을 기준으로 해당 타입의 값을 가감하여 반환한다.
         * 참고로 입력되는 날짜값 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * @param date    :    기준 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    Calendar
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getAddCalendar(date: String, type: Int, value: Int): Calendar {
            val calendar = getCalendar(date)
            return getAddCalendar(calendar, type, value)
        }

        /**
         * 입력된 Date 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * @param date    :    Date 객체
         * @param type    :    가감할 타입
         * @param value    :    가감할 값
         * @return    Calendar
         */
        fun getAddCalendar(date: Date?, type: Int, value: Int): Calendar {
            val calendar = getCalendar(date)
            return getAddCalendar(calendar, type, value)
        }

        /**
         * 입력된 Calendar 객체를 기준으로 해당 타입의 값을 가감하여 반환한다.
         * @param calendar    :    Date 객체
         * @param type        :    가감할 타입
         * @param value        :    가감할 값
         * @return    Calendar
         */
        fun getAddCalendar(calendar: Calendar, type: Int, value: Int): Calendar {
            calendar.add(type, value)
            return calendar
        }

        /**
         * 입력된 두 날짜값 문자열 사이의 일 수를 구한다.
         * 참고로 입력되는 날짜값 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * 입력되는 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param from    :    날짜값 문자열
         * @param to    :    날짜값 문자열
         * @return    int
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getDiffDay(from: String, to: String): Int {
            return getDiffDay(getCalendar(from), getCalendar(to))
        }

        /**
         * 입력된 두 날짜 Date 사이의 일 수를 구한다.
         * @param from    :    Date 객체
         * @param to    :    Date 객체
         * @return    int
         */
        fun getDiffDay(from: Date?, to: Date?): Int {
            return getDiffDay(getCalendar(from), getCalendar(to))
        }

        /**
         * 입력된 두 날짜 Calendar 사이의 일 수를 구한다.
         * @param from    :    Calendar 객체
         * @param to    :    Calendar 객체
         * @return    int
         */
        fun getDiffDay(from: Calendar, to: Calendar): Int {
            return getDiff(from, to, DAY).toInt()
        }

        /**
         * 입력된 두 날짜값 문자열 사이의 차이를 구한다.
         * 참고로 입력되는 날짜값 사이사이에 어떤 기호나 문자가 있어도 상관없이 처리한다.(예: yyyy-MM-dd HH:mm:ss , yyyy년 MM월 dd일 등등)
         * 입력되는 날짜값(yyyyMMddHHmmss 처럼 값들중 맨 앞자리부터 있는 만큼 예:yyyy, yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss)
         * @param from    :    날짜값 문자열
         * @param to    :    날짜값 문자열
         * @param type    :    차이를 구할값의 타입
         * @return    long
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun getDiff(from: String, to: String, type: Int): Long {
            return getDiff(getCalendar(from), getCalendar(to), type)
        }

        /**
         * 입력된 두 날짜 Date 사이의 차이를 구한다.
         * @param from    :    Date 객체
         * @param to    :    Date 객체
         * @param type    :    차이를 구할값의 타입
         * @return    long
         */
        fun getDiff(from: Date?, to: Date?, type: Int): Long {
            return getDiff(getCalendar(from), getCalendar(to), type)
        }

        /**
         * 입력된 두 날짜 Calendar 사이의 차이를 구한다.
         * @param from    :    Calendar 객체
         * @param to    :    Calendar 객체
         * @param type    :    차이를 구할값의 타입
         * @return    long
         */
        fun getDiff(from: Calendar, to: Calendar, type: Int): Long {
            var `val`: Long = 1000
            if (type <= MINUTE) {
                `val` *= 60
                from[SECOND] = 0
                to[SECOND] = 0
            }
            if (type <= HOUR) {
                `val` *= 60
                from[MINUTE] = 0
                to[MINUTE] = 0
            }
            if (type <= DAY) {
                `val` *= 24
                from[HOUR] = 0
                to[HOUR] = 0
            }
            if (type <= MONTH) {
                `val` *= 30
                from[DAY] = 1
                to[DAY] = 1
            }
            if (type <= YEAR) {
                `val` = (1000 * 60 * 60 * 24 * 365).toLong()
                from[MONTH] = 0
                to[MONTH] = 0
            }
            return (from.timeInMillis - to.timeInMillis) / `val`
        }

        /**
         * 입력된 날짜 문자열에 해당하는 날짜 포멧을 반환환다.
         * @param date    :    날짜값 문자열
         * @return
         */
        fun getDateFormat(date: String): String {
            val sb = StringBuffer()
            val str = date.replace(
                "([^0-9]*)[0-9]{4}([^0-9]*)([0-9]{2}([^0-9]*))?([0-9]{2}([^0-9]*))?([0-9]{2}([^0-9]*))?([0-9]{2}([^0-9]*))?([0-9]{2}([^0-9]*))?".toRegex(),
                "$1@#$2@#$4@#$6@#$8@#$10@#$12"
            )
            val pattern = str.split("@#".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val patternLen = pattern.size
            val len = date.replace("[^0-9]".toRegex(), "").length
            if (len >= 4) {
                if (patternLen > 0) sb.append(pattern[0])
                sb.append(FORMAT_YEAR)
                if (patternLen > 1) sb.append(pattern[1])
            }
            if (len >= 6) {
                sb.append(FORMAT_MONTH)
                if (patternLen > 2) sb.append(pattern[2])
            }
            if (len >= 8) {
                sb.append(FORMAT_DAY)
                if (patternLen > 3) sb.append(pattern[3])
            }
            if (len >= 10) {
                sb.append(FORMAT_HOUR)
                if (patternLen > 4) sb.append(pattern[4])
            }
            if (len >= 12) {
                sb.append(FORMAT_MINUTE)
                if (patternLen > 5) sb.append(pattern[5])
            }
            if (len >= 14) {
                sb.append(FORMAT_SECOND)
                if (patternLen > 6) sb.append(pattern[6])
            }
            return sb.toString()
        }
    }
}