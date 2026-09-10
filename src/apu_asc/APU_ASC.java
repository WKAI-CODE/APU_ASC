package apu_asc;

import gui.LoginGUI;

public class APU_ASC {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginGUI();
        });
    }
}