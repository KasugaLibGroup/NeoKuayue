package willow.train.kuayue.item.animate_controller.code_cube.function;

import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.logic.EvaluationCube;

public class FunctionReturnCube extends EvaluationCube implements ReturnCube {
    public FunctionReturnCube(Namespace namespace, String formula) {
        super("return_value", namespace, formula);
    }

    @Override
    public boolean isYield() {
        return false;
    }
}
