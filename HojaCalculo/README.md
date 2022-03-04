# Hoja de Calculo

Nuestro segundo proyecto para el curso de Programacion 1 consiste en desarrollar una hoja de cálculo. 

La hoja solicitada debe extender las funcionalidades de la hoja de cálculo y permitir el uso
de fracciones y operaciones con fracciones. 

Como parte de este proyecto, nuestro equipo debe presentar un avance de la extensión con las
operaciones básicas solicitadas antes de proceder con la integración de las interfaces gráficas.
El avance que debemos presentar debe de pasar todos los casos de prueba suministrados. 

## Analisis

Las operaciones solicitadas  son las siguientes:
1. Sumar y multiplicar filas y columnas desde una celda inicial hasta una celda final.
2. Obtener el valor máximo y mínimo de una fila o columna desde una celda inicial hasta
una celda final.
3. Obtener el promedio y la mediana de una fila o columna desde una celda inicial hasta
una celda final.
4. Crear subconjuntos formados por celdas de la hoja de cálculo.
5. Sumar y multiplicar subconjuntos.


El programa deberá leer los datos de prueba desde la entrada estándar como se muestran en la
siguiente tabla de ejemplo.

| 4   8 |
|----------------------------------------------------------|
|01/02, 02/02, 03/02, 04/02, 05/02, 06/02, 07/02, 00/01
01/05, 02/05, 03/05, 04/05, 05/05, 06/05, 07/05, 00/01
01/07, 02/07, 03/07, 04/07, 05/07, 06/07, 07/07, 00/01
00/01, 00/01, 00/01, 00/01, 00/01, 00/01, 00/01, 00/01
>=CEL(H1)

>=SUMA(B1:G1)

>=CEL(H2)

>=PROMEDIO(A2:G2)

>=CEL(H3)

>=MEDIANA(A3:G3)

>=CEL(B4)

>=MULT(B1:B3)

>=CEL(G4)

>=CONJUNTO(conj1,A1,B2,C3,C4)

>=SUMA(conj1)

>=IMPRIMIR()

Además de los datos que debe cargar nuestra hoja de cálculo, el programa tambien debe leer una serie
de instrucciones que indican la posición donde se debe guardar el resultado y la operación que
se debe realizar.

La salida esperada debe mostrarse en la salida estándar. Esta debe mostrar la hoja de cálculo
actualizada después de haber efectuado las instrucciones anteriores. 

|   | A | B | C | D | E | F | G | H |
|---|-----|-----|-----|-----|-----|-----|-----|-----|
| 1 | 1/2 | 1/1 | 3/2 | 2/1 | 5/2 | 3/1 | 7/2 | 27/2 |
| 2 | 1/5 | 1/1 | 3/5 | 4/5 | 1/1 | 6/5 | 7/5 | 4/5 |
| 3 | 1/7 | 1/1 | 3/7 | 4/7 | 5/7 | 6/7 | 1/1 | 4/7 |
| 4 | 0/1 | 4/35 | 0/1 | 0/1 | 0/1 | 0/1 | 0/1 | 93/70 |

La siguiente tabla muestra la lista de comandos válidos:

|      Comando         |      Descripcion   |
|----------------------|--------------------|
| =CEL(celda)  | Se posiciona en una celda |
| =CONJUNTO(nombre,celda1,…,celda_n)  | Crea un conjunto con un nombre específico y agrega lasceldas que se le indican en el argumento. |
| =SUMA(celda1:celda2)  | Suma una fila o columna desde la celda1 hasta la celda2. |
| =SUMA(nombreConjunto) | Suma los valores de un conjunto con un nombre específico.  |
| =MULT(celda1:celda2) | Multiplica una fila o columna desde la celda1 hasta la celda2. |
| =MULT(nombreConjunto) | Multiplica los valores de un conjunto con un nombre específico. |
| =PROMEDIO(celda1:celda2)  | Obtiene el promedio de una fila o columna desde la celda1 hasta la celda2. |
| =MEDIANA(celda1:celda2) | Obtiene la mediana de una fila o columna desde la celda1 hasta la celda2.|
| =MIN(celda1:celda2)  | Obtiene la fracción con el valor mínimo de una fila o columna desde la celda1 hasta la celda2. |
| =MAX(celda1:celda2) | Obtiene la fracción con el valor máximo de una fila o columna desde la celda1 hasta la celda2. |
| =IMPRIMIR() | Imprime toda la hoja de cálculo. |
| =IMPRIMIR(nombreConjunto) | Imprime un conjunto específico. Cada fracción va separada por un espacio. |

Se trabajara con 4 clases en total para cumplir con los requerimientos del avance, las cuales serian:

- **Controlador**: 
 Esta clase se encarga de controlar el programa y de la parte logico de este. 
Tambien sirve para leer el input y para hacer calculos poco complejos.

- **HojaCalculo**: 
 Esta clase se encarga de la impresion de la tabla y los conjuntos.
 
 - **Fraccion**:
  Esta clase contiene metodos que nos permitira trabajar con las fracciones de manera mas facil, con metodos dedicados a simplificar, sumar y multiplicar
  
 - **ListaDeFracciones**:
  Esta clase sirve para armar los conjuntos y realizar sus respectivas operaciones (sumas y multiplicaciones).
  
  ## Guia de usuario
#### Como abrir el programa 
  
Para ejecutar el programa utilize su IDE de preferencia para abrir el proyecto y construir el archivo "HojaCalculo-1.0.jar".

Luego abra la carpeta "libs" ubicada dentro de la carpeta del proyecto utilizando la consola (HojaCalculo\build\libs).

En windows seria: 
- Boton windows + R
- Escribir cmd.exe y luego run
- Escribir dentro de la consola "cd ...\HojaCalculo\build\libs" (La ubicacion de la carpeta libs en su computadora)

Luego escriba el comando "java -jar HojaCalculo-1.0.jar < ../../tests/input000.txt". Siendo "input000.txt" el archivo que se quiere correr, por lo que si desea
cambiar de caso de prueba simplemente reemplace "input000" por el nombre del archivo deseado .


Tambien hay que asegurarse de que el archivo de texto tenga el formato valido (puede verse en la seccion de Analisis).


Si tiene problemas al ejecutar el codigo puede ser que su JDK ocupe actualizar. 

## Creditos

Diego Valladares: diego.valladaresbermudez@ucr.ac.cr

Sebastian Jimenez Camacho: sebastian.jimenezcamacho@ucr.ac.cr

La Clase "Fraccion" fue creada por el profesor Alberto Rojas Salazar: alberto.rojassalazar@ucr.ac.cr

El conocimiento de JAVA necesario para lograr este proyecto fue obtenido del curso CI-0112 (Programacion1 UCR).


 







