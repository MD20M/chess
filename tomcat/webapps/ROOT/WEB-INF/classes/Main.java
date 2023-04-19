import java.io.*;
import java.util.*;  
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Main extends HttpServlet {
  private String[][] array = {
    {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
    {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
    {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
};

    public static int cToNum(String cPos) {
      if (cPos.equals("A")) {
       return 0;
     }
      if (cPos.equals("B")) {
        return 1;
      }
      if (cPos.equals("C")) {
        return 2;
     }
      if (cPos.equals("D")) {
       return 3;
      }
      if (cPos.equals("E")) {
       return 4;
      }
      if (cPos.equals("F")) {
        return 5;
      }
      if (cPos.equals("G")) {
        return 6;
      }
      if (cPos.equals("H")) {
        return 7;
      }
      return 0;
    }

    public static int cToNumB(String cPos) {
      if (cPos.equals("A")) {
       return 7;
     }
      if (cPos.equals("B")) {
        return 6;
      }
      if (cPos.equals("C")) {
        return 5;
     }
      if (cPos.equals("D")) {
       return 4;
      }
      if (cPos.equals("E")) {
       return 3;
      }
      if (cPos.equals("F")) {
        return 2;
      }
      if (cPos.equals("G")) {
        return 1;
      }
      if (cPos.equals("H")) {
        return 0;
      }
      return 0;
    }
    
    public static List<String> legalMove(String piece, int i, String jl, String[][] t, int turn){
      List<String> legalMovesList = new ArrayList<String>();
      int j = 0;
      if (turn%2==0){
        j = cToNum(jl);
      }
      else{
        j = cToNumB(jl);
      }
      
      if (piece=="♙"){
        if (i-1 <= t.length && t[i-1][j].equals(" ")){
          legalMovesList.add(jl+""+i);
        }
        if (i==6 && t[i-2][j].equals(" ")){
          legalMovesList.add(jl+""+(i-1));
        }
        if (j-1 >= 0 && i-1 <= t.length && t[i-1][j-1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+i);
        }
        if (j+1 <= t.length && i-1 <= t.length && t[i-1][j+1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+i);
        }
      }
      if (piece=="♟"){
        if (i+1 <= t.length && t[i+1][j].equals(" ")){
          legalMovesList.add(jl+""+(i+2));
        }
        if (i==1 && t[i+2][j].equals(" ")){
          legalMovesList.add(jl+""+(i+3));
        }
        if (j-1 >= 0 && i+1 <= t.length && t[i+1][j-1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+2));
        }
        if (j+1 <= t.length && i+1 <= t.length && t[i+1][j+1]!=" "){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+2));
        }
      }
      if (piece=="♜"){
        for (int k = i+1; k < t.length; k++){
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
      if (piece=="♖"){
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
      if (piece=="♝"){
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
      if (piece=="♗"){
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
      if (piece=="♕"){
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
      if (piece=="♘"){
        if (i+2 < t.length && j+1 < t.length && (t[i+2][j+1].equals(" ") || t[i+2][j+1].equals("♜") || t[i+2][j+1].equals("♞") || t[i+2][j+1].equals("♝") || t[i+2][j+1].equals("♛") || t[i+2][j+1].equals("♚") || t[i+2][j+1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+1));
        }
        if (i+2 < t.length && j-1 >= 0 && (t[i+2][j-1].equals(" ") || t[i+2][j-1].equals("♜") || t[i+2][j-1].equals("♞") || t[i+2][j-1].equals("♝") || t[i+2][j-1].equals("♛") || t[i+2][j-1].equals("♚") || t[i+2][j-1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+1));
        }
        if (i-2 >= 0 && j+1 < t.length && (t[i-2][j+1].equals(" ") || t[i-2][j+1].equals("♜") || t[i-2][j+1].equals("♞") || t[i-2][j+1].equals("♝") || t[i-2][j+1].equals("♛") || t[i-2][j+1].equals("♚") || t[i-2][j+1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i-1));
        }
        if (i-2 >= 0 && j-1 >= 0 && (t[i-2][j-1].equals(" ") || t[i-2][j-1].equals("♜") || t[i-2][j-1].equals("♞") || t[i-2][j-1].equals("♝") || t[i-2][j-1].equals("♛") || t[i-2][j-1].equals("♚") || t[i-2][j-1].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i-1));
        }
        if (i+1 < t.length && j+2 < t.length && (t[i+1][j+2].equals(" ") || t[i+1][j+2].equals("♜") || t[i+1][j+2].equals("♞") || t[i+1][j+2].equals("♝") || t[i+1][j+2].equals("♛") || t[i+1][j+2].equals("♚") || t[i+1][j+2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i+1));
        }
        if (i+1 < t.length && j-2 >= 0 && (t[i+1][j-2].equals(" ") || t[i+1][j-2].equals("♜") || t[i+1][j-2].equals("♞") || t[i+1][j-2].equals("♝") || t[i+1][j-2].equals("♛") || t[i+1][j-2].equals("♚") || t[i+1][j-2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i+1));
        }
        if (i-1 >= 0 && j+2 < t.length && (t[i-1][j+2].equals(" ") || t[i-1][j+2].equals("♜") || t[i-1][j+2].equals("♞") || t[i-1][j+2].equals("♝") || t[i-1][j+2].equals("♛") || t[i-1][j+2].equals("♚") || t[i-1][j+2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)+2)+""+(i+1));
        }
        if (i-1 >= 0 && j-2 >= 0 && (t[i-1][j-2].equals(" ") || t[i-1][j-2].equals("♜") || t[i-1][j-2].equals("♞") || t[i-1][j-2].equals("♝") || t[i-1][j-2].equals("♛") || t[i-1][j-2].equals("♚") || t[i-1][j-2].equals("♟"))){
          legalMovesList.add((char)(jl.charAt(0)-2)+""+(i-1));
        }
      }
      if (piece=="♞"){
        if (i+2 < t.length && j+1 < t.length && (t[i+2][j+1].equals(" ") || t[i+2][j+1].equals("♖") || t[i+2][j+1].equals("♘") || t[i+2][j+1].equals("♗") || t[i+2][j+1].equals("♕") || t[i+2][j+1].equals("♔") || t[i+2][j+1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i+3));
        }
        if (i+2 < t.length && j-1 >= 0 && (t[i+2][j-1].equals(" ") || t[i+2][j-1].equals("♖") || t[i+2][j-1].equals("♘") || t[i+2][j-1].equals("♗") || t[i+2][j-1].equals("♕") || t[i+2][j-1].equals("♔") || t[i+2][j-1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i+3));
        }
        if (i-2 >= 0 && j+1 < t.length && (t[i-2][j+1].equals(" ") || t[i-2][j+1].equals("♖") || t[i-2][j+1].equals("♘") || t[i-2][j+1].equals("♗") || t[i-2][j+1].equals("♕") || t[i-2][j+1].equals("♔") || t[i-2][j+1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)+1)+""+(i));
        }
        if (i-2 >= 0 && j-1 >= 0 && (t[i-2][j-1].equals(" ") || t[i-2][j-1].equals("♖") || t[i-2][j-1].equals("♘") || t[i-2][j-1].equals("♗") || t[i-2][j-1].equals("♕") || t[i-2][j-1].equals("♔") || t[i-2][j-1].equals("♙"))){
          legalMovesList.add((char)(jl.charAt(0)-1)+""+(i));
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

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><link href='/style.css' rel='stylesheet'/></head><body onload='startAnimation()'>");

    ServletContext context = getServletContext();
    Integer count = (Integer) context.getAttribute("count");
    if (count == null) {
      count = 0;
    } else {
      count++;
    }
    context.setAttribute("count", count);
    if (count%2 !=0){
      out.println("<script src='script.js'></script>");
    }
    

    // check if a value is being updated
    String From = request.getParameter("From");
    String To = request.getParameter("To");
    
    if (From != null && To != null) {
      try {
       String newValue = " ";
       String[]location = From.split("");
       int i = Integer.parseInt(location[1])-1;
       int j =0;
       if (count%2!=0){
        j = cToNum(location[0]);
       }
       else{
        j = cToNumB(location[0]);
       }
       


        // check if index is within bounds
        if (i < 0 || i >= array.length) {
         out.println("<br>");
         out.println("Invalid index: " + i);
         return;
        }

        // update the value in the array
        String oldValue = array[i][j];
        array[i][j] = newValue;


        List<String> legalM = legalMove(oldValue, i, location[0], array, count);
        out.println(legalM.size() + "SIZE!!!!");
        for (String lm:legalM){
          out.println("<p>"+From + " Move from where"+"<p>");
          out.println("<p>"+To + " Move to where" +"<p>");
          out.println("<p>"+lm + " Legal move"+"<p>");
          if (lm.equals(To)){
            out.println("IT IS LEGAL!!!!!!");
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

        array[i][j] = oldValue;

        

       out.println("<br>");
       out.println("Value at index " + i + " has been updated to " + newValue);
      } catch (NumberFormatException e) {
        out.println("<br>");
        out.println("Invalid value: " + From);
      }
    }
    // display the board
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
     }  

    

    // display the form to update a value
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
