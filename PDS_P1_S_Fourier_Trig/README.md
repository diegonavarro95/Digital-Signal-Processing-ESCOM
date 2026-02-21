# Calculadora Analítica de Series Trigonométricas de Fourier

**Instituto Politécnico Nacional - Escuela Superior de Cómputo (ESCOM)** **Materia:** Procesamiento Digital de Señales  
**Grupo:** 5CM1  
**Desarrollado por:** 
Navarro Arellano Diego Emiliano 
Rubalcaba González Salvador Angelo 

---

## Descripción del Proyecto
Aplicación de escritorio desarrollada en Java que calcula, renderiza y grafica la Serie Trigonométrica de Fourier de una función dada. 

El sistema utiliza una arquitectura híbrida:
* **Frontend (Java/Swing):** Interfaz gráfica de usuario interactiva y renderizado de ecuaciones matemáticas en formato LaTeX nativo.
* **Backend (MATLAB):** Motor de cálculo simbólico para la resolución exacta de integrales definidas y graficación superpuesta de la señal original vs. la aproximación por armónicos.

## Objetivo
Proporcionar una herramienta computacional robusta que permita analizar señales continuas periódicas. A diferencia de las calculadoras numéricas tradicionales, este software realiza integración simbólica pura, manteniendo constantes como $\pi$ o el número de Euler $e$ intactos para ofrecer resultados analíticos formales, evaluados en cualquier intervalo arbitrario $[a, b]$.

---

## Requisitos Previos (Prerrequisitos)
Para compilar y ejecutar este proyecto, el entorno host debe contar con:
1. **Java Development Kit (JDK):** Versión 11 o superior.(Usamos la 25)
2. **MATLAB:** Versión instalada localmente (probado en R2023a - R2025b). Es **obligatorio** tener instalado el *Symbolic Math Toolbox*.
3. **IDE de Desarrollo:** Apache NetBeans (recomendado para la vista Matisse de diseño UI).

---

## Configuración del Entorno y Dependencias

Para que Java y MATLAB se comuniquen correctamente, es estrictamente necesario configurar las dependencias y las variables de entorno del sistema operativo (Windows).

### 1. Archivos JAR Necesarios (Librerías)
Se deben agregar las siguientes librerías al *Build Path* (Libraries) del proyecto en NetBeans:
* `engine.jar`: La API oficial de MATLAB para Java. Se encuentra en tu instalación local de MATLAB, típicamente en la ruta:  
  `C:\Program Files\MATLAB\[Version]\extern\engines\java\jar\engine.jar`
* `jlatexmath-1.0.7.jar`: Librería externa para renderizar código LaTeX como imágenes en la UI de Java. (Descargar de la web y agregar a la carpeta `lib` del proyecto).Ya es incluida en este repositorio.


### 2. Variables de Entorno de Windows (CRÍTICO)
Para evitar el error `java.lang.UnsatisfiedLinkError`, el sistema debe saber dónde encontrar las librerías dinámicas (`.dll`) de MATLAB.
1. Abrir las **Variables de Entorno** de Windows.
2. En las Variables del Sistema, editar la variable **`Path`**.
3. Agregar la siguiente ruta (ajustando la versión de MATLAB):
   `C:\Program Files\MATLAB\R2025b\bin\win64`
4. **Reinicia NetBeans** por completo para que detecte los cambios en el sistema operativo.

### 3. Opciones de la Máquina Virtual (VM Options) en Java
Si se utilizan versiones recientes de Java (JDK 17 o superior), las restricciones de seguridad bloquearán la ejecución de código nativo de MATLAB. 
En NetBeans, ir a *Propiedades del Proyecto -> Run -> VM Options* y pega exactamente esto:
```text
-Djava.library.path="C:\Program Files\MATLAB\R2025b\bin\win64" --enable-native-access=ALL-UNNAMED