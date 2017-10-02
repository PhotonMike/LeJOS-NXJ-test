import lejos.nxt.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> menu = new ArrayList<>();
    private static int selection = 0;
    private static Button left = Button.LEFT, right = Button.RIGHT, enter = Button.ENTER;

    public static void main(String[] args){
        menu.add("Motor teszt");
        drawMenu();
        left.addButtonListener(new ButtonListener() {
            @Override
            public void buttonPressed(Button button) {
                if (selection == 0){
                    selection = menu.size();
                } else {
                    selection--;
                }
                drawMenu();
            }
            @Override
            public void buttonReleased(Button button) {
                Sound.beep();
            }
        });
        right.addButtonListener(new ButtonListener() {
            @Override
            public void buttonPressed(Button button) {
                if (selection == menu.size()){
                    selection = 0;
                } else {
                    selection++;
                }
                drawMenu();
            }
            @Override
            public void buttonReleased(Button button) {
                Sound.beep();
            }
        });
        enter.addButtonListener(new ButtonListener() {
            @Override
            public void buttonPressed(Button button) {
                LCD.clear();
                Sound.beep();
                Sound.beep();
            }

            @Override
            public void buttonReleased(Button button) {
                if (menu.get(selection).equals("Motor teszt"))
                    testMotors();
                drawMenu();
            }
        });
    }
    private static void drawMenu(){
        LCD.clear();
        LCD.drawString("Lehetőségek:", 0,0);
        LCD.drawString((selection+1)+menu.get(selection),3,0);
    }
    private static void testMotors(){
        LCD.clear();
        LCD.drawString("A és B motorok",0,0);
        LCD.drawString("Előre pörgetése gombnyomásig",0,0);
        try {
            Motor.A.forward();
            Motor.B.forward();
        } catch (Exception e) {
            LCD.drawString("Hiba",3,0);
        }
        Button.waitForAnyPress();
        Motor.A.stop();
        Motor.B.stop();
    }
}
