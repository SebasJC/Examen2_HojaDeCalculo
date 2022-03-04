package com.gradle.calculo;

/**
 * Clase que crea objetos relacionados con hojas de cálculo e imprime.
 */
public class HojaDeCalculo
{
  /**
   * Filas de la clase.
   */
  int rows;

  /**
   * Columnas de la clase.
   */
  int cols;

  /**
   * Matriz que tiene los datos de la clase.
   */
  String [][] datos;

  /**
   * Constructor de la clase.
   *
   * @param rows Recibe una variable de tipo {@code int} que tiene la cantidad
   *             de filas de la clase.
   * @param cols Recibe una variable de tipo {@code int} que tiene la cantidad
   *             de columnas de la clase.
   * @param datos Recibe una matriz de tipo {@code String[][]} que tiene los
   *              datos guardados para la clase.
   */
  HojaDeCalculo(int rows, int cols, String[][] datos){
    this.rows = rows;
    this.cols = cols;
    this.datos = datos;

  }

  /**
   * Método que imprime la tabla completa con los cambios realizados.
   *
   * @param rows Recibe una variable de tipo {@code int} que indica la cantidad
   *             de filas que tiene la tabla.
   * @param cols Recibe una variable de tipo {@code int} que indica la cantidad
   *             de columnas que tiene la tabla.
   * @param datos Recibe una matriz de tipo {@code String[][]} que contiene los
   *              elementos de la matriz ingresada al programa.
   */
  public static void Imprimir(int rows, int cols, String[][] datos){
    //Variables que se usarán para sacar el mayor número de espacios que ocupará
    //un elemento de la tabla dentro de lo que se está analizando.
    int size;
    int max = 0;

    //Dos ciclos for que exploran la matriz y sacan la mayor cantidad de
    // caracteres que ocupa el valor más grande que se analiza.
    for (int i = 0; i < rows; i++){
      for (int k = 0; k < cols; k++){
        size = datos[i][k].length();
        if (i == 0 && k == 0){
          max = size;
        }
        else if (max < size){
          max = size;
        }
      }
    }

    //Char que almacena las letras del abecedario.
    char [] letras = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    //Espacio de separación de números.
    int espacioNum = 3;

    //Variable que reemplazará tendrá "-" debajo de las letras de las columnas
    //de la tabla.
    String lineaDivisora = new String(new char[max]).replace("\0",
        "-");

    //Imprimir pared de tabla.
    System.out.print("   |");

    //For que imprime el contenido de "letras" ocupando la cantidad "max" de
    //caracteres para las letras encima de los números.
    for (int i = 0; i < cols; i ++){
      System.out.printf("%"+max+"C ", letras[i]);
    }

    //Se pasa a la siguiente línea donde se imprime "---+" como parte de la
    //tabla.
    System.out.println();
    System.out.print("---+");

    //For que imprime las líneas divisorias debajo de las letras.
    for (int i = 0; i < cols; i ++){
      System.out.printf("%s ", lineaDivisora);
    }

    //Salto de línea.
    System.out.println();

    //Dos ciclos for que imprimen los datos de la matriz en la tabla.
    for (int i = 0; i < rows; i ++){
      System.out.printf("%"+espacioNum+"d|", i+1);
      for (int k = 0; k < cols; k++){
        System.out.printf("%"+max+"s ", datos[i][k]);
      }
      System.out.println();
    }

  }

  /**
   * Método que imprime los conjuntos formados en la matriz.
   *
   * @param nombres Recibe una variable de tipo {@code String} que indica el
   *                nombre del conjunto.
   * @param arregloConjuntos Recibe un arreglo de tipo {@code String[]} que
   *                         contiene los datos que conforman el conjunto.
   * @param datos Recibe una matriz de tipo {@code String[][]} que contiene los
   *              datos almacenados en toda la matriz.
   */
  public static void imprimirConjunto (String nombres,
                                       String[] arregloConjuntos,
                                       String[][] datos){

    //Se imprime el nombre del conjunto y una flecha a su lado.
    System.out.printf("%s -> ", nombres);

    //Ciclo for que obtiene las posiciones de los elementos de la matriz que son
    //parte del conjunto e imprime los elementos en esas posiciones.
    for (int i = 1; i < arregloConjuntos.length; i++){
      char row = arregloConjuntos[i].charAt(1);
      int row1 = Character.getNumericValue(row);
      row1 = row1 - 1;
      int col1 = arregloConjuntos[i].charAt(0);
      col1 = col1 - 65;

      System.out.printf("%s ", datos[row1][col1]);
    }
    System.out.println("");
  }

} //java -jar HojaCalculo-1.0.jar < ../../tests/input006.txt