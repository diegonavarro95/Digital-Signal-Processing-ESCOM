# Casos de Estudio y Ejemplos de Convolución

Esta guía contiene 10 ejemplos clásicos de convolución de señales continuas. Para cada caso, se muestra la definición matemática, cómo ingresarlo utilizando la interfaz simplificada (con límites) y cómo sería la equivalencia manual usando operadores lógicos de MATLAB.

La operación de convolución se define como:
$$y(t) = (f * g)(t) = \int_{-\infty}^{\infty} f(\tau)g(t-\tau)d\tau$$

---

### 1. Dos Pulsos Rectangulares Idénticos
La convolución de dos pulsos rectangulares del mismo ancho genera un triángulo perfecto.
* **Función:** $f(t) = 1$ para $0 \le t \le 2$ | $g(t) = 1$ para $0 \le t \le 2$
* **Entrada con Checkbox (Recomendada):**
  * `f(t)`: `1` | Rango: `[0, 2]`
  * `g(t)`: `1` | Rango: `[0, 2]`
* **Entrada Manual:**
  * `f(t)`: `(t>=0 & t<=2).*(1)`
  * `g(t)`: `(t>=0 & t<=2).*(1)`
* **Salida Esperada:** Un pulso triangular con base de $0$ a $4$ y amplitud máxima de $2$ en $t=2$.

### 2. Dos Pulsos Rectangulares Diferentes
La convolución de pulsos de diferente duración genera una forma trapezoidal.
* **Función:** $f(t) = 1$ para $0 \le t \le 4$ | $g(t) = 1$ para $0 \le t \le 2$
* **Entrada con Checkbox (Recomendada):**
  * `f(t)`: `1` | Rango: `[0, 4]`
  * `g(t)`: `1` | Rango: `[0, 2]`
* **Entrada Manual:**
  * `f(t)`: `(t>=0 & t<=4).*(1)`
  * `g(t)`: `(t>=0 & t<=2).*(1)`
* **Salida Esperada:** Un trapecio que sube de $0$ a $2$, se mantiene plano de $t=2$ a $t=4$, y baja hasta $t=6$.

### 3. Escalón Unitario por Escalón Unitario
La integral de un escalón produce una rampa que crece hacia el infinito.
* **Función:** $f(t) = u(t)$ | $g(t) = u(t)$
* **Entrada con Checkbox:** *No marcar casillas (infinito).*
  * `f(t)`: `heaviside(t)`
  * `g(t)`: `heaviside(t)`
* **Salida Esperada:** Una rampa que inicia en el origen ($t=0$) y crece linealmente con pendiente $1$.

### 4. Exponencial Decreciente por Escalón Unitario
Modela la respuesta al escalón de un circuito RC (Carga de un capacitor).
* **Función:** $f(t) = e^{-t} u(t)$ | $g(t) = u(t)$
* **Entrada con Checkbox:** *No marcar casillas.*
  * `f(t)`: `exp(-t).*heaviside(t)`
  * `g(t)`: `heaviside(t)`
* **Salida Esperada:** Una curva que crece rápidamente desde $0$ y se estabiliza asintóticamente en $1$ (forma $1 - e^{-t}$).

### 5. Exponencial por Exponencial (Misma constante)
* **Función:** $f(t) = e^{-2t} u(t)$ | $g(t) = e^{-2t} u(t)$
* **Entrada con Checkbox:** *No marcar casillas.*
  * `f(t)`: `exp(-2.*t).*heaviside(t)`
  * `g(t)`: `exp(-2.*t).*heaviside(t)`
* **Salida Esperada:** Una función de tipo $t e^{-2t}$ que sube formando un pico suave y luego decae hacia cero.

### 6. Seno Truncado por Pulso Rectangular
* **Función:** $f(t) = \sin(\pi t)$ para $0 \le t \le 1$ | $g(t) = 2$ para $0 \le t \le 0.5$
* **Entrada con Checkbox:**
  * `f(t)`: `sin(pi.*t)` | Rango: `[0, 1]`
  * `g(t)`: `2` | Rango: `[0, 0.5]`
* **Entrada Manual:**
  * `f(t)`: `(t>=0 & t<=1).*(sin(pi.*t))`
  * `g(t)`: `(t>=0 & t<=0.5).*(2)`
* **Salida Esperada:** Una curva que se asemeja a una campana asimétrica, muy útil para ver cómo un pulso "promedia" a una señal variante.

### 7. Rampa Truncada por Pulso Rectangular
* **Función:** $f(t) = t$ para $0 \le t \le 2$ | $g(t) = 1$ para $0 \le t \le 1$
* **Entrada con Checkbox:**
  * `f(t)`: `t` | Rango: `[0, 2]`
  * `g(t)`: `1` | Rango: `[0, 1]`
* **Entrada Manual:**
  * `f(t)`: `(t>=0 & t<=2).*(t)`
  * `g(t)`: `(t>=0 & t<=1).*(1)`
* **Salida Esperada:** Una señal compuesta por parábolas en los extremos y una línea recta en la parte central.

### 8. Señal Bipolar Estructurada
Demostración de funciones por tramos complejas hechas manualmente.
* **Función:** $f(t) = 5$ si $0 \le t < 10$, $-5$ si $10 \le t \le 20$
* **Entrada (Solo manual debido a múltiples intervalos lógicos):**
  * `f(t)`: `(t>=0 & t<10).*(5) + (t>=10 & t<=20).*(-5)`
  * `g(t)`: `(t>=0 & t<=10).*(1)`
* **Salida Esperada:** La convolución muestra una rampa de subida, una pendiente de bajada abrupta cruzando por el cero, y un retorno triangular a cero.

### 9. Dos Pulsos Desfasados
* **Función:** $f(t) = 1$ para $2 \le t \le 4$ | $g(t) = 1$ para $1 \le t \le 3$
* **Entrada con Checkbox:**
  * `f(t)`: `1` | Rango: `[2, 4]`
  * `g(t)`: `1` | Rango: `[1, 3]`
* **Entrada Manual:**
  * `f(t)`: `(t>=2 & t<=4).*(1)`
  * `g(t)`: `(t>=1 & t<=3).*(1)`
* **Salida Esperada:** Un triángulo idéntico al del Ejemplo 1, pero desplazado en el tiempo iniciando en $t=3$ y terminando en $t=7$.

### 10. Función Parábola por Pulso
* **Función:** $f(t) = t^2$ para $0 \le t \le 2$ | $g(t) = 1$ para $0 \le t \le 2$
* **Entrada con Checkbox:**
  * `f(t)`: `t.^2` | Rango: `[0, 2]`
  * `g(t)`: `1` | Rango: `[0, 2]`
* **Entrada Manual:**
  * `f(t)`: `(t>=0 & t<=2).*(t.^2)`
  * `g(t)`: `(t>=0 & t<=2).*(1)`
* **Salida Esperada:** Curvas cúbicas ($t^3$) que crecen y decrecen formando un pulso abombado.