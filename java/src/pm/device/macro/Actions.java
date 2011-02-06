package pm.device.macro;

import java.util.ArrayList;

import wiitunes.interaction.macro.Action;
import wiitunes.interaction.macro.Button;
import wiitunes.interaction.macro.Buttons;
import wiitunes.interaction.macro.Combination;
import wiitunes.interaction.macro.action.Hold;
import wiitunes.interaction.macro.action.Press;
import wiitunes.interaction.macro.action.Release;

public class Actions {
    protected Combination[] combinationArray;

    public Actions(Action... actionArray) {
        ArrayList<Combination> combinationList = new ArrayList<Combination>();
        Buttons heldButtons = new Buttons();
        for (Action action : actionArray) {
            if (action instanceof Press) {
                Button button = ((Press) action).button;
                Buttons buttons = new Buttons(button);
                combinationList.add(new Combination(buttons, null, heldButtons));
                combinationList.add(new Combination(null, buttons, heldButtons));
            } else if (action instanceof Release) {
                Button button = ((Release) action).button;
                Buttons buttons = new Buttons(button);
                heldButtons = heldButtons.clone();
                heldButtons.remove(button);
                combinationList.add(new Combination(null, buttons, heldButtons));
            } else if (action instanceof Hold) {
                Button button = ((Hold) action).button;
                Buttons buttons = new Buttons(button);
                combinationList.add(new Combination(buttons, null, heldButtons));
                heldButtons = heldButtons.clone();
                heldButtons.add(button);
            }
        }
        combinationArray = (Combination[]) combinationList.toArray(new Combination[0]);
    }

    public Combination get(int step) {
        return combinationArray[step];
    }

    public int count() {
        return combinationArray.length;
    }

    public String toString() {
        String string = String.format("Gesture contains %d actions:\n", combinationArray.length);;
        for (Combination combination : combinationArray) {
            string += String.format("> %s\n", combination);
        }
        return string;
    }
}