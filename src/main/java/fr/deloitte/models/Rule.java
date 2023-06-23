package fr.deloitte.models;

import lombok.Data;

import java.util.List;

@Data
public class Rule {
    private List<String> inputs;
    private List<String> outputs;

    public Rule(List<String> inputs, List<String> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "inputs=" + inputs +
                ", outputs=" + outputs +
                '}';
    }



    @Override
    public boolean equals(Object obj) {
        Rule other = (Rule) obj;

        if(other.getInputs().size() != this.inputs.size() || other.getOutputs().size() != this.outputs.size())
            return false;

        for(int i = 0; i < inputs.size(); i++){
            if(inputs.get(i).equals(other.getInputs().get(i)))
                return false;
        }
        for(int i = 0; i < outputs.size(); i++){
            if(outputs.get(i).equals(other.getOutputs().get(i)))
                return false;
        }

        return true;
    }

}
