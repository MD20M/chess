//tranlsate this code into .jsp and replace vh.readLine() with input fields: "import java.io.*;public class sah { public static void move(String[][] t) throws IOException { BufferedReader vh = new BufferedReader(new InputStreamReader(System.in)); System.out.println("What piece do you want to move?"); String moveWhere = vh.readLine(); String[] from = moveWhere.split(""); String lineF = from[0]; int columnF = Integer.parseInt(from[1]); System.out.println("Where do you want to move it?"); String moveTo = vh.readLine(); String[] to1 = moveTo.split(""); String lineT = to1[0]; int columnT = Integer.parseInt(to1[1]); int lineNum;t[columnT - 1][cToNum(to1)] = t[columnF - 1][cToNum(from)]; t[columnF - 1][cToNum(from)] = "[]"; board(t); System.out.println(); }public static int cToNum(String[] get) { if (get[0].equals("A")) { return 0; } if (get[0].equals("B")) { return 1; } if (get[0].equals("C")) { return 2; } if (get[0].equals("D")) { return 3; } if (get[0].equals("E")) { return 4; } if (get[0].equals("F")) { return 5; } if (get[0].equals("G")) { return 6; } if (get[0].equals("H")) { return 7; } return 0; }public static void board(String[][] t) { for (int i = 7; i >= 0; i--) { System.out.print(i + 1); for (int j = 0; j < 8; j++) { System.out.printf("%6s", t[i][j]); } System.out.println(); } for (int k = 0; k < 8; k++) { String[] l = { "A", "B", "C", "D", "E", "F", "G", "H" }; System.out.printf("%6s", l[k]); } System.out.println(); }public static boolean hasKing(String[][] t) { for (int i = 7; i >= 0; i--) { for (int j = 0; j < 8; j++) { if (t[i][j].equals("K")) { return true; } } } return false; }public static void main(String[] args) throws IOException { BufferedReader vh = new BufferedReader(new InputStreamReader(System.in)); String[][] t = { { "R", "K", "B", "Q", "K", "B", "K", "B" }, { "P", "P", "P", "P", "P", "P", "P", "P" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "P", "P", "P", "P", "P", "P", "P", "P" }, { "R", "K", "B", "Q", "K", "B", "K", "R" } }; while (hasKing(t)) { board(t); move(t); System.out.println(""); } board(t); move(t); } }"
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> <%@ page import="java.io.*"%> <%@ page import="java.util.*"%> <%@ page import="java.lang.*"%> <%@ page import="java.util.Scanner"%> <%@ page import="java.util.Arrays"%> <%@ page import="java.util.ArrayList"%> <%@ page import="java.util.List"%> <%@ page import="java.util.Collections"%> <%@ page import="java.util.Comparator"%> <%@ page import="java.util.Iterator"%> <%@ page import="java.util.ListIterator"%> <%@ page import="java.util.Random"%> <%@ page import="java.util.Scanner"%> <%@ page import="java.util.Set"%> <%@ page import="java.util.TreeSet"%> <%@ page import="java.util.Vector"%> <%@ page import="java.util.regex.Pattern"%> <%@ page import="java.util.regex.Matcher"%> <%@ page import="java.util.regex.PatternSyntaxException"%> <%@ page i