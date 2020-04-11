package lesson8;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelWindow extends JPanel {
    Border etched = BorderFactory.createEtchedBorder();
    Border borderTitle;

    public PanelWindow(String title) {
        borderTitle = BorderFactory.createTitledBorder(etched, title);
        this.setBorder(borderTitle);
    }

    public PanelWindow(String title, Dimension dimension) {
        borderTitle = BorderFactory.createTitledBorder(etched, title);
        this.setPreferredSize(dimension);
        this.setBorder(borderTitle);

    }

}
