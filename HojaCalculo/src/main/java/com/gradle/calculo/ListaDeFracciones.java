package com.gradle.calculo;

/**
 * Clase asociada a los conjuntos de fracciones; suma y multiplica los
 * conjuntos.
 */
public class ListaDeFracciones {


  /**
   * Método que se encarga de las sumas de conjuntos de fracciones.
   *
   * @param arregloConjuntos Recibe un arreglo de tipo {@code String[]} con el
   *                         conjunto de celdas que se van a trabajar.
   * @param datos Recibe una matriz de tipo {@code String[][]} que tiene los
   *              datos que fueron ingresados a la hoja de cálculo.
   * @return Retorna el resultado de la suma.
   */
  public String sumaConjunto (String[] arregloConjuntos, String[][] datos) {

    //Se establecen numerador y denominador por defecto.
    String num = "0";
    String den = "1";

    //Se crea una nueva instancia de la clase.
    Fraccion f2 = new Fraccion(num, den);

    //For que cambia el valor de las letras de la celda por el número
    //correspondiente, toma los valores de numeradores y denominadores de la
    //matriz "datos" y elimina el carácter "/"; si "i" es igual a 1, f2 es igual
    //a f1, si no, se suman f1 y f2 usando el método "sumar" de la clase
    //Fraccion.
    for (int i = 1; i < arregloConjuntos.length; i++){
      char Letra = arregloConjuntos[i].charAt(1);
      int row = Character.getNumericValue(Letra);
      row = row - 1;

      Letra = arregloConjuntos[i].charAt(0);
      int col = Letra;
      col = col - 65;

      String num2 = datos[row][col].split("/")[0];
      String den2 = datos[row][col].split("/")[1];
      Fraccion f1 = new Fraccion (num2, den2);
      if (i == 1){
        f2 = f1;
      }
      else {
        f2 = f1.sumar(f2);
      }

    }
    return f2.toString();
  }

  /**
   * Método que se encarga de las multiplicaciones de conjuntos de fracciones.
   *
   * @param arregloConjuntos Recibe un arreglo de tipo {@code String[]} con el
   *                         conjunto de celdas que se van a trabajar.
   * @param datos Recibe una matriz de tipo {@code String[][]} que tiene los
   *              datos que fueron ingresados a la hoja de cálculo.
   * @return Retorna el resultado de la suma.
   */
  public String multConjunto (String[] arregloConjuntos, String[][] datos) {

    //Se establecen numerador y denominador por defecto.
    String num = "1";
    String den = "1";

    //Se crea una nueva instancia de la clase.
    Fraccion f2 = new Fraccion(num, den);


    //For que cambia el valor de las letras de la celda por el número
    //correspondiente, toma los valores de numeradores y denominadores de la
    //matriz "datos" y elimina el carácter "/"; si "i" es igual a 1, f2 es igual
    //a f1, si no, se multiplican f1 y f2 usando el método "multiplicar" de la
    //clase Fraccion.
    for (int i = 1; i < arregloConjuntos.length; i++){
      char Letra = arregloConjuntos[i].charAt(1);
      int row = Character.getNumericValue(Letra);
      row = row - 1;

      Letra = arregloConjuntos[i].charAt(0);
      int col = Letra;
      col = col - 65;

      String num2 = datos[row][col].split("/")[0];
      String den2 = datos[row][col].split("/")[1];
      Fraccion f1 = new Fraccion (num2, den2);
      if (i == 1){
        f2 = f1;
      }
      else {
        f2 = f1.multiplicar(f2);
      }

    }
    return f2.toString();
  }

}
