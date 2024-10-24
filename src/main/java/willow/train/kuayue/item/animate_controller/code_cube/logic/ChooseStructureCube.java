package willow.train.kuayue.item.animate_controller.code_cube.logic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kasuga.lib.core.util.data_type.Pair;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import kasuga.lib.vendor_modules.interpreter.compute.data.Variable;
import kasuga.lib.vendor_modules.interpreter.compute.infrastructure.Formula;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalSwitch;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 增强分之语句(选择语句)
public class ChooseStructureCube implements LogicalCode, LogicalSwitch {
    private final HashMap<LogicalCode, Pair<Float, Float>> ranges;
    private Pair<Float, Float> defaultRange;
    private final Namespace namespace;
    private Formula formula;
    private final List<LogicalCode> logicalNext;
    private String varName;
    public ChooseStructureCube(Namespace namespace, String varName, String formula, Pair<Float, Float> defaultRange) {
        ranges = Maps.newHashMap();
        this.namespace = namespace;
        this.formula = namespace.decodeFormula(formula);
        this.defaultRange = defaultRange;
        logicalNext = Lists.newArrayList();
        namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
        this.varName = varName;
    }

    @Override
    public List<LogicalCode> getNext() {
        ArrayList<LogicalCode> result = new ArrayList<>();
        result.addAll(logicalNext);
        return result;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeFormula(formula);
    }

    public List<LogicalCode> getLogicalNext() {
        return logicalNext;
    }

    @Override
    @Deprecated
    public void addNext(LogicalCode logicalCode) {}

    public void addLogicalNext(LogicalCode logicalCode) {
        this.logicalNext.add(logicalCode);
    }

    @Override
    public boolean removeNext(LogicalCode object) {
        logicalNext.remove(object);
        ranges.remove(object);
        return true;
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
        namespace.assign(this.varName, result);
        return result;
    }

    @Override
    public ChooseStructureCube copy(Namespace namespace) {
        return null;
    }

    public void setRange(LogicalCode code, Pair<Float, Float> range) {
        ranges.put(code, range);
    }

    public @Nullable Pair<Float, Float> getRange(LogicalCode code) {
        return ranges.getOrDefault(code, null);
    }

    public void setDefaultRange(Pair<Float, Float> defaultRange) {
        this.defaultRange = defaultRange;
    }

    public Pair<Float, Float> getDefaultRange() {
        return defaultRange;
    }

    public List<LogicalCode> getCorrectNext() {
        float data = getData();
        ArrayList<LogicalCode> result = new ArrayList<>();
        for (LogicalCode code : logicalNext) {
            Pair<Float, Float> r = ranges.get(code);
            if (r.getFirst() <= data && r.getSecond() > data) result.add(code);
        }
        return result;
    }
}
