package willow.train.kuayue.item.animate_controller.code_cube.function;

import com.google.common.collect.Lists;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalSwitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FunctionCube implements LogicalCode {
    private final FunctionInputCube func;
    private final List<LogicalCode> stack;
    private final List<LogicalCode> next;
    public FunctionCube(FunctionInputCube cube) {
        this.func = cube;
        stack = new ArrayList<>();
        this.next = Lists.newArrayList();
        stack.add(cube);
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    @Override
    public boolean readOnly() {
        return false;
    }

    @Override
    public boolean writeOnly() {
        return false;
    }

    @Override
    public float getData() {
        return goToNearestReturn();
    }

    public void reset() {
        this.stack.clear();
        this.stack.add(this.func);
    }

    public float goToNearestReturn() {
        ReturnCube retValue;
        do {
            step();
            retValue = hasReturnValue();
        } while (!stack.isEmpty() && retValue == null);
        if (retValue != null && !retValue.isYield()) {this.reset();}
        return retValue == null ? 0f : retValue.getData();
    }

    public ReturnCube hasReturnValue() {
        for (LogicalCode code : stack) {
            if (code instanceof ReturnCube r) return r;
        }
        return null;
    }

    public void step() {
        if (stack.isEmpty()) return;
        ArrayList<LogicalCode> result = new ArrayList<>();
        for (LogicalCode code : stack) {
            if (code instanceof LogicalSwitch logicalSwitch) {
                result.addAll(logicalSwitch.getCorrectNext());
            } else {
                code.getData();
                result.addAll(code.getNext());
            }
        }
        stack.clear();
        stack.addAll(result);
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new FunctionCube((FunctionInputCube) func.copy(namespace));
    }
}
