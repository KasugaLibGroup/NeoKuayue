package willow.train.kuayue.item.animate_controller.code_cube.function;

import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.logic.EvaluationCube;

public class FunctionYieldCube extends EvaluationCube implements ReturnCube {
    public FunctionYieldCube(Namespace namespace, String formula) {
        super("return_value", namespace, formula);
    }

    @Override
    public boolean isYield() {
        return true;
    }
}
