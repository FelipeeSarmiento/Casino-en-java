import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

public class JuegoDados extends JFrame implements ActionListener {
    private JLabel dado1Label, dado2Label, resultadoLabel;
    private JButton lanzarButton;
    private Random random;
    private ImageIcon[] imagenesDados;
    private Timer timer;
    private Clip sonidoLanzarDados;
    
    public JuegoDados() {
        super("Juego de dados");
        
        // Inicializar variables
        random = new Random();
        imagenesDados = new ImageIcon[6];
        timer = new Timer();
        
        // Cargar imágenes de los dados
        for (int i = 0; i < 6; i++) {
            imagenesDados[i] = new ImageIcon("dado" + (i+1) + ".png");
        }
        
        // Cargar sonido de lanzar dados
        cargarSonido("lanzarDados.wav");
        
        // Crear componentes
        dado1Label = new JLabel(imagenesDados[0]);
        dado2Label = new JLabel(imagenesDados[0]);
        resultadoLabel = new JLabel("Resultado: ");
        lanzarButton = new JButton("Lanzar dados");
        lanzarButton.addActionListener(this);
        
        // Crear paneles
        JPanel dadosPanel = new JPanel(new GridLayout(1, 2));
        dadosPanel.add(dado1Label);
        dadosPanel.add(dado2Label);
        
        JPanel resultadoPanel = new JPanel(new BorderLayout());
        resultadoPanel.add(resultadoLabel, BorderLayout.CENTER);
        resultadoPanel.add(lanzarButton, BorderLayout.SOUTH);
        
        // Agregar componentes al marco
        add(dadosPanel, BorderLayout.CENTER);
        add(resultadoPanel, BorderLayout.SOUTH);
        
        // Configurar el marco
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        // Deshabilitar el botón mientras se realiza la animación
        lanzarButton.setEnabled(false);
        
        // Reproducir sonido de lanzar dados
        reproducirSonido(sonidoLanzarDados);
        
        // Detener el Timer existente si está en ejecución
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        
        // Establecer un nuevo Timer para la animación de los dados
        timer = new Timer();
        TimerTask task = new TimerTask() {
            private int contador = 0;
            
            @Override
            public void run() {
                // Lanzar los dados y actualizar las imágenes
                int dado1 = random.nextInt(6) + 1;
                int dado2 = random.nextInt(6) + 1;
                dado1Label.setIcon(imagenesDados[dado1 - 1]);
                dado2Label.setIcon(imagenesDados[dado2 - 1]);
                contador++;
                
                // Detener la animación después de cierto número de iteraciones
                if (contador == 10) {
                    timer.cancel();
                    timer.purge();
                    
                    // Mostrar el resultado final
                    int resultado = dado1 + dado2;
                    resultadoLabel.setText("Resultado: " + resultado);
                    
                    // Habilitar el botón después de mostrar el resultado
                    lanzarButton.setEnabled(true);
                }
            }
        };
        
        // Programar el TimerTask para que se ejecute cada 100 ms
        timer.scheduleAtFixedRate(task, 100, 100);
    }
    
    private void cargarSonido(String archivoSonido) {
        try {
            File archivo = new File(archivoSonido);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            sonidoLanzarDados = AudioSystem.getClip();
            sonidoLanzarDados.open(audioStream);
        } catch (Exception e) {
            System.out.println("Error al cargar el sonido: " + e.getMessage());
        }
    }
    
    private void reproducirSonido(Clip clip) {
        try {
            if (clip != null) {
                clip.setFramePosition(0);
                clip.start();
            }
        } catch (Exception e) {
            System.out.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new JuegoDados();
    }
}
