package com.gradle.calculo;
import java.util.*;

/**
 * Clase que maneja el programa; desde ella se llama a los métodos y se hacen
 * instancias otras clases.
 */
public class Controlador {

  /**
   * Instancia de clase Scanner que recibe los datos de la entrada estándar.
   */
  private final Scanner input = new Scanner(System.in);

  /**
   * Método que arranca el programa.
   *
   * @param args Argumentos del programa.
   */
  public static void main(final String[] args) {

    //Creación de una instancia de clase Controlador.
    Controlador controlador = new Controlador();
    //Invocación del método run.
    controlador.run();

  }

  /**
   * Variable que contiene la fila en que actualizará la información.
   */
  static int celdaRow = 0;
  /**
   * Variable que contiene la columna en que actualizará la información.
   */
  static int celdaCol = 0;

  /**
   * Método que ejecuta la lógica del programa.
   */
  public void run() {

    //Variables que indican la cantidad de filas y columnas de la matriz.
    int rows = input.nextInt();
    int cols = input.nextInt();



    input.nextLine();
    input.nextLine();

    //Creación de la matriz que se llena con lo de la entrada estándar por medio
    //de 2 ciclos for.
    input.useDelimiter("[,\\n]");
    String[][] datos = new String[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int k = 0; k < cols; k++) {
        datos[i][k] = input.next();
      }

    }

    //Dos ciclos for que exploran la matriz y reemplazan un espacio vacío por
    //un borrado de espacio en cada componente de la matriz.
    for (int i = 0; i < rows; i++) {
      for (int k = 0; k < cols; k++) {
        datos[i][k] = datos[i][k].replace(" ","");
        String num = datos[i][k].split("/")[0];
        String den = datos[i][k].split("/")[1];
        Fraccion ff = new Fraccion(num, den);
        datos [i][k] = ff.toString();
      }
    }
    input.nextLine();


    //Se establece el delimitador que salte en "(".
    input.useDelimiter("[\\n(]");


    String nombres;
    String [] arregloConjuntos;

    HashMap<String, String[]> Conjuntos = new HashMap<String, String[]>();

    //Ciclo while que indica que lo que está en su interior se ejecuta mientras
    //haya una cadena texto.
    while (input.hasNextLine() && input.hasNext()) {

      //Variable que indicará cual comando se recibirá.
      String comando = input.next();
      //System.out.println(comando);

      //Variable que recibe las posiciones que se van a usar en el comando.
      String posiciones = input.next();
      //System.out.println(posiciones);


      //Variable que indicará la letra de las posiciones
      //usadas por el comando.
      char primerLetra;

      //Variables que indicarán los numeradores y denominadores de los
      //contenidos de las posiciones usadas por el comando.
      String num1;
      String num2;
      String den1;
      String den2;

      //Variables que se usarán para explorar la matriz y sus valores y
      //modificarlos o imprimirlos de acuerdo con el comando indicado.
      int row1;
      int row2;
      int row3;
      int row4;
      int col1;
      int col2;
      int col3;
      int col4;

      //If que indica la celda en donde estará el resultado del próximo comando.
      if (comando.contains(">=CEL")) {

        //Se obtiene el número como String de la celda que se va a modificar.
        primerLetra = posiciones.charAt(1);

        //Se establece la celda con el valor de primeraLetra como int.
        celdaRow = Character.getNumericValue(primerLetra);
        celdaRow = celdaRow - 1;
        //Se obtiene la letra de la celda que se va a modificar.
        primerLetra = posiciones.charAt(0);

        //Se establece la columna de la celda que se va a modificar con lo que
        //hay en "primeraLetra".
        celdaCol = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        celdaCol = celdaCol - 65;
      }

      //If que indica que lo que tiene se ejecuta si el comando es ">=CONJUNTO".
      if (comando.contains(">=CONJUNTO")) {
        posiciones = posiciones.replace(')',' ');
        posiciones = posiciones.replaceAll(" ", "");
        nombres = posiciones.split(",")[0];


        //Se eliminan las comas en comando y se cuenta lo que hay.
        int temp = posiciones.split(",").length;
        //Se crea un arreglo de Strings de tamaño "temp".
        arregloConjuntos = new String[temp];
        //Ciclo for que explora arregloConjuntos y elimina las comas del
        //comando.
        for (int index = 1; index < temp; index++) {
          arregloConjuntos[index] = posiciones.split(",")[index];
        }
        Conjuntos.put(nombres,arregloConjuntos);
        //System.out.println(Conjuntos);
      }

      //If que indica que lo que tiene se ejecuta si el comando es ">=IMPRIMIR".
      if (comando.contains(">=IMPRIMIR")){
        posiciones = posiciones.replace(')',' ');
        posiciones = posiciones.replaceAll(" ", "");
        if (Conjuntos.containsKey(posiciones)){
          nombres = posiciones;
          arregloConjuntos = Conjuntos.get(posiciones);
          HojaDeCalculo.imprimirConjunto(nombres, arregloConjuntos, datos);
        }
        else {
          //Invocación del método que se usará para imprimir la matriz.
          HojaDeCalculo.Imprimir(rows, cols,datos);
          //Se detiene la ejecución del programa.
        }
      }


      //If que indica que lo que tiene se ejecuta si el comando es ">=SUMA".
      if (comando.contains(">=SUMA")){
        posiciones = posiciones.replace(')',' ');
        posiciones = posiciones.replaceAll(" ", "");
        if (Conjuntos.containsKey(posiciones)){
          arregloConjuntos = Conjuntos.get(posiciones);
          ListaDeFracciones suma = new ListaDeFracciones();
          datos[celdaRow][celdaCol] = suma.sumaConjunto(arregloConjuntos, datos);
        }
        else  {

          //Se obtiene el número como String de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(1);

          //Se establece la variable "row1" con el valor de primeraLetra como
          //int.
          row1 = Character.getNumericValue(primerLetra);
          row1 = row1 - 1;
          //Se obtiene la letra de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(0);

          //Se establece la columna de la celda en que se va a empezar con lo
          //que hay en "primeraLetra".
          col1 = primerLetra;

          //Se obtiene el valor numérico de la letra y se le restan 65 para que
          //quede establecido en la columna de la celda de la matriz que se
          //modifica.
          col1 = col1 - 65;

          //Se obtiene el número como String de la celda en que se va a
          //terminar.
          primerLetra = posiciones.charAt(4);

          //Se establece la variable "row2" con el valor de primeraLetra como
          //int.
          row2 = Character.getNumericValue(primerLetra);
          row2 = row2 - 1;
          //Se obtiene la letra de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(3);

          //Se establece la columna de la celda en que se va a empezar con lo
          //que hay en "primeraLetra".
          col2 = primerLetra;

          //Se obtiene el valor numérico de la letra y se le restan 65 para que
          //quede establecido en la columna de la celda de la matriz que se
          //modifica.
          col2 = col2 - 65;
          //Se establecen "num2" y "den2" como "0".
          num2 = "0";
          den2 = "1";

          //Creación de una nueva instancia de clase Fraccion con "num2" y
          //"den2".
          Fraccion f2 = new Fraccion(num2,den2);

          //Dos ciclos por donde se exploran las celdas de la matriz, se
          //establece que los numeradores y denominadores tienen los valores
          //de números que tienen sin el carácter "/", se crea una nueva
          //instancia de la clase Fraccion con "num1" y "num2" y f2 pasa a tener
          //el valor de lo que se sume entre ella y f1.
          for (int i = row1; i <= row2; i++){
            for (int k = col1; k <= col2; k++){
              num1 = datos[i][k].split("/")[0];
              den1 = datos[i][k].split("/")[1];
              Fraccion f1 = new Fraccion(num1, den1);
              if (i == row1 && k == col1){
                f2 = f1;
              }
              else{
                f2 = f1.sumar(f2);
              }
            }
          }

          //Se establece que en la celda que se modifica, estará el valor de f2.
          datos[celdaRow][celdaCol] = f2.toString();

        }
      }

      if (comando.contains(">=PROMEDIO")) {
        //Se obtiene el número como String de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(1);

        //Se establece la variable "row1" con el valor de primeraLetra como
        //int.
        row1 = Character.getNumericValue(primerLetra);
        row1 = row1 - 1;
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(0);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col1 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col1 = col1 - 65;

        //Se obtiene el número como String de la celda en que se va a
        //terminar.
        primerLetra = posiciones.charAt(4);

        //Se establece la variable "row2" con el valor de primeraLetra como
        //int.
        row2 = Character.getNumericValue(primerLetra);
        row2 = row2 - 1;
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(3);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col2 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col2 = col2 - 65;
        //Se establecen "num2" y "den2" como "0".
        num2 = "0";
        den2 = "1";

        //Creación de una nueva instancia de clase Fraccion con "num2" y
        //"den2".
        Fraccion f2 = new Fraccion(num2,den2);

        //Dos ciclos por donde se exploran las celdas de la matriz, se
        //establece que los numeradores y denominadores tienen los valores
        //de números que tienen sin el carácter "/", se crea una nueva
        //instancia de la clase Fraccion con "num1" y "num2" y f2 pasa a tener
        //el valor de lo que se sume entre ella y f1.

        int contador = 0;
        for (int i = row1; i <= row2; i++){
          for (int k = col1; k <= col2; k++){
            num1 = datos[i][k].split("/")[0];
            den1 = datos[i][k].split("/")[1];
            Fraccion f1 = new Fraccion(num1, den1);
            if (i == row1 && k == col1){
              f2 = f1;
            }
            else{
              f2 = f1.sumar(f2);
            }
            contador++;
          }
        }

        //Se establece el numerador de una nueva fracción como 1.
        String num3 = "1";

        //Se establece el denominador de una nueva fracción como el valor del
        //contador.
        String den3 = Integer.toString(contador);

        //Se establece una nueva instancia de la clase Fraccion con num3 y den3.
        Fraccion f3 = new Fraccion(num3, den3);

        //Se invoca al método "multiplicar" de la clase Fraccion con f2 y f3.
        f3 = f2.multiplicar(f3);

        //Se establece que en la celda que se modifica, estará el valor de f3.
        datos[celdaRow][celdaCol] = f3.toString();
      }

      //If que indica que lo que tiene se ejecuta si el comando es ">=MULT".
      if (comando.contains(">=MULT")) {
        posiciones = posiciones.replace(')',' ');
        posiciones = posiciones.replaceAll(" ", "");
        if (Conjuntos.containsKey(posiciones)){
          arregloConjuntos = Conjuntos.get(posiciones);
          ListaDeFracciones multiplicacion = new ListaDeFracciones();
          datos[celdaRow][celdaCol] = multiplicacion.multConjunto(arregloConjuntos, datos);
        }
        else {
          //Se obtiene el número como String de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(1);

          //Se establece la variable "row1" con el valor de primeraLetra como
          //int.
          row1 = Character.getNumericValue(primerLetra);
          row1 = row1 - 1;
          //Se obtiene la letra de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(0);

          //Se establece la columna de la celda en que se va a empezar con lo
          //que hay en "primeraLetra".
          col1 = primerLetra;

          //Se obtiene el valor numérico de la letra y se le restan 65 para que
          //quede establecido en la columna de la celda de la matriz que se
          //modifica.
          col1 = col1 - 65;

          //Se obtiene el número como String de la celda en que se va a
          //terminar.
          primerLetra = posiciones.charAt(4);

          //Se establece la variable "row2" con el valor de primeraLetra como
          //int.
          row2 = Character.getNumericValue(primerLetra);
          row2 = row2 - 1;
          //Se obtiene la letra de la celda en que se va a empezar.
          primerLetra = posiciones.charAt(3);

          //Se establece la columna de la celda en que se va a empezar con lo
          //que hay en "primeraLetra".
          col2 = primerLetra;

          //Se obtiene el valor numérico de la letra y se le restan 65 para que
          //quede establecido en la columna de la celda de la matriz que se
          //modifica.
          col2 = col2 - 65;

          //Se establecen "num2" y "den2" como "1".
          num2 = "1";
          den2 = "1";

          //Creación de una nueva instancia de clase Fraccion con "num2" y
          //"den2".

          Fraccion f2 = new Fraccion(num2,den2);

          //Dos ciclos dor donde se exploran las celdas de la matriz, se
          //establece que los numeradores y denominadores tienen los valores
          //de números que tienen sin el carácter "/", se crea una nueva
          //instancia de la clase Fraccion con "num1" y "num2" y f2 pasa a tener
          //el valor de lo que se multiplique entre ella y f1.
          for (int i = row1; i <= row2; i++){
            for (int k = col1; k <= col2; k++){
              num1 = datos[i][k].split("/")[0];
              den1 = datos[i][k].split("/")[1];
              Fraccion f1 = new Fraccion(num1, den1);
              if (i == row1 && k == col1){
                f2 = f1;
              }
              else {
                f2 = f1.multiplicar(f2);
              }
            }
          }

          //Se establece que en la celda que se modifica, estará el valor de f2.
          datos[celdaRow][celdaCol] = f2.toString();
        }
      }

      if (comando.contains(">=MEDIANA")) {
        //Se obtiene el número como String de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(1);

        //Se establece la variable "row1" con el valor de primeraLetra como
        //int.
        row1 = Character.getNumericValue(primerLetra);
        row1 = row1 - 1;
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(0);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col1 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col1 = col1 - 65;

        //Se obtiene el número como String de la celda en que se va a
        //terminar.
        primerLetra = posiciones.charAt(4);

        //Se establece la variable "row2" con el valor de primeraLetra como
        //int.
        row2 = Character.getNumericValue(primerLetra);

        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(3);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col2 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col2 = col2 - 65;

        //Variables que van a almacenar los valores de las fracciones analizadas
        //y los resultados de sus operaciones.
        String frac;
        double valor;
        double numerador;
        double denominador;
        String valor2;

        //Variable que tiene un contador.
        int cont = 0;
        row2 = row2 - 1;

        //Dos ciclos for que van aumentando el contador.
        for (int i = row1; i <= row2; i++) {
          for (int k = col1; k <= col2; k++) {
            cont++;
          }
        }

        //Arreglos que se usarán para ordenar las fracciones analizadas.
        double [] minToMax = new double[cont];
        double [] ordenados = new double[cont];


        //Nuevo contador.
        int contador = 0;

        //HashMap que será igual a los valores String finales.
        HashMap<String,String> valores = new HashMap<String, String>();

        //Dos ciclos for que reciben las fracciones, sacan sus valores
        //decimales, los agregan al HashMap y aumentan el contador.
        for (int i = row1; i <= row2; i++) {
          for (int k = col1; k <= col2; k++) {
            frac = datos[i][k].split("/")[0];
            numerador = Double.parseDouble(frac);
            frac = datos[i][k].split("/")[1];
            denominador = Double.parseDouble(frac);
            valor = numerador / denominador;
            frac = datos[i][k];
            minToMax[contador] = valor;
            valor2 = Double.toString(valor);
            valores.put(valor2, frac);
            contador++;
          }
        }

        //Variable que almacena un número.
        double num;

        //Dos ciclos for que exploran minToMax, comparan sus valores y los
        //ordenan.
        for (int i = 0; i < minToMax.length; i++){
          num = minToMax[i];
          for (int k = i+1; k < minToMax.length; k++){
            if (num > minToMax[k]){
              num = minToMax[k];
            }
            ordenados[i] = num;
          }
        }

        //Variable que tendrá el resultado numérico de la mediana.
        double mediana;

        //Variable que tendrá la mediana en forma de fracción.
        String median;

        //Variable que tendrá el tamaño del arreglo de los elementos ordenados.
        int size = ordenados.length;

        //If que indica que operación matemática debe usarse para obtener el
        //resultado numérico de la mediana en caso de que el tamaño del arreglo
        //sea par o impar, obtiene su equivalente de "valores" y establece la
        //celda que se va a modificar con ese valor.
        if (ordenados.length % 2 == 0){
          mediana = (ordenados[(size/2)-1] + ordenados[size/2]) / 2;

        }
        else {
          mediana = ordenados[size/2];
          median = Double.toString(mediana);
          median = valores.get(median);
          datos[celdaRow][celdaCol] = median;
        }



      }

      //If que indica que lo que tiene se ejecuta si el comando es ">=MIN".
      if (comando.contains(">=MIN")) {
        //Se obtiene el número como String de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(1);

        //Se establece la variable "row1" con el valor de primeraLetra como
        //int.
        row1 = Character.getNumericValue(primerLetra);
        row1 = row1 - 1;
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(0);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col1 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col1 = col1 - 65;

        //Se obtiene el número como String de la celda en que se va a
        //terminar.
        primerLetra = posiciones.charAt(4);

        //Se establece la variable "row2" con el valor de primeraLetra como
        //int.
        row2 = Character.getNumericValue(primerLetra);

        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(3);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col2 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col2 = col2 - 65;

        //Variable que almacene el numerador.
        double numerador;

        //Variable que almacene el denominador.
        double denominador;

        //Variable que almacena el valor mínimo de lo que se está analizando.
        String minimo;

        //Variable que se usará para sacar el valor de la fracción que se
        //analiza.
        double total;

        //Contador que aumentará, después de analizar cada fracción.
        int contador = 0;

        //Se crea un arreglo de tipo String de tamaño row2 * col2 para que
        //contenga los valores obtenidos.
        double[] menores = new double[row2 * col2];

        //Se crea un arreglo de tipo String de tamaño row2 * col2 para que
        //contenga las coordenadas de los valores obtenidos.
        String[] cords = new String[row2 * col2];
        row2 = row2 - 1;
        //Dos ciclos for donde se obtienen los datos de las celdas que se
        //analizan, se establece el numerador y el denominador, se hace la
        //división para sacar el valor de cada fracción; el valor va al arreglo
        //"menores" y las coordenadas al arreglo "cords" y el contador aumenta.
        for (int i = row1; i <= row2; i++){
          for (int k = col1; k < col2; k++){
            minimo = datos[i][k].split("/")[0];
            numerador = Double.parseDouble(minimo);
            minimo = datos[i][k].split("/")[1];
            denominador = Double.parseDouble(minimo);
            total = numerador / denominador;
            menores[contador] = total;
            cords[contador] = i +"/"+ k;
            contador++;
          }
        }

        //Variable que se usará para tener el valor mínimo.
        double min = 0;

        //Variable que se usará para tener el valor del número que se esté
        //revisando en un momento específico.
        double num;

        //Variable que se usará para tener la ubicación de las coordenadas del
        //valor menor en el arreglo "cords".
        int coordenada = 1;

        //Ciclo for que recorre el arreglo "menores", compara si un valor es
        //menor que otro, cuando quede establecido el menor, se establecerá la
        //coordenada con su valor final en el arreglo "cords".
        for (int index = 0; index < menores.length; index++) {
          num = menores[index];
          if (index == 0) {
            min = num;
          }

          if (num < min) {
            min = num;
            coordenada = index;
          }
        }

        coordenada = coordenada - 1;
        //Variable que tiene la fila de la matriz donde está el dato menor.
        char fila = cords[coordenada].charAt(0);
        int fila2 = Character.getNumericValue(fila);
        //Variable que tiene la columna de la matriz donde está el dato menor.
        char columna = cords[coordenada].charAt(2);
        int columna2 = Character.getNumericValue(columna);

        //Se establece que la celda que se va a modificar tendrá el valor
        //almacenado en datos[fila][columna].
        datos[celdaRow][celdaCol] = datos[fila2][columna2];

      }

      //If que indica que lo que tiene se ejecuta si el comando es ">=MAX".
      if (comando.contains(">=MAX")) {

        //Se obtiene el número como String de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(1);

        //Se establece la variable "row1" con el valor de primeraLetra como
        //int.
        row1 = Character.getNumericValue(primerLetra);
        row1 = row1 - 1;
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(0);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col1 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col1 = col1 - 65;

        //Se obtiene el número como String de la celda en que se va a
        //terminar.
        primerLetra = posiciones.charAt(4);

        //Se establece la variable "row2" con el valor de primeraLetra como
        //int.
        row2 = Character.getNumericValue(primerLetra);
        //Se obtiene la letra de la celda en que se va a empezar.
        primerLetra = posiciones.charAt(3);

        //Se establece la columna de la celda en que se va a empezar con lo
        //que hay en "primeraLetra".
        col2 = primerLetra;

        //Se obtiene el valor numérico de la letra y se le restan 65 para que
        //quede establecido en la columna de la celda de la matriz que se
        //modifica.
        col2 = col2 - 65;

        //Variable que almacene el numerador.
        double numerador;

        //Variable que almacene el denominador.
        double denominador;

        //Variable que almacena el valor mínimo de lo que se está analizando.
        String maximo;

        //Variable que se usará para sacar el valor de la fracción que se
        //analiza.
        double total;

        //Contador que aumentará, después de analizar cada fracción.
        int contador = 0;

        //Se crea un arreglo de tipo String de tamaño row2 * col2 para que
        //contenga los valores obtenidos.
        double[] mayores = new double[row2 * col2];

        //Se crea un arreglo de tipo String de tamaño row2 * col2 para que
        //contenga las coordenadas de los valores obtenidos.
        String[] cords = new String[row2 * col2];

        row2 = row2 - 1;

        //Dos ciclos for donde se obtienen los datos de las celdas que se
        //analizan, se establece el numerador y el denominador, se hace la
        //división para sacar el valor de cada fracción; el valor va al arreglo
        //"mayores" y las coordenadas al arreglo "cords" y el contador aumenta.
        for (int i = row1; i <= row2; i++){
          for (int k = col1; k < col2; k++){
            maximo = datos[i][k].split("/")[0];
            numerador = Double.parseDouble(maximo);
            maximo = datos[i][k].split("/")[1];
            denominador = Double.parseDouble(maximo);
            total = numerador / denominador;
            mayores[contador] = total;
            cords[contador] = i +"/"+ k;
            contador++;
          }
        }

        //Variable que se usará para tener el valor máximo.
        double max = 0;

        //Variable que se usará para tener el valor del número que se esté
        //revisando en un momento específico.
        double num;

        //Variable que se usará para tener la ubicación de las coordenadas del
        //valor menor en el arreglo "cords".
        int coordenada = 0;

        //Ciclo for que recorre el arreglo "mayores", compara si un valor es
        //mayor que otro, cuando quede establecido el menor, se establecerá la
        //coordenada con su valor final en el arreglo "cords".
        for (int index = 0; index < mayores.length; index++) {
          num = mayores[index];

          if (num > max) {
            max = num;
            coordenada = index;
          }
        }
        //Variable que tiene la fila de la matriz donde está el dato mayor.
        char fila = cords[coordenada].charAt(0);
        int fila2 = Character.getNumericValue(fila);
        //Variable que tiene la columna de la matriz donde está el dato mayor.
        char columna = cords[coordenada].charAt(2);
        int columna2 = Character.getNumericValue(columna);

        //Se establece que la celda que se va a modificar tendrá el valor
        //almacenado en datos[fila][columna].
        datos[celdaRow][celdaCol] = datos[fila2][columna2];

      }


      //ESTO ES PARTE DEL EXAMEN DENTRO DE ESTA CLASE.
      if (comando.contains(">=PRODUCTOMATRICIAL")) {
        primerLetra = posiciones.charAt(1);
        row1 = Character.getNumericValue(primerLetra);
        row1 = row1 - 1;
        primerLetra = posiciones.charAt(0);
        col1 = primerLetra;
        col1 = col1 - 65;
        primerLetra = posiciones.charAt(4);
        row2 = Character.getNumericValue(primerLetra);
        row2 = row2 - 1;
        primerLetra = posiciones.charAt(3);
        col2 = primerLetra;
        col2 = col2 - 65;
        primerLetra = posiciones.charAt(7);
        row3 = Character.getNumericValue(primerLetra);
        row3 = row3 - 1;
        primerLetra = posiciones.charAt(6);
        col3 = primerLetra;
        col3 = col3 - 65;
        primerLetra = posiciones.charAt(10);
        row4 = Character.getNumericValue(primerLetra);
        row4 = row4 - 1;
        primerLetra = posiciones.charAt(9);
        col4 = primerLetra;
        col4 = col4 - 65;
        String[][] primeraMatriz = new String[row2 - row1 + 1][col2 - col1 + 1];
        String[][] segundaMatriz = new String[row4 - row3 + 1][col4 - col3 + 1];

        
      }

      //AQUÍ TERMINA LA PARTE DEL EXAMEN EN ESTA CLASE.




    }
    //Se cierra el input.
    input.close();

  }
}
