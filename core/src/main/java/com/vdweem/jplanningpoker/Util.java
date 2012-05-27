package com.vdweem.jplanningpoker;

import java.util.Collection;

/**
 * com.vdweem.jplanningpoker.Util
 *
 * A collection of useful functions.
 *
 * @author       Niels
 */
public class Util {

    /**
     * Determines if the input is empty. What is considered empty is dependent on the type.
     * @param in
     * @return
     */
    public static boolean isEmpty(Object in) {
        if (in == null) return true;

        if (in instanceof Collection)
            return isEmpty((Collection<?>) in);
        if (in instanceof String)
            return isEmpty((String) in);
        if (in instanceof Object[])
            return isEmpty((Object[]) in);
        throw new UnsupportedOperationException("Util.isEmpty is not yet supported on " + in.getClass() + " types.");
    }
    /**
     * Returns true if the string is empty. A string is empty when it is null or of 0 length after trimming.
     * @param in
     * @return
     */
    public static boolean isEmpty(String in) {
        return in == null || in.length() == 0 || in.trim().length() == 0;
    }
    /**
     * Determines if a collection is empty. A collection is empty when it is null or has no elements. Collections with only null elements are
     * not empty.
     * @param in
     * @return
     */
    public static boolean isEmpty(Collection<?> in) {
        return in == null || in.size() == 0;
    }
    /**
     * Determines if an array is empty. An array is empty if it is null or if it has a zero length. An array with only null elements
     * not empty.
     * @param in
     * @return
     */
    public static boolean isEmpty(Object[] in) {
        return in == null || in.length == 0;
    }
    /**
     * Performs an equals check but can be used if either of the arguments is null.
     * Useful if o1.equals(o2) may produce NPE's.
     * @param o1
     * @param o2
     * @return
     */
    public static boolean nullableEquals(Object o1, Object o2) {
        return     o1 == o2
                || o1 != null && o1.equals(o2);
    }
    /**
     * Returns the String value of the input object, but returns an empty string if the input is null.
     * @param o
     * @return
     */
    public static String nonNullString(Object o) {
        if (o == null) return "";
        return o.toString();
    }
    /**
     * Parses an Object to a string. Produces null if the input is null or the toString value is empty.
     * @param o
     * @return
     */
    public static String parseString(Object o) {
        if (o == null) return null;
        String result = o.toString();
        if (Util.isEmpty(result)) return null;
        return result;
    }
    /**
     * Parses a long from the input object. Returns null if the input can't be parsed. Never throws exceptions.
     * @param in
     * @return
     */
    public static Long parseLong(Object in) {
        try {
            return Long.parseLong(Util.parseString(in));
        } catch (Exception e) {
            return null;
        }
    }
}
