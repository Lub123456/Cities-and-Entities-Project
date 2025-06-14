import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class CitiesGUI extends JFrame {
    private ArrayList<City> ALC = new ArrayList<>();
    private int currentIndex = 0;

    private JTextField tfRef, tfGen, tfDat, tfEnName, tfRegion, tfDepartment;

    public CitiesGUI() {
        setTitle("City Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLayout(new BorderLayout());

        loadCitiesFromFile("data/cities.txt");

        JPanel panel = new JPanel(new GridLayout(7, 2));

        // Creation of labels and fields
        panel.add(new JLabel("Reference:"));
        tfRef = new JTextField(); tfRef.setEditable(false); panel.add(tfRef);

        panel.add(new JLabel("Genitive:"));
        tfGen = new JTextField(); tfGen.setEditable(false); panel.add(tfGen);

        panel.add(new JLabel("Dative:"));
        tfDat = new JTextField(); tfDat.setEditable(false); panel.add(tfDat);

        panel.add(new JLabel("English name:"));
        tfEnName = new JTextField(); tfEnName.setEditable(false); panel.add(tfEnName);

        panel.add(new JLabel("Region:"));
        tfRegion = new JTextField(); tfRegion.setEditable(false); panel.add(tfRegion);

        panel.add(new JLabel("Department:"));
        tfDepartment = new JTextField(); tfDepartment.setEditable(false); panel.add(tfDepartment);
        add(panel, BorderLayout.CENTER);

        // Navigation buttons
        JPanel navPanel = new JPanel();
        JButton btnFirst = new JButton("First");
        JButton btnPrev = new JButton("Previous");
        JButton btnNext = new JButton("Next");
        JButton btnLast = new JButton("Last");

        navPanel.add(btnFirst);
        navPanel.add(btnPrev);
        navPanel.add(btnNext);
        navPanel.add(btnLast);
        add(navPanel, BorderLayout.SOUTH);

        // Button actions
        btnFirst.addActionListener(e -> showCity(0));
        btnPrev.addActionListener(e -> {
            if (currentIndex > 0) showCity(currentIndex - 1);
        });
        btnNext.addActionListener(e -> {
            if (currentIndex < ALC.size() - 1) showCity(currentIndex + 1);
        });
        btnLast.addActionListener(e -> showCity(ALC.size() - 1));

        // Initial display of the first city
        if (!ALC.isEmpty()) {
            showCity(0);
        }

        setVisible(true);
    }

    private void loadCitiesFromFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ALC.add(new City(line));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading cities.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showCity(int index) {
        if (index >= 0 && index < ALC.size()) {
            City city = ALC.get(index);
            currentIndex = index;

            tfRef.setText(String.valueOf(city.ref));
            tfGen.setText(city.gen);
            tfDat.setText(city.dat);
            tfEnName.setText(city.enName);
            tfRegion.setText(city.region);
            tfDepartment.setText(city.department);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CitiesGUI::new);
    }
}
