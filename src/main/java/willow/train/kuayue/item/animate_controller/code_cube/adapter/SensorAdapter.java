package willow.train.kuayue.item.animate_controller.code_cube.adapter;

import com.google.common.collect.Lists;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.sensor.AnimateSensor;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter implements LogicalCode {

    public final AnimateSensor sensor;
    private final List<LogicalCode> next;
    public SensorAdapter(AnimateSensor sensor) {
        this.sensor = sensor;
        this.next = Lists.newArrayList();
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    @Override
    public boolean readOnly() {
        return true;
    }

    @Override
    public boolean writeOnly() {
        return false;
    }

    @Override
    public float getData() {
        return sensor.getOutput();
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new SensorAdapter(sensor);
    }
}
