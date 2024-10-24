package willow.train.kuayue.item.animate_controller.code_cube.logic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import kasuga.lib.vendor_modules.interpreter.compute.data.Variable;
import kasuga.lib.vendor_modules.interpreter.logic.infrastructure.LogicalData;
import willow.train.kuayue.item.animate_controller.code_cube.Evalutaionable;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalSwitch;

import java.util.HashMap;
import java.util.List;

// 分支语句(if)
public class SwitchStructureCube implements LogicalCode, LogicalSwitch {
    private final List<LogicalCode> next;
    private final Namespace namespace;
    private LogicalData formula;
    private String varName;
    public SwitchStructureCube(Namespace namespace, String formula, String varName) {
        this.next = Lists.newArrayList();
        this.namespace = namespace;
        this.formula = namespace.decodeLogical(formula);
        this.namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
        this.varName = varName;
    }

    public void initOutput() {
        this.next.clear();
        this.next.add(new PlugCube()); // true
        this.next.add(new PlugCube()); // false
        this.next.add(new PlugCube()); // var_output
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        this.namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeLogical(formula);
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
        float result = formula.getResult() ? 1 : 0;
        namespace.assign(varName, result);
        return result;
    }

    @Override
    public SwitchStructureCube copy(Namespace namespace) {
        return new SwitchStructureCube(namespace, this.formula.toString(), this.varName);
    }

    @Override
    public List<LogicalCode> getCorrectNext() {
        return List.of(formula.getResult() ? next.get(0) : next.get(1));
    }
}
