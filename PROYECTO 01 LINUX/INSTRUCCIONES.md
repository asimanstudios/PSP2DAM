---
tags:
  - psp
  - dam
  - actividad
  - prueba
  - dam
  - linux
---

## Actividad Semana 1: _"¿Quién está trabajando aquí?"_

### 🎯 Objetivo

Explorar y monitorizar los procesos activos en un sistema Linux utilizando herramientas de terminal. Aprender a identificar, observar y gestionar procesos, y registrar toda la sesión de trabajo para su posterior evaluación.

---

### 📝 Enunciado

Has recibido una petición para comprobar qué está ocurriendo en un servidor Linux que parece ir lento. Como técnico de sistemas, tu misión es investigar qué procesos están activos, cómo se comportan y cómo se gestionan. Realizarás esta práctica en una terminal Linux y **grabarás toda tu sesión** para que pueda ser revisada por tu profesor.

---

### 🧩 Tareas a realizar

1. **Listar procesos activos**
    - Ejecuta `ps aux` y guarda la salida en un archivo `procesos.txt`
    - Explica en pocas líneas qué significan las columnas más importantes
2. **Supervisar el sistema en tiempo real**
    - Ejecuta `top` durante unos segundos
    - Identifica el proceso con mayor uso de CPU y anota su PID y nombre
3. **Gestionar un proceso**
    - Lanza `ping google.com` y detenlo con `Ctrl+C`
    - Repite con `ping google.com > /dev/null &`, usa `jobs`, `fg`, `bg`, y `kill` para controlarlo
4. **Explorar el árbol de procesos**
    - Ejecuta `pstree` y analiza jerarquías de procesos padre e hijo
5. **Visualizar logs del sistema**
    - Muestra las últimas 20 líneas de `/var/log/syslog` o `/var/log/messages`

---

### 🎥 Registro de la sesión con `asciinema`

Antes de comenzar la práctica, **debes ejecutar el siguiente script para grabar tu actividad**:

#### 🔧 Script `iniciar_registro.sh`

```bash
#!/bin/bash

echo "Introduce tu nombre o identificador (sin espacios):"
read alumno
fecha=$(date +%Y-%m-%d_%H-%M)
archivo="registro_${alumno}_${fecha}.cast"

echo "Grabando tu sesión en: $archivo"
echo "Cuando termines, escribe 'exit' o pulsa Ctrl+D para finalizar."

asciinema rec "$archivo"
```

#### 🧰 Instrucciones de uso

1. Asegúrate de tener `asciinema` instalado:

    ```bash
    sudo apt update
    sudo apt install asciinema
    ```

2. Dale permisos de ejecución al script:

    ```bash
    chmod +x iniciar_registro.sh
    ```

3. Ejecuta el script:

    ```bash
    ./iniciar_registro.sh
    ```

4. Realiza toda la práctica con normalidad.  
    La sesión se grabará en el archivo `registro_tunombre_fecha.cast`.

5. Cuando termines, escribe:

    ```bash
    exit
    ```


---

### 📎 Entregables

- Archivo `.cast` generado con `asciinema`
- Documento PDF o Markdown con:
    - Captura y explicación de `ps`, `top`, `pstree`
    - Archivos generados como `procesos.txt`
    - Respuestas a las preguntas planteadas

---

### 📘 Evaluación

Se valorará:

- Uso correcto de los comandos
- Claridad y calidad de las explicaciones
- Archivo de grabación funcional y completo
- Orden y presentación del documento

---

### Rúbrica de evaluación 

|Criterio de evaluación|Excelente (10)|Adecuado (7)|Mejorable (5)|Insuficiente (0–4)|Peso|
|---|---|---|---|---|---|
|**Uso de comandos Linux**|Usa todos los comandos requeridos correctamente.|Usa la mayoría de los comandos de forma adecuada.|Usa algunos comandos correctamente, pero comete errores.|Usa los comandos de forma incorrecta o no los utiliza.|25%|
|**Registro de actividad con `asciinema`**|Graba toda la sesión correctamente y entrega el `.cast`.|Graba la sesión pero falta parte del contenido o nombre.|El archivo está incompleto o tiene errores de grabación.|No entrega el archivo `.cast`.|20%|
|**Análisis e interpretación de procesos**|Explica con claridad el uso de `ps`, `top`, `pstree`, etc.|Explica los comandos con algunos errores menores.|Explica superficialmente o de forma confusa.|No aporta explicaciones o son incorrectas.|20%|
|**Gestión de procesos y redirecciones**|Controla foreground/background, redirecciones y `kill`.|Realiza las tareas con pequeños fallos corregibles.|Realiza parcialmente las tareas propuestas.|No logra realizar las tareas de gestión de procesos.|15%|
|**Presentación del informe (documento)**|Documento completo, ordenado y bien redactado.|Documento entregado con estructura clara y pocos errores.|Documento incompleto o poco claro.|No entrega el documento o está muy desordenado.|10%|
|**Entrega y formato de archivos solicitados**|Entrega todos los archivos solicitados correctamente.|Entrega casi todos, con nombres adecuados.|Faltan varios archivos o están mal nombrados.|No se entregan los archivos requeridos.|10%|

### 🧮 Nota final

> Nota = (Uso comandos × 0.25) + (Asciinema × 0.20) + (Análisis × 0.20) + (Gestión procesos × 0.15) + (Informe × 0.10) + (Entrega archivos × 0.10)
