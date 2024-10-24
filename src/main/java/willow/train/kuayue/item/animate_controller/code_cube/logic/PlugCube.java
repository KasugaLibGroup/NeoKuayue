package willow.train.kuayue.item.animate_controller.code_cube.logic;

import com.google.common.collect.Lists;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;

import java.util.List;

// 空体
public class PlugCube implements LogicalCode {

    public PlugCube() {}

    @Override
    public List<LogicalCode> getNext() {
        return List.of();
    }

    @Override
    public boolean readOnly() {
        return false;
    }

    @Override
    public boolean writeOnly() {
        return true;
    }

    @Override
    public float getData() {
        return 0;
    }

    @Override
    public PlugCube copy(Namespace namespace) {
        return new PlugCube();
    }
}
