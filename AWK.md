# Comando awk.
- Lenguaje propio
```awk
ps aux | grep ping | awk '{print $1 $2 $7 $11}'
```
> Analisis de datos desde la terminal.

## Ejemplos prácticos con AWK

### 1. Filtrado básico de líneas
```bash
# Mostrar solo líneas que contengan "error"
cat /var/log/syslog | awk '/error/ {print}'

# Mostrar líneas que NO contengan "INFO"
cat archivo.log | awk '!/INFO/ {print}'
```

### 2. Manipulación de campos
```bash
# Extraer el primer y tercer campo separados por ":"
cat /etc/passwd | awk -F: '{print $1, $3}'

# Cambiar delimitador a "," y mostrar campos específicos
echo "Juan,Perez,25,Madrid" | awk -F, '{print "Nombre: " $1, "Edad: " $3}'
```

### 3. Cálculos numéricos
```bash
# Suma de la segunda columna
cat datos.txt | awk '{sum += $2} END {print "Total:", sum}'

# Calcular promedio
cat notas.txt | awk '{sum += $2; count++} END {print "Promedio:", sum/count}'

# Encontrar máximo y mínimo
cat temperaturas.txt | awk 'NR==1 {max=$1; min=$1} $1>max {max=$1} $1<min {min=$1} END {print "Max:", max, "Min:", min}'
```

### 4. Formateo de salida
```bash
# Crear tabla formateada
cat empleados.txt | awk '{printf "%-10s %-15s %8s\n", $1, $2, $3}'

# Generar reporte con encabezados
cat ventas.txt | awk 'BEGIN {print "=== REPORTE DE VENTAS ==="} {print "Producto:", $1, "Ventas:", $2} END {print "Total de productos:", NR}'
```

### 5. Procesamiento de archivos CSV
```bash
# Procesar archivo CSV con encabezados
cat datos.csv | awk 'BEGIN {FS=","} NR==1 {print "Encabezado:", $0} NR>1 {sum[$1] += $3} END {for (i in sum) print i, sum[i]}'

# Filtrar por condición en CSV
cat usuarios.csv | awk -F, '$3 > 25 && $4 == "Activo" {print $1, $2}'
```

### 6. Análisis de logs del sistema
```bash
# Análisis de logs de Apache
cat access.log | awk '{print $1}' | sort | uniq -c | sort -nr | head -10

# Extraer IPs y códigos de respuesta
cat access.log | awk '{print $1, $9}' | awk '$2 >= 400 {print "Error:", $1, "Código:", $2}'

# Estadísticas por hora
cat access.log | awk '{print $4}' | cut -d: -f2 | sort | uniq -c
```

### 7. Procesamiento de texto
```bash
# Contar palabras en un archivo
cat texto.txt | awk '{for(i=1;i<=NF;i++) words[$i]++} END {for(w in words) print w, words[w]}'

# Extraer emails de un archivo
cat archivo.txt | awk '/[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}/ {print $0}'

# Convertir a mayúsculas la primera letra de cada palabra
echo "hola mundo" | awk '{for(i=1;i<=NF;i++) $i=toupper(substr($i,1,1)) substr($i,2)}1'
```

### 8. Uso con variables y arrays
```bash
# Usar variables para filtros dinámicos
awk -v limite=100 '$2 > limite {print $1, $2}' datos.txt

# Arrays asociativos para conteo
cat colores.txt | awk '{count[$1]++} END {for(color in count) print color, count[color]}'

# Arrays numéricos
cat numeros.txt | awk '{arr[NR]=$1} END {for(i=1;i<=NR;i++) print "Posición", i, ":", arr[i]}'
```

### 9. Funciones integradas
```bash
# Usar funciones matemáticas
cat valores.txt | awk '{print "Raíz cuadrada:", sqrt($1), "Logaritmo:", log($1)}'

# Funciones de string
cat nombres.txt | awk '{print "Longitud:", length($1), "Mayúsculas:", toupper($1)}'

# Función split para dividir strings
echo "uno,dos,tres" | awk '{split($0,arr,","); for(i in arr) print arr[i]}'
```

### 10. Scripts AWK más complejos
```bash
# Script para validar correos electrónicos
awk '
BEGIN {
    print "=== VALIDADOR DE EMAILS ==="
}
/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/ {
    print "VÁLIDO:", $0
    validos++
    next
}
{
    print "INVÁLIDO:", $0
    invalidos++
}
END {
    print "Total válidos:", validos
    print "Total inválidos:", invalidos
}' emails.txt
```

### 11. Uso con otros comandos
```bash
# Procesar salida de ps
ps aux | awk 'NR>1 {print $1, $2, $11}' | sort -k3

# Análisis de uso de disco
df -h | awk 'NR>1 {print $1, $5}' | awk -F% '$2 > 80 {print "¡Advertencia! " $1 " está al " $2 "%"}'

# Monitor de procesos en tiempo real
watch -n 1 'ps aux | awk "NR>1 && \$3 > 50 {print \$1, \$2, \$3, \$11}"'
```

### 12. Patrones avanzados
```bash
# Procesar múltiples archivos
awk '/error|warning/i {print FILENAME ":" NR ":" $0}' *.log

# Usar rangos de líneas
cat archivo.txt | awk '/INICIO/,/FIN/ {print NR ": " $0}'

# Condicionales complejas
cat datos.txt | awk '$1 ~ /^A/ && $2 > 100 {sum += $2} END {print "Suma de valores A*:", sum}'
```

### 13. AWK para tareas de administración
```bash
# Backup de configuración con timestamp
awk '{print "# Backup creado: " strftime("%Y-%m-%d %H:%M:%S")}1' config.conf > config_$(date +%Y%m%d).conf

# Limpieza de logs antiguos
find /var/log -name "*.log" -mtime +30 | xargs awk 'NR>1000 {nextfile} {print}' > logs_recientes.txt

# Análisis de permisos de archivos
ls -la | awk 'NR>1 && substr($1,2,1)!="r" {print "Sin lectura:", $9}'
```

### 14. One-liners útiles
```bash
# Contar archivos por extensión
ls -1 | awk -F. '{print $NF}' | sort | uniq -c | sort -nr

# Extraer URLs de archivos HTML
cat *.html | awk 'match($0, /https?:\/\/[^" ]+/) {print substr($0, RSTART, RLENGTH)}'

# Validar formato de números de teléfono
cat telefonos.txt | awk '/^[0-9]{9}$/ {print "Válido:", $0} !/^[0-9]{9}$/ {print "Inválido:", $0}'
```

### 15. AWK para procesamiento de datos científicos
```bash
# Estadísticas básicas
cat datos.csv | awk 'NR>1 {sum+=$1; sumsq+=$1^2; n++} END {
    mean = sum/n
    variance = (sumsq - sum^2/n)/n
    print "Media:", mean, "Varianza:", variance, "Desviación:", sqrt(variance)
}'

# Normalización de datos
cat valores.txt | awk 'NR==1 {max=$1; min=$1; next}
{max=$1>max?$1:max; min=$1<min?$1:min}
END {print "Máximo:", max, "Mínimo:", min}' | awk 'NR>1 {print ($1-min)/(max-min)}'
```
