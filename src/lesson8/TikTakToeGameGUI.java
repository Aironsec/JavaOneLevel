package lesson8;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TikTakToeGameGUI extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int sizeWidth = 500;
    public int sizeHeight = 400;
    public int locationX = (screenSize.width - sizeWidth) / 2;
    public int locationY = (screenSize.height - sizeHeight) / 2;

    private final ImageIcon imageX = new ImageIcon("src/lesson8/x.jpg");
    private final ImageIcon imageO = new ImageIcon("src/lesson8/o.jpg");
    private final ImageIcon imageEmpty = new ImageIcon("src/lesson8/empty.jpg");
    private int sizeMap = 3;
    JPanel panelMap;
    JButton[][] buttonsDot;
    JLabel labelTurn;
    JCheckBox checkBox;

    public TikTakToeGameGUI() {
        super("Игра в крестики и нолики");
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new PanelWindow("Ход");
        labelTurn = new JLabel("<html><FONT SIZE=\"+2\" COLOR=\"green\"> Выберите размер поля и начните игру</FONT>");
        panel.add(labelTurn);
        add(panel, BorderLayout.PAGE_START);

        panel = new PanelWindow("Настройки", new Dimension(150, 0));
        JLabel label = new JLabel("Размер поля");
        panel.add(label);
        SpinnerModel numbers = new SpinnerNumberModel(sizeMap, 3, 10, 1);
        JSpinner spinner = new JSpinner(numbers);
        panel.add(spinner);
        checkBox = new JCheckBox("Посложнее");
        panel.add(checkBox);
        JButton okGame = new JButton("Начать игру");
        okGame.setPreferredSize(new Dimension(110, 30));
        okGame.addActionListener(okGameGo -> {
            sizeMap = (int) spinner.getValue();
            spinner.setEnabled(false);
            okGame.setEnabled(false);
            checkBox.setEnabled(false);
            labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"blue\">Ходите</FONT>");
            panelMap.setLayout(new GridLayout(sizeMap, sizeMap));
            buttonsDot = new JButton[sizeMap][sizeMap];
            for (int i = 0; i < sizeMap; i++) {
                for (int j = 0; j < sizeMap; j++) {
                    buttonsDot[i][j] = new JButton();
                    buttonsDot[i][j].setIcon(imageEmpty);
                    JButton copyButtonDot = buttonsDot[i][j];
                    copyButtonDot.addActionListener(buttonsDotGo -> {
                        copyButtonDot.setEnabled(false);
                        copyButtonDot.setDisabledIcon(imageX);
                        if (checkWin()) return;
                        if (isFullMap()) return;
                        aiTurn();
                        if (checkWin()) return;
                        if (isFullMap()) return;

                    });

                    panelMap.add(buttonsDot[i][j]);
                }

            }
        });
        panel.add(okGame);
        JButton cancelGame = new JButton("Сбросить");
        cancelGame.setPreferredSize(new Dimension(110, 30));
        cancelGame.addActionListener(cancelGameGo -> {
            panelMap.removeAll();
            this.repaint();
            sizeMap = 3;
            spinner.setEnabled(true);
            okGame.setEnabled(true);
            checkBox.setEnabled(true);
            aiRun = false;
            spinner.setValue(3);

            labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"green\"> Выберите размер поля и начните игру</FONT>");

        });
        panel.add(cancelGame);

        JButton closeGame = new JButton("Закрыть");
        closeGame.setPreferredSize(new Dimension(110, 30));
        closeGame.addActionListener(close -> {
            dispose();
        });
        panel.add(closeGame);
        add(panel, BorderLayout.LINE_END);
//        setResizable(false);
        panelMap = new PanelWindow("Поле");
        add(panelMap, BorderLayout.CENTER);
    }

    private int aiBaseTurnX;
    private int aiBaseTurnY;
    private boolean aiRun = false;
    private boolean flagFinishHorizontal = false;
    private boolean flagFinishVertical = false;
    private boolean flagFinishDiagonal = false;
    private boolean flagFinishReversDiagonal = false;
    private boolean flagBlockHorizontal = false;
    private boolean flagBlockVertical = false;
    private boolean flagBlockDiagonal = false;
    private boolean flagBlockReversDiagonal = false;
    private int[] finishTurn;
    private int[] blockTurn;
    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;

    public void aiTurn() {
        Random rand = new Random();
        if (flagFinishHorizontal) {
            flagFinishHorizontal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[finishTurn[HORIZONTAL]][i].isEnabled()) {
                    buttonsDot[finishTurn[HORIZONTAL]][i].setEnabled(false);
                    buttonsDot[finishTurn[HORIZONTAL]][i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagFinishVertical) {
            flagFinishVertical = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][finishTurn[VERTICAL]].isEnabled()) {
                    buttonsDot[i][finishTurn[VERTICAL]].setEnabled(false);
                    buttonsDot[i][finishTurn[VERTICAL]].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagFinishDiagonal) {
            flagFinishDiagonal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][i].isEnabled()) {
                    buttonsDot[i][i].setEnabled(false);
                    buttonsDot[i][i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagFinishReversDiagonal) {
            flagFinishReversDiagonal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][sizeMap - 1 - i].isEnabled()) {
                    buttonsDot[i][sizeMap - 1 - i].setEnabled(false);
                    buttonsDot[i][sizeMap - 1 - i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagBlockHorizontal) {
            flagBlockHorizontal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[blockTurn[HORIZONTAL]][i].isEnabled()) {
                    buttonsDot[blockTurn[HORIZONTAL]][i].setEnabled(false);
                    buttonsDot[blockTurn[HORIZONTAL]][i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagBlockVertical) {
            flagBlockVertical = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][blockTurn[VERTICAL]].isEnabled()) {
                    buttonsDot[i][blockTurn[VERTICAL]].setEnabled(false);
                    buttonsDot[i][blockTurn[VERTICAL]].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagBlockDiagonal) {
            flagBlockDiagonal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][i].isEnabled()) {
                    buttonsDot[i][i].setEnabled(false);
                    buttonsDot[i][i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else if (flagBlockReversDiagonal) {
            flagBlockReversDiagonal = false;
            for (int i = 0; i < sizeMap; i++) {
                if (buttonsDot[i][sizeMap - 1 - i].isEnabled()) {
                    buttonsDot[i][sizeMap - 1 - i].setEnabled(false);
                    buttonsDot[i][sizeMap - 1 - i].setDisabledIcon(imageO);
                    return;
                }
            }
        } else
            if (!aiRun) {
                aiRun = true;
                if (!strongTurn()) {
                    int x, y;
                    do {
                        x = rand.nextInt(sizeMap);
                        y = rand.nextInt(sizeMap);
                    } while (!buttonsDot[y][x].isEnabled());
                    buttonsDot[y][x].setEnabled(false);
                    buttonsDot[y][x].setDisabledIcon(imageO);
                    aiBaseTurnX = x;
                    aiBaseTurnY = y;
                }
            } else {
                int x, y;
                int randTurn = 0;
                do {
                    randTurn++;
                    do {
                        x = rand.nextInt(sizeMap);
                        y = rand.nextInt(sizeMap);
                    } while ((aiBaseTurnY == y && aiBaseTurnX == x) || (aiBaseTurnY != y && aiBaseTurnX != x));

                } while ((!buttonsDot[y][x].isEnabled() && !(randTurn > 1000)));
                if (randTurn > 1000) {
                    aiRun = false;
                    aiTurn();
                }
                buttonsDot[y][x].setEnabled(false);
                buttonsDot[y][x].setDisabledIcon(imageO);
            }
    }

    private boolean strongTurn() {
        String[] tmpMas = {"00", "0" + (sizeMap - 1), (sizeMap - 1) + "0", "" + (sizeMap - 1) + (sizeMap - 1)};
        if (checkBox.isSelected()) {
            if (sizeMap % 2 != 0) {
                if (buttonsDot[sizeMap / 2][sizeMap / 2].isEnabled()) {
                    buttonsDot[sizeMap / 2][sizeMap / 2].setEnabled(false);
                    buttonsDot[sizeMap / 2][sizeMap / 2].setDisabledIcon(imageO);
                    return true;
                }
            }
            for (int i = 0; i < 4; i++) {
                int x = Integer.parseInt(String.valueOf(tmpMas[i].charAt(0)));
                int y = Integer.parseInt(String.valueOf(tmpMas[i].charAt(1)));
                if (buttonsDot[y][x].isEnabled()) {
                    buttonsDot[y][x].setEnabled(false);
                    buttonsDot[y][x].setDisabledIcon(imageO);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWin() {
        flagFinishHorizontal = false;
        flagFinishVertical = false;
        flagFinishDiagonal = false;
        flagFinishReversDiagonal = false;
        flagBlockHorizontal = false;
        flagBlockVertical = false;
        flagBlockDiagonal = false;
        flagBlockReversDiagonal = false;
        int countTurn = sizeMap * 2 + 2;
        int dotWinX = 0;
        int dotWinO = 0;
        finishTurn = new int[2];
        blockTurn = new int[2];
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                if (buttonsDot[i][j].getDisabledIcon() == imageX) dotWinX++;
                else if (buttonsDot[i][j].getDisabledIcon() == imageO) dotWinO++;
            }
            if (dotWinX == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"blue\">Игра окончена. Победил человек!</FONT>");
                return true;
            }
            if (dotWinO == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"red\">Игра окончена. Победил комп!</FONT>");
                return true;
            }
            if (dotWinX - dotWinO == sizeMap - 1) {
                flagBlockHorizontal = true;
                blockTurn[HORIZONTAL] = i;
            } else if (dotWinO - dotWinX == sizeMap - 1) {
                flagFinishHorizontal = true;
                finishTurn[HORIZONTAL] = i;
            }
            if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
            if ((dotWinX == 0)) {
                aiBaseTurnX = i;
            }
            dotWinX = 0;
            dotWinO = 0;
        }

        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                if (buttonsDot[j][i].getDisabledIcon() == imageX) dotWinX++;
                else if (buttonsDot[j][i].getDisabledIcon() == imageO) dotWinO++;
            }
            if (dotWinX == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"blue\">Игра окончена. Победил человек!</FONT>");
                return true;
            }
            if (dotWinO == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"red\">Игра окончена. Победил комп!</FONT>");
                return true;
            }
            if (dotWinX - dotWinO == sizeMap - 1) {
                flagBlockVertical = true;
                blockTurn[VERTICAL] = i;
            } else if (dotWinO - dotWinX == sizeMap - 1) {
                flagFinishVertical = true;
                finishTurn[VERTICAL] = i;
            }
            if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
            if ((dotWinX == 0)) {
                aiBaseTurnY = i;
            }
            dotWinX = 0;
            dotWinO = 0;
        }

        for (int i = 0; i < sizeMap; i++) {
            if (buttonsDot[i][i].getDisabledIcon() == imageX) dotWinX++;
            else if (buttonsDot[i][i].getDisabledIcon() == imageO) dotWinO++;

            if (dotWinX == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"blue\">Игра окончена. Победил человек!</FONT>");
                return true;
            }
            if (dotWinO == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"red\">Игра окончена. Победил комп!</FONT>");
                return true;
            }
        }
        if (dotWinX - dotWinO == sizeMap - 1) {
            flagBlockDiagonal = true;
        } else if (dotWinO - dotWinX == sizeMap - 1) {
            flagFinishDiagonal = true;
        }
        if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
        dotWinX = 0;
        dotWinO = 0;
        for (int i = 0; i < sizeMap; i++) {
            if (buttonsDot[i][sizeMap - 1 - i].getDisabledIcon() == imageX) dotWinX++;
            else if (buttonsDot[i][sizeMap - 1 - i].getDisabledIcon() == imageO) dotWinO++;

            if (dotWinX == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"blue\">Игра окончена. Победил человек!</FONT>");
                return true;
            }
            if (dotWinO == sizeMap) {
                labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"red\">Игра окончена. Победил комп!</FONT>");
                return true;
            }
        }
        if (dotWinX - dotWinO == sizeMap - 1) {
            flagBlockReversDiagonal = true;
        } else if (dotWinO - dotWinX == sizeMap - 1) {
            flagFinishReversDiagonal = true;
        }
        if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
        if (countTurn <= 0) {
            labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"gray\">Игра окончена. Ничья!</FONT>");
            return true;
        }
        return false;
    }

    private boolean isFullMap() {
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                if (buttonsDot[i][j].isEnabled()) return false;
            }
        }
        labelTurn.setText("<html><FONT SIZE=\"+2\" COLOR=\"gray\">Игра окончина. Ничья!</FONT>");
        return true;
    }

}
