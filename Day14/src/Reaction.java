public class Reaction {
    public Chemical[] input;
    public Chemical output;
//    public String[] input;
//    public int[] inputQuantities;
//    public String output;
//    public int outputQuantity;

//    public Reaction(String[] initialInput, int[] initialInputQuantities, String initialOutput, int initialOutputQuantity){
//        this.input = initialInput;
//        this.inputQuantities = initialInputQuantities;
//        this.output = initialOutput;
//        this.outputQuantity = initialOutputQuantity;
//    }

    public Reaction(Chemical[] initialInput, Chemical initialOutput){
        this.input = initialInput;
        this.output = initialOutput;
    }

    @Override
    public String toString() {
        String text = "";
        for(int i = 0; i < input.length; i++){
            text += input[i].quantity+" "+input[i].name;
            if(i != input.length-1){
                text += ", ";
            }else{
                text += " => ";
            }
        }
        text += output.quantity+" "+output.name;
        return text;
    }

}
