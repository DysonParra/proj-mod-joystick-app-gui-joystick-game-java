/*
 * @fileoverview    {Enemy}
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
package com.project.dev.game.desktop.enemy;

import com.project.dev.game.desktop.getter.GraphicGetter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lombok.Data;

/**
 * TODO: Definición de {@code Enemy}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class Enemy {

    public static int ENEMY_IMAGE_QUANTITY = 100;                               // Cantidad de imagenes posibles para un enemigo.
    private JLabel view;                                                        // Componente gráfico del enemigo.
    private int viewWidth = 0;
    private int viewHeight = 0;
    private int PositionX = 0;
    private int PositionY = 0;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private int enemyQuantity = 0;                                              // Cantiadd de enemigos que caben en lo alto de la ventana.

    /**
     * TODO: Definición de {@code Enemy}.
     *
     * @param window        Ventana donde se agregará el enemigo.
     * @param windowWidth   Máximo de la ventana donde se moverá el enemigo.
     * @param windowHeight  Máximo de la ventana donde se moverá el enemigo.
     * @param enemyQuantity Cantiadd de enemigos que cabrán en la pantalla.
     */
    public Enemy(JFrame window, int windowWidth, int windowHeight, int enemyQuantity) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.view = new JLabel();
        this.enemyQuantity = enemyQuantity;
        this.view.setIcon(GraphicGetter.getGraphic("punch_01.png", this.windowWidth, this.windowHeight, this.enemyQuantity));
        this.viewWidth = this.view.getIcon().getIconWidth();
        this.viewHeight = this.view.getIcon().getIconHeight();
        this.view.setSize(this.viewWidth, this.viewHeight);
        this.PositionX = windowWidth - viewWidth;
        this.view.setLocation(this.PositionX, this.PositionY);
        window.add(view, 0);
    }

    /**
     * TODO: Definición de {@code Enemy}.
     *
     * @param window        Ventana donde se agregará el enemigo.
     * @param windowWidth   Máximo de la ventana donde se moverá el enemigo.
     * @param windowHeight  Máximo de la ventana donde se moverá el enemigo.
     * @param enemyQuantity Cantiadd de enemigos que cabrán en la pantalla.
     * @param indexY        Posición inicial que tedrá el enemigo en Y.
     */
    public Enemy(JFrame window, int windowWidth, int windowHeight, int enemyQuantity, int indexY) {
        this(window, windowWidth, windowHeight, enemyQuantity);
        PositionY = viewHeight * indexY;
        view.setLocation(windowWidth - view.getIcon().getIconWidth(), PositionY);
    }

    /**
     * FIXME: Definición de {@code moveLeft}. Mueve el enemigo a la izquierda.
     *
     * @return si se pudo mover el enemigo a la izquierda.
     */
    public boolean moveLeft() {
        PositionX -= viewWidth / 2;
        if (PositionX <= -viewWidth) {
            return false;
        }
        view.setLocation(PositionX, PositionY);

        return true;
    }

    /**
     * FIXME: Definición de {@code restorePositionX}. Mueve el enemigo a la posición inicial en X.
     */
    public void restorePositionX() {
        this.PositionX = windowWidth - viewWidth;
        view.setLocation(PositionX, PositionY);
    }

    /**
     * FIXME: Definición de {@code updateIcon}. Actualiza la imagen del enemigo por una cualquiera.
     */
    public void updateIcon() {
        int imageIndex = (int) (Math.random() * ENEMY_IMAGE_QUANTITY) + 1;
        this.view.setIcon(GraphicGetter.getGraphic("enemy/enemy (" + imageIndex + ").png", windowWidth, windowHeight, enemyQuantity));
    }
}
