/*
 * @fileoverview    {NintendoJoystickComponentActionSetter}
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
package com.project.dev.game.desktop.setter;

import com.project.dev.game.desktop.buttonaction.ButtonAction;
import com.project.dev.game.desktop.frame.GameFrame;
import com.project.dev.joystick.button.GenericButtonActionListener;
import com.project.dev.joystick.name.generic.GenericJoystick;
import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import javax.swing.JLabel;

/**
 * TODO: Definición de {@code NintendoJoystickComponentActionSetter}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class NintendoJoystickComponentActionSetter extends GenericJoystickComponentActionSetter<GameFrame, JLabel> {

    /**
     * TODO: Definición de {@code NintendoJoystickComponentActionSetter}.
     *
     * @param component      es un componente gráfico que será controlado por el joystick
     * @param window         es el JFrame al que pertenece el componnete gráfico.
     * @param windowWidth    es el ancho máximo de la ventana donde se moverá el componente gráfico.
     * @param windowHeight   Es el alto máximo de la ventana donde se moverá el componente gráfico.
     * @param windowDivision Indica en cuanto se dividirá la ventana (cuantas imagenes cabrán a lo
     *                       alto).
     */
    public NintendoJoystickComponentActionSetter(JLabel component, GameFrame window, int windowWidth, int windowHeight, int windowDivision) {
        super(component, window, windowWidth, windowHeight, windowDivision);
    }

    /**
     * FIXME: Definición de {@code setButtonActions}. Asigna acciones a un joystick.
     *
     * @param joystick es el joystick que controlará el componente.
     * @throws java.lang.Exception si no es posible asignar acciones al joystick indicado.
     */
    @Override
    public void setButtonActions(GenericJoystick joystick) throws Exception {
        if (!(joystick instanceof NintendoJoystick))
            throw new Exception("This method is only allowed for NintendoJoystick");

        joystick.getButton(NintendoJoystick.BUTTON_START_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonStartAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonStartAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonStartAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonStartAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_SELECT_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonSelectAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonSelectAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonSelectAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonSelectAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_LEFT_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonMoveLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonMoveLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonMoveLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonMoveLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_RIGHT_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonMoveRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonMoveRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonMoveRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonMoveRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_UP_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonMoveUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonMoveUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonMoveUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonMoveUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_DOWN_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonMoveDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonMoveDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonMoveDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonMoveDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_MEDIUM_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonMoveMediumAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonMoveMediumAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonMoveMediumAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonMoveMediumAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_Y_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonLeftAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_A_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonRightAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_X_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonUpAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });

        joystick.getButton(NintendoJoystick.BUTTON_B_NAME).setOnActionListener(new GenericButtonActionListener() {
            @Override
            public void onTyped() {
                ButtonAction.runButtonDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_TYPED);
            }

            @Override
            public void onPressed() {
                ButtonAction.runButtonDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_PRESSED);
            }

            @Override
            public void onReleased() {
                ButtonAction.runButtonDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_RELEASED);
            }

            @Override
            public void onUnpressed() {
                ButtonAction.runButtonDownAction(NintendoJoystickComponentActionSetter.this, BUTTON_UNPRESSED);
            }
        });
    }
}
