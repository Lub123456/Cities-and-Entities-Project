import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class EntitiesGUI extends JFrame {
    private ArrayList<NamedEntity> ALE = new ArrayList<>();
    private int currentIndex = 0;

    private JTextField tfNom, tfGen, tfAcc, tfDat, tfGender, tfCityRef, tfType;

    public EntitiesGUI() {
        setTitle("Entity Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLayout(new BorderLayout());

        loadEntitiesFromFile("data/namedEntities.txt");

        JPanel panel = new JPanel(new GridLayout(8, 2));

        // Creation of labels and fields
        panel.add(new JLabel("  Nominative:"));
        tfNom = new JTextField(); tfNom.setEditable(false); panel.add(tfNom);

        panel.add(new JLabel("  Genitive:"));
        tfGen = new JTextField(); tfGen.setEditable(false); panel.add(tfGen);

        panel.add(new JLabel("  Accusative:"));
        tfAcc = new JTextField(); tfAcc.setEditable(false); panel.add(tfAcc);

        panel.add(new JLabel("  Dative:"));
        tfDat = new JTextField(); tfDat.setEditable(false); panel.add(tfDat);

        panel.add(new JLabel("  Gender:"));
        tfGender = new JTextField(); tfGender.setEditable(false); panel.add(tfGender);

        panel.add(new JLabel("  City reference:"));
        tfCityRef = new JTextField(); tfCityRef.setEditable(false); panel.add(tfCityRef);

        panel.add(new JLabel("  Entity Type:"));
        tfType = new JTextField(); tfType.setEditable(false); panel.add(tfType);
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
        btnFirst.addActionListener(e -> showEntity(0));
        btnPrev.addActionListener(e -> {
            if (currentIndex > 0) showEntity(currentIndex - 1);
        });
        btnNext.addActionListener(e -> {
            if (currentIndex < ALE.size() - 1) showEntity(currentIndex + 1);
        });
        btnLast.addActionListener(e -> showEntity(ALE.size() - 1));

        // Initial display of the first entity
        if (!ALE.isEmpty()) {
            showEntity(0);
        }

        setVisible(true);
    }

    private void loadEntitiesFromFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ALE.add(new NamedEntity(line));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading namedEntities.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showEntity(int index) {
        if (index >= 0 && index < ALE.size()) {
            NamedEntity entity = ALE.get(index);
            currentIndex = index;

            tfNom.setText(entity.nom);
            tfGen.setText(entity.gen);
            tfAcc.setText(entity.acc);
            tfDat.setText(entity.dat);
            tfGender.setText(entity.gender);
            tfCityRef.setText(String.valueOf(entity.cityRef));
            tfType.setText(entity.entityType);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EntitiesGUI::new);
    }
}
