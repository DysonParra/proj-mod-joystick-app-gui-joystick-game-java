/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.game.desktop.getter;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * TODO: Definición de {@code GraphicGetter}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class GraphicGetter {

    private static final String RES_ROOT_PATH = "/assets/com/project/dev/game/";

    /**
     * FIXME: Definición de {@code getGraphic}. Obtiene un ImageIcon desde los recursos de la app.
     *
     * @param fileName es el nombre de la imagen.
     * @return un ImageIcon obtenido desde el archivo indicado.
     */
    public static ImageIcon getGraphic(String fileName) {
        return new ImageIcon(GraphicGetter.class.getResource(RES_ROOT_PATH + fileName));
    }

    /**
     * FIXME: Definición de {@code getGraphic}. Obtiene un ImageIcon desde los recursos de la app.
     *
     * @param fileName       es el nombre de la imagen.
     * @param windowWidth    es el ancho máximo de la ventana donde se moverá el componente gráfico.
     * @param windowHeight   Es el alto máximo de la ventana donde se moverá el componente gráfico.
     * @param windowDivision Indica en cuanto se dividirá la ventana (cuantas imagenes cabrán a lo
     *                       alto).
     * @return un ImageIcon obtenido desde el archivo indicado.
     */
    public static ImageIcon getGraphic(String fileName, int windowWidth, int windowHeight, int windowDivision) {
        ImageIcon imageIcon = (new ImageIcon(GraphicGetter.class.getResource(RES_ROOT_PATH + fileName)));
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        double percent = (windowHeight / (double) windowDivision) / height;
        return new ImageIcon(imageIcon.getImage().getScaledInstance((int) (width * percent), (int) (height * percent), Image.SCALE_DEFAULT));
    }
}
