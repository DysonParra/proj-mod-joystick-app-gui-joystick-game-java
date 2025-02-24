/*
 * @fileoverview    {ButtonAction}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.game.desktop.buttonaction;

import com.project.dev.game.desktop.frame.GameFrame;
import com.project.dev.game.desktop.getter.GraphicGetter;
import com.project.dev.game.desktop.setter.GenericJoystickComponentActionSetter;
import java.awt.Point;
import javax.swing.JLabel;

import static com.project.dev.game.desktop.enemy.Enemy.ENEMY_IMAGE_QUANTITY;
import static com.project.dev.joystick.GenericComponent.BUTTON_PRESSED;
import static com.project.dev.joystick.GenericComponent.BUTTON_RELEASED;
import static com.project.dev.joystick.GenericComponent.BUTTON_TYPED;
import static com.project.dev.joystick.GenericComponent.BUTTON_UNPRESSED;

/**
 * TODO: Description of {@code ButtonAction}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class ButtonAction {

    private static int buttonRightImageNumber = 0;

    /**
     * FIXME: Description of {@code runButtonStartAction}. Ejecuta la acción para el botón start.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonStartAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else
                    actionSetter.getWindow().pressStartButton();
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonSelectAction}. Ejecuta la acción para el botón select.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonSelectAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonMoveLeftAction}. Ejecuta la acción para el botón flecha
     * izquierda.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveLeftAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else if (!actionSetter.getWindow().isPaused()) {
                    Point location = actionSetter.getComponent().getLocation();
                    if (location.getX() > 0) {
                        location.setLocation(location.getX() - (actionSetter.getComponent().getWidth() / 2), location.getY());
                        actionSetter.getComponent().setLocation(location);
                    }
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonMoveRightAction}. Ejecuta la acción para el botón flecha
     * derecha.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveRightAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else if (!actionSetter.getWindow().isPaused()) {
                    Point location = actionSetter.getComponent().getLocation();
                    if (location.getX() + actionSetter.getComponent().getWidth() + (actionSetter.getComponent().getWidth() / 2) <= actionSetter.getWindowWidth()) {
                        location.setLocation(location.getX() + (actionSetter.getComponent().getWidth() / 2), location.getY());
                        actionSetter.getComponent().setLocation(location);
                    }
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonMoveUpAction}. Ejecuta la acción para el botón flecha
     * arriba.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveUpAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else if (!actionSetter.getWindow().isPaused()) {
                    Point location = actionSetter.getComponent().getLocation();
                    if (location.getY() > 0) {
                        location.setLocation(location.getX(), location.getY() - (actionSetter.getComponent().getHeight() / 2));
                        actionSetter.getComponent().setLocation(location);
                    }
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonMoveDownAction}. Ejecuta la acción para el botón flecha
     * abajo.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveDownAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else if (!actionSetter.getWindow().isPaused()) {
                    Point location = actionSetter.getComponent().getLocation();
                    if (location.getY() + actionSetter.getComponent().getHeight() + (actionSetter.getComponent().getHeight() / 2) <= actionSetter.getWindowHeight()) {
                        location.setLocation(location.getX(), location.getY() + (actionSetter.getComponent().getHeight() / 2));
                        actionSetter.getComponent().setLocation(location);
                    }
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonMoveMediumAction}. Ejecuta la acción para el botón
     * flecha intermedia.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveMediumAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonLeftAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonLeftAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else {
                    int imageIndex = (int) (Math.random() * ENEMY_IMAGE_QUANTITY) + 1;
                    actionSetter.getComponent().setIcon(GraphicGetter.getGraphic("enemy/enemy (" + imageIndex + ").png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonRightAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonRightAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else {
                    buttonRightImageNumber++;
                    actionSetter.getComponent().setIcon(GraphicGetter.getGraphic("punch_0" + ((buttonRightImageNumber % 6) + 1) + ".png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonUpAction}. Ejecuta la acción para el botón de arriba del
     * lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonUpAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else {
                    actionSetter.getWindow().getViewScore().setVisible(!actionSetter.getWindow().getViewScore().isVisible());
                    actionSetter.getWindow().getViewMaxScore().setVisible(!actionSetter.getWindow().getViewMaxScore().isVisible());
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Description of {@code runButtonDownAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonDownAction(GenericJoystickComponentActionSetter<GameFrame, JLabel> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                if (actionSetter.getWindow().isLosed())
                    actionSetter.getWindow().setLosed(false);
                else if (!actionSetter.getWindow().isPaused())
                    actionSetter.getWindow().setEnemiesSleepTime(50);
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                if (!actionSetter.getWindow().isLosed() && !actionSetter.getWindow().isPaused()) {
                    actionSetter.getWindow().setManualEnemiesSpeed(false);
                }
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }
}
