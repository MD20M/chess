//Namen tega programa je možnost igranja šaha s njo.
//Program ne zahteva posebne strojne opreme saj ne porablja veliko računalniških virov. Deluje na vseh operacijskih sistemih je pa zanje treba naložiti pravilno različico Tomcat omrežnega vmesnika.
//Uporabnik uporablja program s vpisom premikov šahovskih figur v mesta kamor jih želi premakniti. Program preveri ali je premik legalen in če je premakne figuro. Če premik ni legalen uporabniku javi napako in ga prosi za ponovni vnos.
//Uporabljeni moduli so java.io.*, java.util.*, jakarta.servlet.* in jakarta.servlet.http.*
//Program potrebuje približno pol sekunde ćasa za izvedbo vsakega premika.
//Razvoj programa se je začel brez omrežnega vmesnika Tomcat s izpisom figur v konzoli. Ko je bila konzola zamenjana z omrežnim vmesnikom Tomcat je bilo potrebno spremeniti način izpisa figur in vpis njihovih premikov. Dodani so bili tudi pravilni premiki.

import java.io.*;
import java.util.*;  
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Main extends HttpServlet {
  //šahovnica predstavljena s dvodimenzionalnim poljem
  public String[][] array = {
    {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
    {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
    {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
  };

    public static int cToNum(String cPos) { //Metoda pretvori koordinato y bele figure predstavljeno s črko v število
      if (cPos.equals("A")) {
       return 0;
     }
      else if (cPos.equals("B")) {
        return 1;
      }
      else if (cPos.equals("C")) {
        return 2;
     }
      else if (cPos.equals("D")) {
       return 3;
      }
      else if (cPos.equals("E")) {
       return 4;
      }
      else if (cPos.equals("F")) {
        return 5;
      }
      else if (cPos.equals("G")) {
        return 6;
      }
      else if (cPos.equals("H")) {
        return 7;
      }
    }

    public static int cToNumB(String cPos) { //Metoda pretvori koordinato y bele figure predstavljeno s črko v število
      if (cPos.equals("A")) {
       return 7;
     }
      else if (cPos.equals("B")) {
        return 6;
      }
      else if (cPos.equals("C")) {
        return 5;
     }
      else if (cPos.equals("D")) {
       return 4;
      }
      else if (cPos.equals("E")) {
       return 3;
      }
      else if (cPos.equals("F")) {
        return 2;
      }
      else if (cPos.equals("G")) {
        return 1;
      }
      else if (cPos.equals("H")) {
        return 0;
      }
    }
    
    public static List<String> legalMove(String piece, int i, String jl, String[][] t, int turn){ //Metoda preveri ali je premik figure legalen
      List<String> legalMovesList = new ArrayList<String>();
      int j = 0;
      if (turn%2==0){
        j = cToNum(jl);
      }
      else{
        j = cToNumB(jl);
      }
      
      if (piece=="♙"){ //Legalni premiki za belega kmeta
        if (i-1 <= t.length && t[i-1][j].equals(" ")){
          legalMovesList.add(jl+""+(i-1));
        }
        if (i==6 && t[i-2][j].equals(" ")){
          legalMovesList.add(jl+""+(i-2));
        }
        if (j-1 >= 0 && i-1 <= t.length && t[i-1][j-1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i));
        }
        if (j+1 <= t.length && i-1 <= t.length && t[i-1][j+1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i));
        }
      }
      if (piece=="♟"){ //Legalni premiki za črnega kmeta
        if (i+1 <= t.length && t[i+1][j].equals(" ")){
          legalMovesList.add(jl+""+(i+2));
        }
        if (i==1 && t[i+2][j].equals(" ")){
          legalMovesList.add(jl+""+(i+3));
        }
        if (j-1 >= 0 && i+1 <= t.length && t[i+1][j-1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+1));
        }
        if (j+1 <= t.length && i+1 <= t.length && t[i+1][j+1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+1));
        }
      }
      if (piece=="♜"){ //Legalni premiki za črno trdnjavo
        for (int k = i; k < t.length; k++){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♙") || t[k][j].equals("♘") || t[k][j].equals("♗") || t[k][j].equals("♕") || t[k][j].equals("♔") || t[k][j].equals("♖")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = i-1; k >= 0; k--){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♙") || t[k][j].equals("♘") || t[k][j].equals("♗") || t[k][j].equals("♕") || t[k][j].equals("♔") || t[k][j].equals("♖")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = j+1; k < t.length; k++){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          else if (t[i][k].equals("♙") || t[i][k].equals("♘") || t[i][k].equals("♗") || t[i][k].equals("♕") || t[i][k].equals("♔") || t[i][k].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
        for (int k = j-1; k >= 0; k--){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          else if (t[i][k].equals("♙") || t[i][k].equals("♘") || t[i][k].equals("♗") || t[i][k].equals("♕") || t[i][k].equals("♔") || t[i][k].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
      }
      if (piece=="♖"){ //Legalni premiki za belo trdnjavo
        for (int k = i; k < t.length; k++){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = i-1; k >= 0; k--){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = j+1; k < t.length; k++){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
        for (int k = j-1; k >= 0; k--){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
      }
      if (piece=="♝"){ //Legalni premiki za črnega tekača
        for (int k = i+1, l = j+1; k < t.length && l < t.length; k++, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♙") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♔") || t[k][l].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j-1; k >= 0 && l >= 0; k--, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♙") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♔") || t[k][l].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i+1, l = j-1; k < t.length && l >= 0; k++, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♙") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♔") || t[k][l].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j+1; k >= 0 && l < t.length; k--, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♙") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♔") || t[k][l].equals("♖")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
      }
      if (piece=="♗"){ //Legalni premiki za belega tekača
        for (int k = i+1, l = j+1; k < t.length && l < t.length; k++, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j-1; k >= 0 && l >= 0; k--, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i+1, l = j-1; k < t.length && l >= 0; k++, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j+1; k >= 0 && l < t.length; k--, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
      }
      if (piece=="♕"){ //Legalni premiki za belo kraljico
        for (int k = i+1, l = j+1; k < t.length && l < t.length; k++, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j-1; k >= 0 && l >= 0; k--, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i+1, l = j-1; k < t.length && l >= 0; k++, l--){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i-1, l = j+1; k >= 0 && l < t.length; k--, l++){
          if (t[k][l].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
          }
          else if (t[k][l].equals("♜") || t[k][l].equals("♞") || t[k][l].equals("♝") || t[k][l].equals("♛") || t[k][l].equals("♚") || t[k][l].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+l-j)+""+(k+1));
            break;
          }
        }
        for (int k = i+1; k < t.length; k++){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = i-1; k >= 0; k--){
          if (t[k][j].equals(" ")){
            legalMovesList.add(jl+""+(k+1));
          }
          else if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add(jl+""+(k+1));
            break;
          }
        }
        for (int k = j+1; k < t.length; k++){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
        for (int k = j-1; k >= 0; k--){
          if (t[i][k].equals(" ")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
          }
          if (t[k][j].equals("♜") || t[k][j].equals("♞") || t[k][j].equals("♝") || t[k][j].equals("♛") || t[k][j].equals("♚") || t[k][j].equals("♟")){
            legalMovesList.add((char)(jl.charAt(0)+k-j)+""+(i+1));
            break;
          }
        }
      }

      if (piece=="♘"){ //Legalni premiki za belega konja
        if (i+2 < t.length && j+1 < t.length && (t[i+2][j+1].equals(" ") || t[i+2][j+1].equals("♜") || t[i+2][j+1].equals("♞") || t[i+2][j+1].equals("♝") || t[i+2][j+1].equals("♛") || t[i+2][j+1].equals("♚") || t[i+2][j+1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+2));
        }
        if (i+2 < t.length && j-1 >= 0 && (t[i+2][j-1].equals(" ") || t[i+2][j-1].equals("♜") || t[i+2][j-1].equals("♞") || t[i+2][j-1].equals("♝") || t[i+2][j-1].equals("♛") || t[i+2][j-1].equals("♚") || t[i+2][j-1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+2));
        }
        if (i-2 >= 0 && j+1 < t.length && (t[i-2][j+1].equals(" ") || t[i-2][j+1].equals("♜") || t[i-2][j+1].equals("♞") || t[i-2][j+1].equals("♝") || t[i-2][j+1].equals("♛") || t[i-2][j+1].equals("♚") || t[i-2][j+1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i-1));
        }
        if (i-2 >= 0 && j-1 >= 0 && (t[i-2][j-1].equals(" ") || t[i-2][j-1].equals("♜") || t[i-2][j-1].equals("♞") || t[i-2][j-1].equals("♝") || t[i-2][j-1].equals("♛") || t[i-2][j-1].equals("♚") || t[i-2][j-1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i-1));
        }
        if (i+1 < t.length && j+2 < t.length && (t[i+1][j+2].equals(" ") || t[i+1][j+2].equals("♜") || t[i+1][j+2].equals("♞") || t[i+1][j+2].equals("♝") || t[i+1][j+2].equals("♛") || t[i+1][j+2].equals("♚") || t[i+1][j+2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i));
        }
        if (i+1 < t.length && j-2 >= 0 && (t[i+1][j-2].equals(" ") || t[i+1][j-2].equals("♜") || t[i+1][j-2].equals("♞") || t[i+1][j-2].equals("♝") || t[i+1][j-2].equals("♛") || t[i+1][j-2].equals("♚") || t[i+1][j-2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i));
        }
        if (i-1 >= 0 && j+2 < t.length && (t[i-1][j+2].equals(" ") || t[i-1][j+2].equals("♜") || t[i-1][j+2].equals("♞") || t[i-1][j+2].equals("♝") || t[i-1][j+2].equals("♛") || t[i-1][j+2].equals("♚") || t[i-1][j+2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i));
        }
        if (i-1 >= 0 && j-2 >= 0 && (t[i-1][j-2].equals(" ") || t[i-1][j-2].equals("♜") || t[i-1][j-2].equals("♞") || t[i-1][j-2].equals("♝") || t[i-1][j-2].equals("♛") || t[i-1][j-2].equals("♚") || t[i-1][j-2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i));
        }
      }
      if (piece=="♞"){ //Legalni premiki za črnega konja
        if (i+2 < t.length && j+1 < t.length && (t[i+2][j+1].equals(" ") || t[i+2][j+1].equals("♖") || t[i+2][j+1].equals("♘") || t[i+2][j+1].equals("♗") || t[i+2][j+1].equals("♕") || t[i+2][j+1].equals("♔") || t[i+2][j+1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+3));
        }
        if (i+2 < t.length && j-1 >= 0 && (t[i+2][j-1].equals(" ") || t[i+2][j-1].equals("♖") || t[i+2][j-1].equals("♘") || t[i+2][j-1].equals("♗") || t[i+2][j-1].equals("♕") || t[i+2][j-1].equals("♔") || t[i+2][j-1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+3));
        }
        if (i-2 >= 0 && j+1 < t.length && (t[i-2][j+1].equals(" ") || t[i-2][j+1].equals("♖") || t[i-2][j+1].equals("♘") || t[i-2][j+1].equals("♗") || t[i-2][j+1].equals("♕") || t[i-2][j+1].equals("♔") || t[i-2][j+1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i-2));
        }
        if (i-2 >= 0 && j-1 >= 0 && (t[i-2][j-1].equals(" ") || t[i-2][j-1].equals("♖") || t[i-2][j-1].equals("♘") || t[i-2][j-1].equals("♗") || t[i-2][j-1].equals("♕") || t[i-2][j-1].equals("♔") || t[i-2][j-1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i-2));
        }
        if (i+1 < t.length && j+2 < t.length && (t[i+1][j+2].equals(" ") || t[i+1][j+2].equals("♖") || t[i+1][j+2].equals("♘") || t[i+1][j+2].equals("♗") || t[i+1][j+2].equals("♕") || t[i+1][j+2].equals("♔") || t[i+1][j+2].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i+1));
        }
        if (i+1 < t.length && j-2 >= 0 && (t[i+1][j-2].equals(" ") || t[i+1][j-2].equals("♖") || t[i+1][j-2].equals("♘") || t[i+1][j-2].equals("♗") || t[i+1][j-2].equals("♕") || t[i+1][j-2].equals("♔") || t[i+1][j-2].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i+1));
        }
        if (i-1 >= 0 && j+2 < t.length && (t[i-1][j+2].equals(" ") || t[i-1][j+2].equals("♖") || t[i-1][j+2].equals("♘") || t[i-1][j+2].equals("♗") || t[i-1][j+2].equals("♕") || t[i-1][j+2].equals("♔") || t[i-1][j+2].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i-1));
        }
        if (i-1 >= 0 && j-2 >= 0 && (t[i-1][j-2].equals(" ") || t[i-1][j-2].equals("♖") || t[i-1][j-2].equals("♘") || t[i-1][j-2].equals("♗") || t[i-1][j-2].equals("♕") || t[i-1][j-2].equals("♔") || t[i-1][j-2].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i-1));
        }
      }


      return legalMovesList;
    }

    public static boolean checkForCheckW(int i, int j, String[][] t, int turn){ //Metoda preveri če je kraljev premik šah

      if (t[i-1][j-1].equals("♟") || t[i-1][j+1].equals("♟")){
        return true;
      }
      else if (t[i][j].equals(" ") || t[i][j].equals("♜") || t[i][j].equals("♞") || t[i][j].equals("♝") || t[i][j].equals("♛") || t[i][j].equals("♚") || t[i][j].equals("♟")){
        for(int k = i; i<t.length; k++){
          if (t[k][j].equals("♜") || t[k][j].equals("♛")){
            return true;
          }
          if (t[k][j].equals("♖") || t[k][j].equals("♘") || t[k][j].equals("♗") || t[k][j].equals("♕") || t[k][j].equals("♙") || t[k][j].equals("♞") || t[i][j].equals("♝") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = i; k>=0; k--){
          if (t[k][j].equals("♜") || t[k][j].equals("♛")){
            return true;
          }
          if (t[k][j].equals("♖") || t[k][j].equals("♘") || t[k][j].equals("♗") || t[k][j].equals("♕") || t[k][j].equals("♙") || t[k][j].equals("♞") || t[i][j].equals("♝") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = j; k<t.length; k++){
          if (t[i][k].equals("♜") || t[i][k].equals("♛")){
            return true;
          }
          if (t[i][k].equals("♖") || t[i][k].equals("♘") || t[i][k].equals("♗") || t[i][k].equals("♕") || t[i][k].equals("♙") || t[i][k].equals("♞") || t[i][j].equals("♝") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = j; k>=0; k--){
          if (t[i][k].equals("♜") || t[i][k].equals("♛")){
            return true;
          }
          if (t[i][k].equals(t[i][k].equals("♖") || t[i][k].equals("♘") || t[i][k].equals("♗") || t[i][k].equals("♕") || t[i][k].equals("♙") || t[i][k].equals("♞") || t[i][j].equals("♝") || t[i][j].equals("♚") || t[i][j].equals("♟"))){
            break;
          }
        }
        for (int k = i, l = j; k<t.length && l<t.length; k++, l++){
          if (t[k][l].equals("♝") || t[k][l].equals("♛")){
            return true;
          }
          if (t[k][l].equals("♖") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♙") || t[k][l].equals("♞") || t[i][j].equals("♜") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = i, l = j; k>=0 && l>=0; k--, l--){
          if (t[k][l].equals("♝") || t[k][l].equals("♛")){
            return true;
          }
          if (t[k][l].equals("♖") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♙") || t[k][l].equals("♞") || t[i][j].equals("♜") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = i, l = j; k<t.length && l>=0; k++, l--){
          if (t[k][l].equals("♝") || t[k][l].equals("♛")){
            return true;
          }
          if (t[k][l].equals("♖") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♙") || t[k][l].equals("♞") || t[i][j].equals("♜") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
        for (int k = i, l = j; k>=0 && l<t.length; k--, l++){
          if (t[k][l].equals("♝") || t[k][l].equals("♛")){
            return true;
          }
          if (t[k][l].equals("♖") || t[k][l].equals("♘") || t[k][l].equals("♗") || t[k][l].equals("♕") || t[k][l].equals("♙") || t[k][l].equals("♞") || t[i][j].equals("♜") || t[i][j].equals("♚") || t[i][j].equals("♟")){
            break;
          }
        }
      }
      else if ((i+2<t.length && j+1<t.length && t[i+2][j+1].equals("♞")) || (i+2<t.length && j-1<t.length && t[i+2][j+1].equals("♞")) || (i-2<t.length && j+1<t.length && t[i+2][j+1].equals("♞")) || (i-2<t.length && j-1<t.length && t[i+2][j+1].equals("♞")) || (i+1<t.length && j+2<t.length && t[i+2][j+1].equals("♞")) || (i+1<t.length && j-2<t.length && t[i+2][j+1].equals("♞")) || (i-1<t.length && j+2<t.length && t[i+2][j+1].equals("♞")) || (i-1<t.length && j-2<t.length && t[i+2][j+1].equals("♞"))){
        return true;
      }
      else{
          return false;
      }
      return false;
    }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><link href='/style.css' rel='stylesheet'/></head><body onload='startAnimation()'>");

    ServletContext context = getServletContext();
    Integer count = (Integer) context.getAttribute("count"); // nastavimo štetje rund
    if (count == null) {
      count = 2;
    }
    context.setAttribute("count", count);
    if (count%2 !=0){
      out.println("<script src='script.js'></script>"); // če je število liho, se zažene animacija za spremembo barve v temnejšo
    }
    

    // Preveri če se je vrednost spremenila
    String From = request.getParameter("From");
    String To = request.getParameter("To");

    boolean isLegal = false;
    
    if (From != null && To != null) { // če vrednosti nista nič, se ne izvede
      try {
       String newValue = " ";
       String[]location = From.split("");
       int i = Integer.parseInt(location[1])-1;
       int j =0;
       if (count%2!=0){ // določimo če je na vrsti beli ali črni
        j = cToNum(location[0]);
       }
       else{
        j = cToNumB(location[0]);
       }
       


        // preveri če je vrednost veljavna
        if (i < 0 || i >= array.length) {
         out.println("<br>");
         out.println("Invalid index: " + i);
         return;
        }

        // shranimo figuro, ki jo premikamo
        String oldValue = array[i][j];
        



        // dobimo legalne premike figure
        List<String> legalM = legalMove(oldValue, i, location[0], array, count);
        out.println(legalM.size() + "SIZE!!!!");
        for (String lm:legalM){
          out.println("<p>"+From + " Move from where"+"<p>");
          out.println("<p>"+To + " Move to where" +"<p>");
          out.println("<p>"+lm + " Legal move"+"<p>");
          if (lm.equals(To)){
            out.println("IT IS LEGAL!!!!!!");
            isLegal = true;
            array[i][j] = newValue;
          }
        }

        location = To.split("");
        i = Integer.parseInt(location[1])-1;


        if (count%2!=0){
          j = cToNum(location[0]);
        }
        else{
          j = cToNumB(location[0]);
        }

        if (oldValue.equals("♔")){ // dobimo če je kralj v šahu v v njegovem naslednjem premiku
          if (checkForCheckW(i, j, array, count)){
            out.println("CHECK!!!!");
          }
          else{
            isLegal = true;
            out.print(array[i-1][j-1] + "<- ->" + array[i-1][j+1]);
          }
        }

        if (isLegal){
          array[i][j] = oldValue;
        }
        

        

       out.println("<br>");
       out.println("Value at index " + i + " has been updated to " + newValue);
      } catch (NumberFormatException e) {
        out.println("<br>");
        out.println("Invalid value: " + From);
      }
    }
    // izpišemo šahovnico
    if (count%2 == 0){
      out.println("<div class='board'>");
      out.println("<table>");
      for (int i = 0; i < array.length; i++) {
        out.println("<tr><td style='background-color: none; border: none; background: none;'>" + (i+1) + "</td>");
        for (int j = 0; j < array[i].length; j++){
          
          out.println("<td>"+array[i][j] + "</td>");
        }
        out.println("</tr>");
      }
      out.println("<tr>");
      out.println("<td style='background-color: none; border: none; background: none;'>" +" "+ "</td>");
      for(char i = 'A'; i <= 'H'; i++){
        out.println("<td style='background-color: none; border: none; background: none;'>" + (i) + "</td>");
      }
      out.println("</tr>");
      out.println("</table>");
      out.println("</div>");
      if (isLegal || count == 2){
        count++;
        context.setAttribute("count", count);
      }
      else{
        out.print("<div class='illegalNotification'>");
        out.print("<p>The move you entered is illegal!</p>");
        out.print("</div>");
      }
    }
    else{
      out.println("<div class='board'>");
        out.println("<table>");
        for (int i = 7; i >= 0; i--) {
          out.println("<tr><td style='background-color: none; border: none; background: none;'>" + (i+1) + "</td>");
          for (int j = 0; j < 8; j++){
            out.println("<td>"+array[i][j] + "</td>");
          }
          out.println("</tr>");
        }
        out.println("<tr>");
        out.println("<td style='background-color: none; border: none; background: none;'>" +" "+ "</td>");
        for(char i = 'H'; i >= 'A'; i--){
          out.println("<td style='background-color: none; border: none; background: none;'>" + (i) + "</td>");
        }
        out.println("</tr>");
        out.println("</table>");
        out.println("</div>");
        if (isLegal || count == 2){
          count++;
          context.setAttribute("count", count);
        }
        else{
          out.print("<div class='illegalNotification'>");
          out.print("<p>The move you entered is illegal!</p>");
          out.print("</div>");
        }
     }  

    

    // izpišemo meni za vpis premikov
    out.println("<div class='controllMenu'>");
    out.println("<form method='get'>");
    out.println("<p class='cm'>Control Menu</p><br>");
    out.println("<label>From:</label> <input type='text' name='From'><br>");
    out.println("<label>To:</label> <input type='text' name='To'><br>");
    out.println("<input type='submit' value='Update'>");
    out.println("</form>");
    out.println("</div>");
    out.println("</body></html>");
  }
}
