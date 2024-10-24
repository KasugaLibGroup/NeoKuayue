package willow.train.kuayue.item.animate_controller.code_cube;

import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;

import java.util.List;

public interface LogicalCode {
    List<LogicalCode> getNext();
    default void addNext(LogicalCode logicalCode) {
        if (writeOnly()) return;
        if (logicalCode.readOnly()) return;
        getNext().add(logicalCode);
    }

    default void setNext(int index, LogicalCode logicalCode) {
        if (writeOnly()) return;
        if (logicalCode.readOnly()) return;
        if (getNext().size() > index) {
            getNext().set(index, logicalCode);
        } else {
            addNext(logicalCode);
        }
    }

    default LogicalCode removeNext(int index) {
        if (writeOnly()) return null;
        if (getNext().size() <= index) return null;
        return getNext().remove(index);
    }

    default boolean removeNext(LogicalCode object) {
        if (writeOnly()) return false;
        return getNext().remove(object);
    }

    boolean readOnly();
    boolean writeOnly();
    float getData();
    LogicalCode copy(Namespace namespace);
}
