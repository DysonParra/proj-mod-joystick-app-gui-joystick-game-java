/*
 * @fileoverview    {GameFrame}
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
package com.project.dev.game.desktop.frame;

import com.project.dev.game.desktop.enemy.Enemy;
import com.project.dev.game.desktop.getter.GraphicGetter;
import com.project.dev.game.desktop.setter.GenericJoystickComponentActionSetter;
import com.project.dev.game.desktop.setter.NintendoJoystickComponentActionSetter;
import com.project.dev.game.desktop.setter.PolyJoystickComponentActionSetter;
import com.project.dev.joystick.exception.JoystickClientConnectionRefusedException;
import com.project.dev.joystick.exception.UnknownJoystickTypeException;
import com.project.dev.joystick.factory.JoystickFactory;
import com.project.dev.joystick.listener.JoystickServerListener;
import com.project.dev.joystick.name.generic.type.GenericJoystickServer;
import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import com.project.dev.joystick.name.poly.PolyJoystick;
import com.project.dev.joystick.setter.GenericJoystickPrintActionSetter;
import com.project.dev.tray.setter.TrayIconSetter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * TODO: Definición de {@code GameFrame}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class GameFrame extends JFrame {

    /* Ancho de la ventana. */
    private int width = 0;
    /* Alto de la ventana. */
    private int height = 0;

    /*
     * Variables locales.
     */
    private String joystickType = "";                                                   // Indica el tipo de joystick a utilizar.
    private String joystickName = "";                                                   // Indica el nombre del joystick a utilizar.
    private GenericJoystickServer server = null;                                        // Indica el joystick asociado a la ventana.
    private final GameFrame window = this;                                              // Referencia a la ventana.
    private int score = 0;                                                              // Puntuación actual.
    private int maxScore = 0;                                                           // Puntuación máxima.
    private JLabel viewPaused;                                                          // Crea un componente gráfico para mostar que el juego está pausado.
    private JLabel viewLose;                                                            // Crea un componente gráfico para mostar que se perdió la partida.
    private JLabel viewScore;                                                           // Crea un componente gráfico para mostar la puntuación.
    private JLabel viewMaxScore;                                                        // Crea un componente gráfico para mostar la máxima puntuación obtenida.
    private JLabel viewBackground;                                                      // El fondo del juego.
    private JLabel player;                                                              // El jugador.
    private final int playerMoveQuantity = 6;                                           // Cantidad de veces que cabe el jugador a lo alto de la ventana.
    private final Enemy[] enemies = new Enemy[playerMoveQuantity];                      // Enemigos.
    private final AtomicBoolean losed = new AtomicBoolean(false);                       // Indica i se perdió la partida.
    private boolean paused = true;                                                      // Si el juego está pausado.
    private final Random random = new Random();                                         // Usada para generar objetos aleatorios.
    private final long DEFAULT_ENEMIES_SLEEP_TIME = 500;                                // Cantidad de tiempo por defecto que esperan los enenmigos antes de moverse de nuevo.
    private long enemiesSleepTime = DEFAULT_ENEMIES_SLEEP_TIME;                         // Cantidad de tiempo que esperan los enenmigos antes moverse de nuevo.
    private final AtomicBoolean manualEnemiesSleepTime = new AtomicBoolean(false);      // Si la velocidad en que se mueven los enemigos se puso manualmente (desde el joystick).)
    private int visibleEnemies = 0;                                                     // Cantidad de enemigos visibles.
    private boolean closedGame = false;                                                 // Si el juego se cerró.
    private final Object pauseLock = new Object();
    private final Thread enemiesThread = new Thread(this::startGame);
    private ByteArrayOutputStream os;                                                   // Crea objeto de tipo ByteArrayOutputStream.
    private PrintStream printer;                                                        // Crea objeto de tipo PrintStream.
    private String output;                                                              // Crea String.

    /**
     * TODO: Definición de {@code getJoystickType}.
     *
     * @return
     */
    public String getJoystickType() {
        return joystickType;
    }

    /**
     * TODO: Definición de {@code setJoystickType}.
     *
     * @param joystickType
     */
    public void setJoystickType(String joystickType) {
        this.joystickType = joystickType;
    }

    /**
     * TODO: Definición de {@code getJoystickName}.
     *
     * @return
     */
    public String getJoystickName() {
        return joystickName;
    }

    /**
     * TODO: Definición de {@code setJoystickName}.
     *
     * @param joystickName
     */
    public void setJoystickName(String joystickName) {
        this.joystickName = joystickName;
    }

    /**
     * TODO: Definición de {@code getViewScore}.
     *
     * @return
     */
    public JLabel getViewScore() {
        return viewScore;
    }

    /**
     * TODO: Definición de {@code setViewScore}.
     *
     * @param viewScore
     */
    public void setViewScore(JLabel viewScore) {
        this.viewScore = viewScore;
    }

    /**
     * TODO: Definición de {@code getViewMaxScore}.
     *
     * @return
     */
    public JLabel getViewMaxScore() {
        return viewMaxScore;
    }

    /**
     * TODO: Definición de {@code setViewMaxScore}.
     *
     * @param viewMaxScore
     */
    public void setViewMaxScore(JLabel viewMaxScore) {
        this.viewMaxScore = viewMaxScore;
    }

    /**
     * TODO: Definición de {@code getViewBackground}.
     *
     * @return
     */
    public JLabel getViewBackground() {
        return viewBackground;
    }

    /**
     * TODO: Definición de {@code setViewBackground}.
     *
     * @param viewBackground
     */
    public void setViewBackground(JLabel viewBackground) {
        this.viewBackground = viewBackground;
    }

    /**
     * TODO: Definición de {@code getEnemiesSleepTime}.
     *
     * @return
     */
    public long getEnemiesSleepTime() {
        return enemiesSleepTime;
    }

    /**
     * TODO: Definición de {@code setEnemiesSleepTime}.
     *
     * @param enemiesSleepTime
     */
    public void setEnemiesSleepTime(long enemiesSleepTime) {
        this.enemiesSleepTime = enemiesSleepTime;
        manualEnemiesSleepTime.set(true);
    }

    /**
     * TODO: Definición de {@code isManualEnemiesSpeed}.
     *
     * @return
     */
    public boolean isManualEnemiesSpeed() {
        return manualEnemiesSleepTime.get();
    }

    /**
     * TODO: Definición de {@code setManualEnemiesSpeed}.
     *
     * @param manualEnemiesSpeed
     */
    public void setManualEnemiesSpeed(boolean manualEnemiesSpeed) {
        manualEnemiesSleepTime.set(manualEnemiesSpeed);
        setDefautEnemiesSleepTime();
    }

    /**
     * TODO: Definición de {@code isPaused}.
     *
     * @return
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * TODO: Definición de {@code setPaused}.
     *
     * @param paused
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            viewPaused.setVisible(false);
            synchronized (pauseLock) {
                pauseLock.notifyAll();
            }
        } else {
            viewPaused.setVisible(true);
        }
    }

    /**
     * TODO: Definición de {@code isLosed}.
     *
     * @return
     */
    public boolean isLosed() {
        return losed.get();
    }

    /**
     * TODO: Definición de {@code setLosed}.
     *
     * @param losed
     */
    public void setLosed(boolean losed) {
        this.losed.set(losed);
        if (this.losed.get()) {
            viewLose.setVisible(true);
        } else {
            viewLose.setVisible(false);
            restartGame();
        }
    }

    /**
     * TODO: Definición de {@code restartGame}.
     *
     */
    public void restartGame() {
        enemiesSleepTime = 500;
        score = 0;
        restoreEnemies();
        player.setLocation(0, 0);
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    /**
     * TODO: Definición de {@code pressStartButton}.
     *
     */
    public void pressStartButton() {
        if (paused)
            setPaused(false);
        else
            setPaused(true);
    }

    /**
     * TODO: Definición de {@code GameFrame}.
     *
     */
    public GameFrame() {
        initComponents();

        // Obtiene el alto en píxeles de la ventana.
        Dimension windowSize = window.getContentPane().getSize();
        width = windowSize.width;
        height = windowSize.height;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * FIXME: Definición de {@code startFrame}. Inicializa el frame con la información obtenida del
     * frame anterior.
     */
    public void startFrame() {
        try {
            // Obtiene un joystick servidor del nombre indicado en el frame anterior usando la fábrica de joystick.
            server = (GenericJoystickServer) JoystickFactory.makeJoystick(joystickType, joystickName, null, 0);

            // Agrega acciones de impresión en pantalla a cada botón del joystick.
            GenericJoystickPrintActionSetter printActionSetter = new GenericJoystickPrintActionSetter();
            printActionSetter.addButtonPrintActions(server);

            // Asigna icono y título al frame, y agrega el frame al systemTray.
            window.setIconImage(GraphicGetter.getGraphic("game_icon.png").getImage());
            window.setTitle("Game en " + server.getServerIpAddress() + " Puerto " + server.getServerPort() + " (" + server.getName() + ")");
            TrayIconSetter.setTrayIconToFrame(window);

            // Agrega mensajes que informan cuando un cliente se conecte y cuando uno se desconecte.
            server.setOnJoystickServerListener(new JoystickServerListener() {
                @Override
                public void onClientConnected() {
                    new Thread(() -> {
                        JOptionPane.showMessageDialog(window, "Se ha conectado un cliente.");
                    }).start();
                }

                @Override
                public void onClientDisconnected() {
                    new Thread(() -> {
                        JOptionPane.showMessageDialog(window, "Se ha desconectado un cliente.");
                    }).start();
                }
            });

            // Indica que cuando se cierre la ventana se parará el servidor del joystick.
            // Además notifica al hilo con los enemigos que se despierte y que termine la ejecución.
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    server.stopServer();
                    paused = true;
                    closedGame = true;
                    synchronized (pauseLock) {
                        pauseLock.notifyAll();
                    }
                }
            });

            // Crea un componente que tendrá el fondo, y lo agrega a la ventana.
            viewBackground = new JLabel();
            viewBackground.setIcon(GraphicGetter.getGraphic("game_background.png", width, height, 1));
            viewBackground.setSize(viewBackground.getIcon().getIconWidth(), viewBackground.getIcon().getIconHeight());
            viewBackground.setLocation(0, 0);
            window.add(viewBackground, 0);

            // Inicializa el componente que tendrá el jugador, y lo agrega a la ventana.
            player = new JLabel();
            player.setIcon(GraphicGetter.getGraphic("punch_01.png", width, height, playerMoveQuantity));
            player.setSize(player.getIcon().getIconWidth(), player.getIcon().getIconHeight());
            player.setLocation(0, 0);
            window.add(player, 0);

            // Asigna acciones para controlar el jugador y la ventana dependiendo del nombre del joystick.
            GenericJoystickComponentActionSetter componentActionSetter;

            switch (joystickName) {
                case NintendoJoystick.JOYSTICK_NAME:
                    componentActionSetter = new NintendoJoystickComponentActionSetter(player, window, width, height, playerMoveQuantity);
                    componentActionSetter.setButtonActions(server);
                    break;

                case PolyJoystick.JOYSTICK_NAME:
                    componentActionSetter = new PolyJoystickComponentActionSetter(player, window, width, height, playerMoveQuantity);
                    componentActionSetter.setButtonActions(server);
                    break;
            }

            // Crea los enemigos del juego e inicia el hilo pausado.
            for (int i = 0; i < playerMoveQuantity; i++) {
                Enemy enemy = new Enemy(window, width, height, playerMoveQuantity, i);
                enemy.updateIcon();
                enemies[i] = enemy;
            }
            setVisibleEnemies();
            enemiesThread.start();

            // Indica que se validará si el jugador choca con enemigos cada vez que se mueva.
            player.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    //System.out.println("PLAYER");
                    if (hitEnemy(player, enemies)) {
                        synchronized (pauseLock) {
                            pauseLock.notifyAll();
                        }
                    }
                }
            });

            // Agrega a la pantalla los label con las puntuaciones, el del juego pausado, y el de juego perdido.
            viewScore = new JLabel("Score: ", SwingConstants.RIGHT);
            viewScore.setLocation(0, 0);
            viewScore.setSize(width - 10, 20);
            viewScore.setText("Score: 0000000000");
            viewScore.setFont(new Font(viewScore.getFont().getName(), Font.BOLD, 15));
            viewScore.setForeground(Color.BLACK);
            window.add(viewScore, 1);

            viewMaxScore = new JLabel("Max: ", SwingConstants.RIGHT);
            viewMaxScore.setLocation(0, 20);
            viewMaxScore.setSize(width - 10, 20);
            viewMaxScore.setText("Max: 0000000000");
            viewMaxScore.setFont(new Font(viewMaxScore.getFont().getName(), Font.BOLD, 15));
            viewMaxScore.setForeground(Color.BLACK);
            window.add(viewMaxScore, 0);

            viewPaused = new JLabel("PAUSADO", SwingConstants.CENTER);
            viewPaused.setLocation(0, 20);
            viewPaused.setSize(width - 10, 50);
            viewPaused.setText("PAUSADO");
            viewPaused.setFont(new Font(viewPaused.getFont().getName(), Font.BOLD, 50));
            viewPaused.setForeground(Color.YELLOW);
            window.add(viewPaused, 0);

            viewLose = new JLabel("PERDISTE", SwingConstants.CENTER);
            viewLose.setLocation(0, 20);
            viewLose.setSize(width - 10, 50);
            viewLose.setText("PERDISTE");
            viewLose.setFont(new Font(viewPaused.getFont().getName(), Font.BOLD, 50));
            viewLose.setForeground(Color.red);
            viewLose.setVisible(false);
            window.add(viewLose, 0);
        } catch (JoystickClientConnectionRefusedException ex) {
            dispose();
        } catch (UnknownJoystickTypeException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * FIXME: Definición de {@code setVisibleEnemies}. Escoge cuales enemigos estarán visibles.
     */
    public void setVisibleEnemies() {
        visibleEnemies = 0;
        for (Enemy enemy : enemies) {
            if (random.nextBoolean()) {
                enemy.getView().setVisible(true);
                visibleEnemies++;
            } else {
                enemy.getView().setVisible(false);
            }
        }

        if (visibleEnemies == enemies.length) {
            enemies[(int) (Math.random() * enemies.length)].getView().setVisible(false);
            visibleEnemies--;
            //System.out.println("All");
        } else if (visibleEnemies == 0) {
            enemies[(int) (Math.random() * enemies.length)].getView().setVisible(true);
            visibleEnemies++;
            //System.out.println("Anyone");
        }
    }

    /**
     * FIXME: Definición de {@code restoreEnemies}. Reinicia las posiciones de los enemigos al
     * iniciar el juego.
     */
    public void restoreEnemies() {
        setVisibleEnemies();
        for (Enemy enemyToRestore : enemies) {
            enemyToRestore.updateIcon();
            enemyToRestore.restorePositionX();
        }
    }

    /**
     * FIXME: Definición de {@code hitEnemy}. ´Verifica si un enemigo específico chocó con el
     * jugador.
     *
     * @param player es el jugador.
     * @param enemy  es el enemigo.
     * @return si el enemio chocó.
     */
    public boolean hitEnemy(JLabel player, Enemy enemy) {
        Point playerLocation;
        int startPlayerX;
        int endPlayerX;
        int startPlayerY;
        int endPlayerY;

        int startEnemyX;
        int endEnemyX;
        int startEnemyY;
        int endEnemyY;

        playerLocation = player.getLocation();
        startPlayerX = playerLocation.x + 1;
        endPlayerX = playerLocation.x + player.getWidth();
        startPlayerY = playerLocation.y + 1;
        endPlayerY = playerLocation.y + player.getHeight();

        startEnemyX = enemy.getPositionX() + 1;
        endEnemyX = enemy.getPositionX() + enemy.getViewWidth();
        startEnemyY = enemy.getPositionY() + 1;
        endEnemyY = enemy.getPositionY() + enemy.getViewHeight();

        //System.out.println("X: [" + startPlayerX + "," + endPlayerX + "]");
        //System.out.println("Y: [" + startPlayerY + "," + endPlayerY + "]");
        //System.out.println("X: [" + startEnemyX + "," + endEnemyX + "]");
        //System.out.println("Y: [" + startEnemyY + "," + endEnemyY + "]");
        if (((startEnemyX >= startPlayerX && startEnemyX <= endPlayerX)
                || (endEnemyX >= startPlayerX && endEnemyX <= endPlayerX))
                && ((startEnemyY >= startPlayerY && startEnemyY <= endPlayerY)
                || (endEnemyY >= startPlayerY && endEnemyY <= endPlayerY))) {
            setLosed(true);
            //System.out.println("YES\n");
            return true;
        }

        //System.out.println("NO\n");
        return false;
    }

    /**
     * FIXME: Definición de {@code hitEnemy}. Verifica si algún enemigo chocó con el jugador.
     *
     * @param player  es el jugador.
     * @param enemies son los enemigos.
     * @return si algún el enemio chocó.
     */
    public boolean hitEnemy(JLabel player, Enemy[] enemies) {
        for (Enemy enemy : enemies)
            if (enemy.getView().isVisible())
                if (hitEnemy(player, enemy))
                    return true;
        return false;
    }

    /**
     * FIXME: Definición de {@code setDefautEnemiesSleepTime}. Asigna la velocidad por defecto a los
     * enemigos.
     */
    public void setDefautEnemiesSleepTime() {
        // Si la velocidad en que se mueven los enemigos no se especificó desde el joystick.
        if (!manualEnemiesSleepTime.get())
            if (score >= 100 && score <= 200 && enemiesSleepTime != 400)
                enemiesSleepTime = 400;
            else if (score >= 200 && score <= 300 && enemiesSleepTime != 300)
                enemiesSleepTime = 300;
            else if (score >= 300 && score <= 400 && enemiesSleepTime != 200)
                enemiesSleepTime = 200;
            else if (score >= 400 && score <= 500 && enemiesSleepTime != 100)
                enemiesSleepTime = 100;
            else if (score >= 500 && enemiesSleepTime != 50)
                enemiesSleepTime = 50;
    }

    /**
     * FIXME: Definición de {@code startGame}. Inicia el juego.
     */
    public void startGame() {
        try {
            while (!closedGame) {
                while (!paused && !losed.get()) {
                    score += 2;
                    os = new ByteArrayOutputStream();               // Asigna un nuevo buffer a os.
                    printer = new PrintStream(os);                  // Realaciona printer con os.
                    printer.printf("%010d\n", score);
                    output = os.toString();
                    viewScore.setText("Score: " + output);
                    if (score > maxScore) {
                        maxScore = score;
                        viewMaxScore.setText("Max: " + output);
                    }

                    setDefautEnemiesSleepTime();                    // Asigna velociad a los enemigos.

                    synchronized (pauseLock) {
                        pauseLock.wait(enemiesSleepTime);
                    }

                    if (!paused && !losed.get()) {
                        /*
                         * -
                         * if (!enemies[0].moveLeft()) restoreEnemies(); else {
                         * System.out.println("ENEMY"); if (hitEnemy(player, enemies)) {
                         * System.out.println("EN-HIT"); break; } }
                         */
                        for (Enemy enemy : enemies)
                            if (!enemy.moveLeft()) {
                                restoreEnemies();
                                break;
                            }

                        //System.out.println("ENEMY");
                        if (hitEnemy(player, enemies)) {
                            //System.out.println("EN-HIT");
                            break;
                        }
                    } else if (!closedGame) {
                        //System.out.println("PL-HIT");
                    }
                }

                //System.out.println("SALE");
                if (!closedGame)
                    synchronized (pauseLock) {
                    pauseLock.wait();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println("CLOSED");
    }

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new GameFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
