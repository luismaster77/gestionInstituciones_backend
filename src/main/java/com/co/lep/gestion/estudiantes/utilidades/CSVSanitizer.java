package com.co.lep.gestion.estudiantes.utilidades;

public class CSVSanitizer {

	// Método para validar la entrada del archivo CSV
    public boolean validarEntrada(String entrada) {
        // Verificar si la entrada contiene caracteres no deseados
        return entrada.matches("^[\\w\\d\\s,;-]*$"); // Permitir `;` como carácter válido
    }

    // Método para sanitizar la entrada del archivo CSV
    public String sanitizarEntrada(String entrada) {
        // Eliminar etiquetas HTML utilizando una expresión regular
        return entrada.replaceAll("\\<.*?\\>", "");
    }

    // Método para procesar el archivo CSV
    public void procesarArchivoCSV(String[] lineasCSV) {
        for (String linea : lineasCSV) {
            // Validar la entrada de cada línea del archivo CSV
            if (validarEntrada(linea)) {
                // Sanitizar la entrada de cada línea del archivo CSV
                String entradaSanitizada = sanitizarEntrada(linea);
                // Procesar la línea del archivo CSV
                procesarLineaCSV(entradaSanitizada);
            } else {
                // Realizar acciones para manejar la entrada no válida
                System.out.println("La línea contiene caracteres no válidos: " + linea);
            }
        }
    }

    // Método para procesar cada línea del archivo CSV (aquí puedes implementar tu lógica de procesamiento)
    public void procesarLineaCSV(String linea) {
        // Implementa tu lógica de procesamiento aquí
        System.out.println("Línea procesada: " + linea);
    }
}
