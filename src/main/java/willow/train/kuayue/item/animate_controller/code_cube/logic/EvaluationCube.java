package willow.train.kuayue.item.animate_controller.code_cube.logic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import kasuga.lib.vendor_modules.interpreter.compute.data.Variable;
import kasuga.lib.vendor_modules.interpreter.compute.infrastructure.Formula;
import willow.train.kuayue.item.animate_controller.code_cube.Evalutaionable;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 赋值语句
public class EvaluationCube implements LogicalCode {
    private String name;
    private final Namespace namespace;
    private Formula formula;
    private final List<LogicalCode> next;

    public EvaluationCube(String name, Namespace namespace, String formula) {
        this.name = name;
        this.namespace = namespace;
        this.formula = namespace.decodeFormula(formula);
        Variable variable = new Variable(name, namespace, 0f);
        namespace.registerInstance(name, variable);
        next = Lists.newArrayList();
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeFormula(formula);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Variable variable = new Variable(name, namespace, 0f);
        namespace.registerInstance(name, variable);
    }

    public Formula getFormula() {
        return formula;
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
        float result = formula.getResult();
        namespace.assign(name, result);
        return result;
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new EvaluationCube(this.name, namespace, this.formula.getString());
    }
}
