package fr.deloitte.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DmnTable {

    List<Rule> rules;

    public DmnTable(int inputNumber, int ouputNumber, List<String> inputs, List<String> outputs) {
        List<List<String>> inputRows = getSubString(inputNumber, inputs);
        List<List<String>> outputRows = getSubString(ouputNumber, outputs);
        rules = new ArrayList<>();
        for(int i= 0; i<inputRows.size(); i++){
            Rule rule = new Rule(inputRows.get(i), outputRows.get(i));
            rules.add(rule);
        }
    }

    private List<List<String>> getSubString(int size, List<String> mainList) {
        List<List<String>> subLists = new ArrayList<>();
        for(int i=0; i < (mainList.size()/size); i++){
            subLists.add(mainList.subList(i,i+size));
        }
        return subLists;
    }

    public void addRule(Rule rule){
        if(rules == null)
            rules = new ArrayList<Rule>();
        rules.add(rule);
    }

    @Override
    public String toString() {
        return "DmnTable{" +
                "rules=" + rules +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        DmnTable other = (DmnTable) obj;

        if(rules.size() != other.getRules().size())
            return false;

        for (int i=0; i<rules.size(); i++){
            if(!rules.get(i).equals(other.getRules().get(i)))
                return false;
        }
        return true;
    }
}
