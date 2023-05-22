import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JButton btnJuegoDados;
    private JButton btnTragamonedas;

    public MenuPrincipal() {
        super("Menú Principal");

        // Crear un JLabel para el fondo
        JLabel fondo = new JLabel(new ImageIcon("fondo.jpg"));
        fondo.setBounds(0, 0, 300, 250); // Ajusta las coordenadas y el tamaño según tus necesidades
        add(fondo);

        JLabel lblTitulo = new JLabel("BIENVENIDO AL CASINO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 10, 300, 30);
        fondo.add(lblTitulo);

        btnJuegoDados = new JButton("Juego de Dados");
        btnTragamonedas = new JButton("Tragamonedas");

        btnJuegoDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JuegoDados juegoDados = new JuegoDados();
                juegoDados.setVisible(true);
            }
        });

        btnTragamonedas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tragamonedas tragamonedas = new Tragamonedas();
                tragamonedas.setVisible(true);
            }
        });

        // Establecer el diseño del contenedor del fondo como nulo
        fondo.setLayout(null);

        btnJuegoDados.setBounds(50, 50, 200, 50);
        btnTragamonedas.setBounds(50, 120, 200, 50);

        // Agregar los botones al fondo en lugar del JFrame
        fondo.add(btnJuegoDados);
        fondo.add(btnTragamonedas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
