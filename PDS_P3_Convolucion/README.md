# Motor de Convolución Analítica

**Instituto Politécnico Nacional - Escuela Superior de Cómputo (ESCOM)** **Materia:** Procesamiento Digital de Señales  
**Grupo:** 5CM1  
**Desarrollado por:**
* Navarro Arellano Diego Emiliano
* Rubalcaba González Salvador Angelo

---

## Descripción del Proyecto
Una aplicación de escritorio interactiva desarrollada en **Java (Swing)** que se integra con el motor matemático de **MATLAB** para visualizar y animar la convolución geométrica de señales en tiempo continuo. 

Este proyecto está diseñado para facilitar el análisis de sistemas y el procesamiento de señales, abstrayendo la sintaxis compleja de MATLAB en una interfaz intuitiva, limpia y de estilo analítico.

## Características Principales

* **Interfaz Desacoplada:** Frontend ligero en Java que ejecuta procesos de MATLAB en segundo plano (`-batch`) sin abrir la consola.
* **Procesamiento de Intervalos:** Abstracción de operadores lógicos. Los usuarios pueden delimitar señales simplemente activando un *Checkbox* y definiendo el intervalo $[a, b]$, en lugar de programar vectores lógicos a mano.
* **Animación Geométrica:** Visualización paso a paso del desplazamiento $g(t-\tau)$, el área de intersección superpuesta y la señal resultante $(f * g)(t)$.
* **Gestión de Estados Asíncrona:** Implementación de `SwingWorker` para manejar la carga de procesos sin congelar la interfaz gráfica (UI).

## Tecnologías Utilizadas

* **Java**: Lógica de interfaz, manejo de eventos y ejecución de subprocesos mediante `Runtime.exec()`.
* **MATLAB (R2025b)**: Renderizado de figuras, cálculo de convolución mediante `conv()` y animación gráfica interactiva.
