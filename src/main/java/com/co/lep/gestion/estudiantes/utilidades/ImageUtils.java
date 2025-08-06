package com.co.lep.gestion.estudiantes.utilidades;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUtils {

    public static int[] obtenerDimensionesDesdeBase64(String base64Image) throws IOException {
        // Remover el prefijo "data:image/png;base64," si existe
        String base64 = base64Image.split(",")[1];

        // Decodificar Base64 a un arreglo de bytes
        byte[] imageBytes = Base64.getDecoder().decode(base64);

        // Convertir bytes en un BufferedImage
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bis);

        if (image == null) {
            throw new IOException("No se pudo leer la imagen.");
        }

        return new int[]{image.getWidth(), image.getHeight()}; // Retorna [ancho, alto]
    }
}
