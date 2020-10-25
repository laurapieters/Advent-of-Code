import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14Exercise {

    public static void main(String []args) throws FileNotFoundException {
        File file = new File("inputDay14.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Reaction> reactions = new ArrayList<>();
        while(sc.hasNext()){
            String line = sc.nextLine();
            // reading input
            String input = line.split("=>")[0];
            String[] inputSplit = input.split(",");
            String[] inputName = new String[inputSplit.length];
            int[] inputQuantities = new int[inputSplit.length];
            for(int i = 0; i < inputSplit.length; i++){
                if(i==0){
                    inputQuantities[i] = Integer.parseInt(inputSplit[i].split(" ")[0]);
                    inputName[i] = inputSplit[i].split(" ")[1];
                }else{
                    inputQuantities[i] = Integer.parseInt(inputSplit[i].split(" ")[1]);
                    inputName[i] = inputSplit[i].split(" ")[2];
                }
            }

            // reading output
            String output = line.split("=>")[1];
            int outputQuantity = Integer.parseInt(output.split(" ")[1]);
            String outputName = output.split(" ")[2];

            // add reaction
            Chemical[] inputChemicals = new Chemical[inputName.length];
            for(int i = 0; i < inputName.length; i++){
                Chemical inputChem = new Chemical(inputName[i], inputQuantities[i]);
                inputChemicals[i] = inputChem;
            }
            Chemical outputChemical = new Chemical(outputName, outputQuantity);
            Reaction r = new Reaction(inputChemicals,outputChemical);
            reactions.add(r);
        }

        ArrayList<Chemical> requirements = new ArrayList<>();
        Chemical requirement = new Chemical("FUEL", 1);
        requirements.add(requirement);
        boolean reactionComplete = false;
        while(reactionComplete == false){
            for(int i = 0; i < requirements.size(); i++){
                // add new requirements
                Chemical[] foundRequirements = findInputs(requirements.get(i), reactions);
                for(int j = 0; j < foundRequirements.length; j++){
                    requirements.add(foundRequirements[j]);
                }
                // remove old requirement
                requirements.remove(requirements.get(i));
            }
        }

    }

    public static Chemical[] findInputs(Chemical specificOutput, ArrayList<Reaction> reactions){
        int size = 0;
        for(int i = 0; i < reactions.size(); i++){
            if((specificOutput.name).equals(reactions.get(i).output.name)){
                size = reactions.get(i).input.length;
            }
        }
        Chemical[] foundInputs = new Chemical[size];
        for(int i = 0; i < reactions.size(); i++){
            if((specificOutput.name).equals(reactions.get(i).output.name)){
                foundInputs = reactions.get(i).input;
            }
        }
        return foundInputs;
    }

}
