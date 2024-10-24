package willow.train.kuayue.item.animate_controller.code_cube;

import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;

import java.util.HashMap;
import java.util.Map;

public interface Evalutaionable extends LogicalCode {
    HashMap<LogicalCode, String> getVarMapping();
    Namespace getNamespace();

    default void rename(String prevName, String neoName) {
        if (!getVarMapping().containsValue(prevName)) return;
        Map.Entry<LogicalCode, String> entry = null;
        for (Map.Entry<LogicalCode, String> entryItem : getVarMapping().entrySet()) {
            if (entryItem.getValue().equals(prevName)) {
                entry = entryItem;
                break;
            }
        }
        if (entry == null) return;
        getVarMapping().remove(entry.getKey());
        getVarMapping().put(entry.getKey(), neoName);
    }

    default void assign() {
        for (Map.Entry<LogicalCode, String> entry : getVarMapping().entrySet()) {
            getNamespace().assign(entry.getValue(), entry.getKey().getData());
        }
    }
}
