package h04.selection;

import fopbot.*;

import java.awt.*;
import static java.lang.Math.floorMod;
public class KeyboardFieldSelector implements FieldSelector, KeyPressListener {
    private FieldSelectionListener listener;
    private Field lastField;

    public KeyboardFieldSelector() {
        World.addKeyPressListener(this);
    }

    @Override
    public void setFieldSelectionListener(FieldSelectionListener fieldSelectionListener) {
        this.listener = listener;
    }

    public void onKeyPress(KeyPressEvent event) {
        var world = event.getWorld();
        var key = event.getKey();
        if (lastField == null) {
            lastField = world.getField(0,0);
            lastField.setFieldColor(Color.RED);
            return;
        }
        if (key != Key.LEFT && key != Key.RIGHT && key != Key.UP && key != Key.DOWN && key != Key.SPACE) {
            return;
        }
        int dX = key == Key.LEFT ? -1 : key == Key.RIGHT ? 1 : 0;
        int dY = key == Key.UP ? 1 : key == Key.DOWN ? -1 : 0;

        int w = world.getWidth();
        int h = world.getHeight();
        Field field = world.getField(floorMod(lastField.getX()+dX,w), floorMod(lastField.getY()+dY,h));
        lastField.setFieldColor(null);
        field.setFieldColor(Color.RED);
        if (lastField == field) {
            listener.onFieldSelection(field);
        }
        lastField = field;
    }
}
