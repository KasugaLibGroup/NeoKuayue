package willow.train.kuayue.item.animate_controller.code_cube.adapter;

import com.google.common.collect.Lists;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.executor.AnimateExecutor;

import java.util.List;

public class ExecutorAdapter implements LogicalCode {
    public final AnimateExecutor executor;
    public final List<LogicalCode> prev;

    public ExecutorAdapter(AnimateExecutor executor) {
        this.executor = executor;
        this.prev = Lists.newArrayList();
    }

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
    public LogicalCode copy(Namespace namespace) {
        return new ExecutorAdapter(executor);
    }
}
