# Resumen de la Actividad: Lanzando Procesos desde Java

## Introducción
Esta actividad es crear un programa en Java que hace de lanzador de comandos del sistema de Windows. El programa usa la clase `ProcessBuilder` para ejecutar procesos externos, capturar sus salidas y gestionar errores. Es un proyecto creado en java.

## Descripción de Cada Paso

### 1. Desarrollar un Programa Lanzador
Se creó la clase `LanzadorProcesos` con un método `ejecutarDir(String carpeta)` que ejecuta el comando `dir` en una carpeta. La salida se redirige a un archivo llamado `salida_ls.txt`.

- **Código:**
  ```java
  public void ejecutarDir(String carpeta) {
      try {
          ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "dir", carpeta);
          processBuilder.redirectOutput(new File("datos/salida_ls.txt"));
          processBuilder.redirectErrorStream(true); // Redirigir errores a salida estándar

          Process proceso = processBuilder.start();
          int codigoSalida = proceso.waitFor();
          System.out.println("Tarea 1 - ejecutar dir. Código de salida: " + codigoSalida);
      } catch (IOException | InterruptedException e) {
          e.printStackTrace();
      }
  }
  ```

- **Explicación de redirecciones:** Se usa `processBuilder.redirectOutput(new File("salida_ls.txt"))` para enviar la salida al archivo. `processBuilder.redirectErrorStream(true)` combina la salida de error con la salida estándar, haciendo que todo se guarde en el archivo.

- **Captura de salida:** El archivo `salida_ls.txt` tiene la lista de archivos en el directorio actual, como la salida del comando `dir`. como por ejemplo:
  ```
  El volumen de la unidad C no tiene etiqueta.
  El número de serie del volumen es: F447-FF4F

  Directorio de C:\Users\adris\Desktop\multiprocesos

  15/10/2025  18:01    <DIR>          .
  15/10/2025  17:59    <DIR>          ..
  ...
  ```

### 2. Ejecutar un Proceso con Parámetros
El método `ejecutarPingGoogle()` lanza el comando `ping -n 6 google.com` y muestra la salida por consola.

- **Código relevante:**
  ```java
  public void ejecutarPingGoogle() {
      try {
          ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "ping", "-n", "6", "google.com");
          processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
          processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

          Process proceso = processBuilder.start();
          int codigoSalida = proceso.waitFor();
          System.out.println("Tarea 2 - ejecutar ping. Código de salida: " + codigoSalida);
      } catch (IOException | InterruptedException e) {
          e.printStackTrace();
      }
  }
  ```

- **Explicación de redirecciones:** `ProcessBuilder.Redirect.INHERIT` hace que la salida del proceso se muestre en la consola del programa padre que en este caso pues es Java, permitiendo ver los resultados en tiempo real.

- **Captura de salida:** Durante la ejecución, se enseña en consola las respuestas de ping a google.com.

### 3. Automatizar la Ejecución de Varios Procesos
El método `automatizarSecuencia()` ejecuta tres comandos en orden: `echo "Inicio de la tarea"`, `timeout /t 3 > null`, y `echo %date% %time%`.

- **Código:**
  ```java
    public void automatizarSecuencia() {
        try {
            System.out.println("Tarea 3 - ejecutar secuencia.");

            // Comando 1: echo "Inicio de la tarea"
            ProcessBuilder processBuilder1 = new ProcessBuilder("cmd", "/c", "echo Inicio de la tarea");
            processBuilder1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso1 = processBuilder1.start();
            proceso1.waitFor();

            // Comando 2: timeout /t 3 > nul
            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd", "/c", "timeout /t 3 > null");
            processBuilder2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso2 = processBuilder2.start();
            proceso2.waitFor();

            // Comando 3: echo %date% %time%
            ProcessBuilder processBuilder3 = new ProcessBuilder("cmd", "/c", "echo %date% %time%");
            processBuilder3.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process proceso3 = processBuilder3.start();
            proceso3.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
  ```

- **Explicación de redirecciones:** Todos usan `INHERIT` para mostrar en consola al heredar de arriba heredan del programa y el programa lo muestra por consola. El `timeout` espera 3 segundos sin mostrar salida.

- **Captura de salida:** Se muestra en consola algo asi:
  ```
  "Inicio de la tarea"
  15/10/2025 17:21:35,52
  ```

### 4. Ejecutar Procesos en Paralelo
El método `ejecutarPingsParalelos()` lanza dos procesos `ping` en paralelo: uno a `localhost` y otro a `127.0.0.1`, guarda las salidas en archivos separados.

- **Código relevante:**
  ```java
  public void ejecutarPingsParalelos() {
      try {
          ProcessBuilder processBuilder1 = new ProcessBuilder("cmd", "/c", "ping", "localhost");
          processBuilder1.redirectOutput(new File("datos/ping_localhost.txt"));
          processBuilder1.redirectErrorStream(true);

          ProcessBuilder processBuilder2 = new ProcessBuilder("cmd", "/c", "ping", "127.0.0.1");
          processBuilder2.redirectOutput(new File("datos/ping_127.txt"));
          processBuilder2.redirectErrorStream(true);

          Process proceso1 = processBuilder1.start();
          Process proceso2 = processBuilder2.start();

          int salida1 = proceso1.waitFor();
          int salida2 = proceso2.waitFor();

          System.out.println("Tarea 4 - ejecucion de pings paralelos. Salidas: " + salida1 + ", " + salida2);
      } catch (IOException | InterruptedException e) {
          e.printStackTrace();
      }
  }
  ```

- **Explicación de redirecciones:** Cada proceso redirige su salida a un archivo diferente, y los errores se combinan.

- **Captura de salida:** Archivos `ping_localhost.txt` y `ping_127.txt` contienen los datos del ping. Por ejemplo: 
  ```
  Haciendo ping a Asiman-PC [::1] con 32 bytes de datos:
  Respuesta desde ::1: tiempo<1m
  ...
  Estadísticas de ping para ::1:
      Paquetes: enviados = 4, recibidos = 4, perdidos = 0
  ```

### 5. Control de Errores
El método `forzarError()` ejecuta un comando inválido para mostrar la gestión de errores que se pide de forma forzada.

- **Código relevante:**
  ```java
  public void forzarError() {
      try {
          ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "comando_inexistente");
          processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
          processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

          Process proceso = processBuilder.start();
          int codigoSalida = proceso.waitFor();
          System.out.println("Tarea 5: Codigo invalido en base a comando inexistente:  " + codigoSalida);
      } catch (IOException | InterruptedException e) {
          System.out.println("Error para control de errores: " + e.getMessage());
      }
  }
  ```

- **Explicación de gestión de errores:** Si el comando falla, el código de salida no es un crasheo. Se captura con `waitFor()` y se muestra las excepciones se manejan con try-catch.

- **Captura de salida:** Se muestra en consola el error del comando inexistente y el código de salida del error.

## Conclusión
El programa muestra como tal el uso de `ProcessBuilder` para gestionar procesos externos, redirigir salidas y manejar errores. En este caso se ejecuta en windows pero se puede transladar a otros sistemas reemplazando los argumentos de comandos este tipo programas nos van a permitir generar y gestionar procesos dentro de aplicaciones internonectadas y un control general de procesos y comandos internos tanto de las aplicaciones como del sistema.
