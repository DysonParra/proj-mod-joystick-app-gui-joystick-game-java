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
package com.project.dev.game.desktop.setter;

import com.project.dev.joystick.name.generic.GenericJoystick;
import lombok.Data;

/**
 * TODO: Definición de {@code C}.
 *
 * @author Dyson Parra
 * @since 1.8
 * @param <T> es el tipo de componente de la ventana (JFrame o activity)
 * @param <C> es el tipo de componente gráfico que se manipulará.
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public abstract class GenericJoystickComponentActionSetter<T, C> {

    protected T window;                                                         // Ventana controlada por el joystick.
    protected C component;                                                      // Componente controlado por el joystick.
    protected int windowWidth = 0;                                              // Ancho del frame donde se moverá el componente gráfico.
    protected int windowHeight = 0;                                             // Alto del frame donde se moverá el componente gráfico.
    protected int windowDivision = 0;                                           // Cuantas imagenes caben a lo alto de la ventana.

    /**
     * TODO: Definición de {@code GenericJoystickComponentActionSetter}.
     *
     * @param component      es un componente que será controlado por el joystick
     * @param window         es el tipo de componente de la ventana (JFrame o activity).
     * @param windowWidth    es el ancho máximo de la ventana donde se moverá el componente gráfico.
     * @param windowHeight   Es el alto máximo de la ventana donde se moverá el componente gráfico.
     * @param windowDivision Indica en cuanto se dividirá la ventana (cuantas imagenes cabrán a lo
     *                       alto).
     */
    public GenericJoystickComponentActionSetter(C component, T window, int windowWidth, int windowHeight, int windowDivision) {
        this.component = component;
        this.window = window;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowDivision = windowDivision;
    }

    /**
     * FIXME: Definición de {@code setButtonActions}. Asigna acciones a un joystick.
     *
     * @param joystick esl el joystick que controlará el componente gráfico.
     * @throws java.lang.Exception si no es posible asignar acciones al joystick indicado.
     */
    public abstract void setButtonActions(GenericJoystick joystick) throws Exception;
}
