package com.gradle.calculo;

import java.math.BigDecimal;
import java.awt.*;

/**
 * Clase de la que se crean objetos asociados a fracciones y se resuelven
 * operaciones relacionadas.
 */
public class Fraccion {

  /**
   * Numerador de la clase.
   */
  private BigDecimal numerador;

  /**
   * Denominador de la clase.
   */
  private BigDecimal denominador;

  /**
   * Constructor de Fracción por defecto.
   */
  public Fraccion() {

    //Numerador del constructor.
    this.numerador = new BigDecimal("0");

    //Denominador del constructor.
    this.denominador = new BigDecimal("1");
  }

  /**
   * Constructor con un número entero.
   *
   * @param pNumerador Recibe una variable de tipo {@code String} que
   *                   corresponde al numerador, que es el único número que
   *                   recibe.
   */
  public Fraccion(final String pNumerador) {
    this.numerador = new BigDecimal(pNumerador);
    this.denominador = new BigDecimal("1");
  }

  /**
   * Constructor de una fracción negativa usando {@code String}.
   *
   * @param pNumerador Recibe una variable de tipo {@code String} que indica el
   *                   numerador de la fracción.
   * @param pDenominador Recibe una variable de tipo {@code String} que indica
   *                     el denominador de la fracción.
   */
  public Fraccion(final String pNumerador, final String pDenominador) {

    //If que indica que si el denominador tiene símbolo negativo, su numerador
    //se multiplica por -1 y su denominador se convierte a su valor absoluto; si
    //eso no pasa, ambos quedan iguales.
    if (pDenominador.charAt(0) == '-') {
      this.numerador = new BigDecimal(pNumerador).multiply(new BigDecimal(-1));
      this.denominador = new BigDecimal(pDenominador).abs();
    } else {
      this.numerador = new BigDecimal(pNumerador);
      this.denominador = new BigDecimal(pDenominador);
    }

    //Invocación al método para simplificar la fracción.
    simplificar();
  }

  /**
   * Constructor de una fracción negativa usando {@code BigDecimal}.
   *
   * @param pNumerador Recibe una variable de tipo {@code BigDecimal} que
   *                   indica el numerador de la fracción.
   * @param pDenominador Recibe una variable de tipo {@code BigDecimal} que
   *                     indica el denominador de la fracción.
   */
  public Fraccion(final BigDecimal pNumerador, final BigDecimal pDenominador) {

    //If que indica que si el denominador tiene símbolo negativo, su numerador
    //se multiplica por -1 y su denominador se convierte a su valor absoluto; si
    //eso no pasa, ambos quedan iguales.
    if (pDenominador.compareTo(BigDecimal.ZERO) < 0) {
      this.numerador = pNumerador.multiply(new BigDecimal(-1));
      this.denominador = pDenominador.abs();
    } else {
      this.numerador = new BigDecimal(pNumerador.toString());
      this.denominador = new BigDecimal(pDenominador.toString());
    }

    //Invocación al método para simplificar la fracción.
    simplificar();
  }

  /**
   * Método getter que obtiene el valor de la variable numerador de la clase.
   */
  public BigDecimal getNumerador() {
    return this.numerador;
  }

  /**
   * Método getter que obtiene el valor de la variable denominador de la clase.
   */
  public BigDecimal getDenominador() {
    return this.denominador;
  }

  /**
   * Representa la fracción como una cadena de caracteres.
   *
   * @return Retorna un String con la representación de la fracción.
   */
  public String toString() {
    return  numerador.toString() + "/" + denominador.toString();
  }

  private BigDecimal maximoComunDivisor(final BigDecimal pNumerador,
                                        final BigDecimal pDenominador) {
    BigDecimal a = new BigDecimal(pNumerador.toString());
    BigDecimal b = new BigDecimal(pDenominador.toString());
    while (!b.equals(BigDecimal.ZERO)) {
      BigDecimal t = new BigDecimal(b.toString());
      b = a.remainder(b);
      a = new BigDecimal(t.toString());
    }
    return a;
  }

  private void simplificar() {
    BigDecimal mcd = maximoComunDivisor(this.numerador.abs(),
        this.denominador.abs());
    this.numerador = this.numerador.divide(mcd);
    this.denominador = this.denominador.divide(mcd);
  }

  /**
   * Método que suma fracciones.
   *
   * @param fraction Recibe un objeto de tipo {@code Fraccion} que contiene la
   *                 fracción que se está sumando.
   * @return Retorna el resultado de la suma.
   */
  public Fraccion sumar(final Fraccion fraction) {

    //Se convierten las fracciones a homogéneas las fracciones y se suman; el
    //resultado se retorna.
    BigDecimal num1 = this.numerador.multiply(fraction.getDenominador());
    BigDecimal num2 = fraction.numerador.multiply(this.denominador);
    BigDecimal nuevoNumerador = num1.add(num2);
    BigDecimal nuevoDenominador = this.denominador.multiply(
        fraction.getDenominador());
    return new Fraccion(nuevoNumerador, nuevoDenominador);
  }


  /**
   * Método que multiplica fracciones.
   *
   * @param fraction Recibe un objeto de tipo {@code Fraccion} que contiene la
   *                 fracción que se está multiplicando.
   * @return Retorna el resultado de la multiplicación.
   */
  public Fraccion multiplicar(final Fraccion fraction) {

    //Se multiplican los numeradores.
    BigDecimal nuevoNumerador = this.numerador.multiply(
        fraction.getNumerador());

    //Se multiplican los denominadores.
    BigDecimal nuevoDenominador = this.denominador.multiply(
        fraction.getDenominador());

    //Se retorna el resultado.
    return new Fraccion(nuevoNumerador, nuevoDenominador);
  }




}
