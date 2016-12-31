package model.tasks.robot;

/**
 * Created by acer on 20.09.2015.
 */
import java.awt.*;
import java.util.ArrayList;

public class Field {
    private int x0, y0;
    private int width, height;
    static final int SQUARE_SIZE = 85;
    private ArrayList<Integer> Way_X = new ArrayList<Integer>();
    private ArrayList<Integer> Way_Y = new ArrayList<Integer>();
    private int[] necessary_row;
    private int[] necessary_col;
    private int[] border_row;
    private int[] border_col;
    private int robot_x, robot_y;

    public Field (int x0, int y0, int width, int h) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = h;
    }

    public void Draw (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        int x = x0;
        int y = y0;
        int border = 15;
        g2.setColor(new Color(139, 9, 12));
        g2.fillRect(x0 - border, y0 - border, width * SQUARE_SIZE + border * 2, height * SQUARE_SIZE + border * 2);
        g2.setStroke(new BasicStroke(2));

        //������ ������ ����
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g2.setColor(new Color(250, 250, 184));
                g2.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g2.setColor(new Color(139, 9, 12));
                g2.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                x += SQUARE_SIZE;
            }
            y += SQUARE_SIZE;
            x = x0;

        }

        PaintCells(g2);
        g2.setColor(new Color(255, 163, 2)); //��������� ��������� ������ ������
        g2.fillRect(robot_x, robot_y, SQUARE_SIZE, SQUARE_SIZE);
        g2.setColor(new Color(139, 9, 12));
        g2.drawRect(robot_x, robot_y, SQUARE_SIZE, SQUARE_SIZE);

        g2.setColor(Color.BLACK);
        for (int i = 1; i < Way_X.size(); i += 2) {
            g2.drawLine(Way_X.get(i - 1), Way_Y.get(i - 1), Way_X.get(i), Way_Y.get(i));
        }


    }

    //���������� ���������� ��������
    /*public void DrawWay(String command) {
        if (command.equals(GODOWN)) {
            Way_X.create(Way_X.get(Way_X.size() - 1));
            Way_Y.create(Way_Y.get(Way_Y.size() - 1) + 5);
        }
        if (command.equals(GORIGHT)) {
            Way_X.create(Way_X.get(Way_X.size() - 1) + 5);
            Way_Y.create(Way_Y.get(Way_Y.size() - 1));
        }
        if (command.equals(GOUP)) {
            Way_X.create(Way_X.get(Way_X.size() - 1));
            Way_Y.create(Way_Y.get(Way_Y.size() - 1) - 5);
        }
        if (command.equals(GOLEFT)) {
            Way_X.create(Way_X.get(Way_X.size() - 1) - 5);
            Way_Y.create(Way_Y.get(Way_Y.size() - 1));
        }
        if (command.equals(JUMPDOWN)) {
            Way_X.create(Way_X.get(Way_X.size() - 1));
            Way_Y.create(Way_Y.get(Way_Y.size() - 1) + Field.SQUARE_SIZE * 2);
        }
        if (command.equals(JUMPRIGHT)) {
            Way_X.create(Way_X.get(Way_X.size() - 1) + Field.SQUARE_SIZE * 2);
            Way_Y.create(Way_Y.get(Way_Y.size() - 1));
        }
        if (command.equals(JUMPUP)) {
            Way_X.create(Way_X.get(Way_X.size() - 1));
            Way_Y.create(Way_Y.get(Way_Y.size() - 1) - SQUARE_SIZE * 2);
        }
        if (command.equals(JUMPLEFT)) {
            Way_X.create(Way_X.get(Way_X.size() - 1) - SQUARE_SIZE * 2);
            Way_Y.create(Way_Y.get(Way_Y.size() - 1));
        }
    }*/

    public void ClearWay () {
        Way_X.clear();
        Way_Y.clear();
        Way_X.add(robot_x + SQUARE_SIZE / 2);
        Way_Y.add(robot_y + SQUARE_SIZE / 2);
    }

    //��������� ������ ��� �������
    private void PaintCells (Graphics g) {
        int x, y;
        for (int i = 0; i < necessary_row.length; i++) {
            x = x0 + necessary_col[i] * SQUARE_SIZE;
            y = y0 + necessary_row[i] * SQUARE_SIZE;
            g.setColor(new Color(255, 255, 0));
            g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            g.setColor(new Color(139, 9, 12)/*new Color(255, 163, 2)*/);
            g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        }

        if (border_row != null)
            for (int i = 0; i < border_row.length; i++) {
                x = x0 + border_col[i] * SQUARE_SIZE;
                y = y0 + border_row[i] * SQUARE_SIZE;
                g.setColor(new Color(0, 0, 0));
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.setColor(new Color(139, 9, 12));
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
    }

    //���������� ������, ������� ���������� ������
    public void setNecessaryRowCol (int[] row, int[] col) {
        necessary_row = new int[row.length];
        necessary_col = new int[col.length];
        for (int i = 0; i < row.length; i++) {
            necessary_row[i] = row[i];
            necessary_col[i] = col[i];
        }

    }

    //��������� ������-������� (������ ��� �������� ����������)
    public void setBorderRowCol (int[] row, int[] col) {
        border_row = new int[row.length];
        border_col = new int[col.length];
        for (int i = 0; i < row.length; i++) {
            border_row[i] = row[i];
            border_col[i] = col[i];
        }

    }

    //���������, ��� �� ������ � ������� ��������
    public boolean AllCrossedCells () {
        int k = 0;
        for (int i = 0; i < necessary_row.length; i++) {
            for (int j = 0; j < Way_X.size(); j++)
                if (Way_X.get(j) == (x0 + necessary_col[i] * SQUARE_SIZE + SQUARE_SIZE / 2) && Way_Y.get(j) == (y0 + necessary_row[i] * SQUARE_SIZE + SQUARE_SIZE / 2))
                k++;
        }

        if (k >= necessary_row.length)
            return true;
        else
            return false;
    }



}

