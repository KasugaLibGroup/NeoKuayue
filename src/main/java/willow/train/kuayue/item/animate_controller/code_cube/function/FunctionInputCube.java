package willow.train.kuayue.item.animate_controller.code_cube.function;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kasuga.lib.core.util.data_type.Pair;
import kasuga.lib.vendor_modules.interpreter.compute.data.Namespace;
import willow.train.kuayue.item.animate_controller.code_cube.Evalutaionable;
import willow.train.kuayue.item.animate_controller.code_cube.LogicalCode;
import willow.train.kuayue.item.animate_controller.code_cube.logic.EvaluationCube;

import java.util.*;

public class FunctionInputCube implements Evalutaionable {

    public final Namespace namespace;
    public final String funcName;
    private final HashMap<LogicalCode, String> varMapping;
    private final Set<LogicalCode> codes;
    private final List<LogicalCode> next;
    private final List<Pair<String, Float>> params;
    public FunctionInputCube(String funcName, Namespace parentNamespace, List<Pair<String, Float>> params) {
        this.namespace = new Namespace(parentNamespace);
        this.varMapping = Maps.newHashMap();
        this.funcName = funcName;
        this.codes = new HashSet<>();
        this.next = Lists.newArrayList();
        this.params = params;
    }

    public FunctionInputCube(String funcName, Namespace parentNamespace) {
        this.namespace = new Namespace(parentNamespace);
        this.varMapping = Maps.newHashMap();
        this.funcName = funcName;
        this.codes = new HashSet<>();
        this.next = Lists.newArrayList();
        this.params = Lists.newArrayList();
    }

    @Override
    public HashMap<LogicalCode, String> getVarMapping() {
        return varMapping;
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    public Set<LogicalCode> getCodes() {
        return codes;
    }

    public List<Pair<String, Float>> getParams() {
        return params;
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
        return namespace.getInstance("return_value").getValue("return_value");
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        FunctionInputCube neoInput = new FunctionInputCube(this.funcName, namespace, new ArrayList<>(params));
        HashMap<LogicalCode, List<LogicalCode>> copying = new HashMap<>();
        HashMap<LogicalCode, LogicalCode> map = new HashMap<>();
        map.put(this, neoInput);
        copying.put(neoInput, this.getNext());

        while (!copying.isEmpty()) {
            HashMap<LogicalCode, List<LogicalCode>> wouldBeCopy = new HashMap<>();
            for (Map.Entry<LogicalCode, List<LogicalCode>> entry : copying.entrySet()) {
                for (LogicalCode code : entry.getValue()) {
                    if (map.containsKey(code)) {
                        entry.getKey().addNext(map.get(code));
                        continue;
                    }
                    LogicalCode neoCode = code.copy(neoInput.namespace);
                    map.put(code, neoCode);
                    entry.getKey().addNext(neoCode);
                    wouldBeCopy.put(neoCode, code.getNext());
                }
            }
            copying.clear();
            copying.putAll(wouldBeCopy);
        }
        return neoInput;
    }

    public static void main(String[] args) {
        Namespace namespace1 = new Namespace();
        FunctionInputCube fic = new FunctionInputCube("main", namespace1, List.of());
        EvaluationCube e = new EvaluationCube("test", fic.getNamespace(), "1 + 1");
        EvaluationCube e2 = new EvaluationCube("test", fic.getNamespace(), "test + 1");
        fic.addNext(e);
        e.addNext(e2);
        e2.addNext(fic);
        Namespace namespace2 = new Namespace();
        FunctionInputCube neo = (FunctionInputCube) fic.copy(namespace2);
    }
}
