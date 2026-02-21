# Calculadora de Series de Fourier - Casos de Prueba 

 Casos de prueba para validar el motor simbólico (MATLAB) y el renderizado gráfico (Java/JLaTeXMath) de la aplicación desarrollada para la materia de Procesamiento Digital de Señales (ESCOM - IPN) Grupo: 5CM1.

Para cada prueba, ingrese los valores en la interfaz gráfica y verifique que el resultado analítico coincida con los valores esperados.

---

## Prueba 1: Pulso Exponencial Amortiguado
**Objetivo:** Validar el cálculo con funciones trascendentes y límites numéricos simétricos.

* **Función f(t):** `exp(-t)`
* **Límite inferior (a):** `-1`
* **Límite superior (b):** `1`
* **Armónicos (N):** `5`

**Resultados Analíticos Esperados:**
Al integrar esta función, los coeficientes quedan en términos del número de Euler ($e$) y de $\pi$. El programa debería arrojar algo equivalente a:
* $a_0 = e - \frac{1}{e}$  *(o su equivalente en seno hiperbólico)*
* $a_n = \frac{(-1)^n (e - 1/e)}{1 + n^2\pi^2}$
* $b_n = \frac{(-1)^{n+1} n\pi (e - 1/e)}{1 + n^2\pi^2}$

*Nota: * La gráfica debe mostrar caídas exponenciales repetidas cada 2 segundos.

---

## Prueba 2: Parábola Desplazada (Límites asimétricos)
**Objetivo:** Validar que el algoritmo calcule correctamente la frecuencia fundamental $\omega_0$ cuando el intervalo de integración comienza en $0$ en lugar de un valor negativo.

* **Función f(t):** `t^2 - 2*t`
* **Límite inferior (a):** `0`
* **Límite superior (b):** `2`
* **Armónicos (N):** `10`

**Resultados Analíticos Esperados:**
Dado que la función es simétrica respecto a $t=1$ en ese intervalo, es una función par en su periodo, por lo que los coeficientes $b_n$ deben anularse por completo.
* $a_0 = -\frac{4}{3}$
* $a_n = \frac{4}{n^2\pi^2}$
* $b_n = 0$

*Nota: * La gráfica debe mostrar una serie de "u" invertidas conectadas. Al subir a $N=10$, la aproximación azul debe superponerse casi perfectamente a la roja.

---

## Prueba 3: Coseno Rectificado de Onda Completa
**Objetivo:** Validar el comportamiento del motor de integración ante funciones con valor absoluto y límites fraccionarios de $\pi$.



* **Función f(t):** `abs(cos(t))`
* **Límite inferior (a):** `-pi/2`
* **Límite superior (b):** `pi/2`
* **Armónicos (N):** `6`

**Resultados Analíticos Esperados:**
Esta es una señal muy común en electrónica y comunicaciones. Al ser una función puramente par, no tiene términos senoidales.
* $a_0 = \frac{4}{\pi}$
* $a_n = \frac{4(-1)^{n+1}}{\pi(4n^2 - 1)}$
* $b_n = 0$

*Nota: * El renderizado en JLaTeXMath pondrá a prueba el acomodo de los exponentes y los paréntesis en el denominador.