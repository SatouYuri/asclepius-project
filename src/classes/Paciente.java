package classes;

import asclepius.DSC.DataSetComponent;
import asclepius.DSC.IDataSet;
import interfaces.IResponder;
import java.util.ArrayList;
import java.util.Random;

public class Paciente implements IResponder{   
    /*Esse ArrayList vai guardar os 7 sintomas (yes/no), os dados do Paciente*/
    private ArrayList<String> dataList = new ArrayList<>();
    public static final String sim = "yes";
    public static final String nao = "no";
    public static final String naoSei = "unkown";
    
    public Paciente(){
    /*Quando um Paciente for instanciado, ele obrigatória e aleatoriamente recebe um conjunto de dados diretamente da base .CSV*/
        IDataSet DS = new DataSetComponent();
        DS.setDataSource("src\\data\\test-cases.csv");
        String attributes[] = DS.requestAttributes();
        String instances[][] = DS.requestInstances();
        int randomData = new Random().nextInt(instances.length);
        //considerando a última coluna como "diagnostic" ou outro elemento q não deve ser considerado
        for(int x = 0; x < attributes.length - 1; x++){  
            dataList.add(instances[randomData][x]);
        }
        //TESTE OU DEBUGGING
        /*Habilite essa parte do código para fins de teste*/
        for(int x = 0; x < dataList.size(); x++){    
            System.out.printf("%s ", dataList.get(x));
        }
        System.out.println();
        //TESTE OU DEBUGGING
    }
    
    @Override 
    public String ask(String question) {
        /*Chamando um DS para adquirir o vetor de sintomas*/
        IDataSet DS = new DataSetComponent();
        DS.setDataSource("src\\data\\test-cases.csv");
        String attributes[] = DS.requestAttributes();
        
        /*Checando se o sintoma existe e gerando a posição counter do vetor que possui tal sintoma, se existit.*/
        int counter = 0;
        for(int x = 0; x < attributes.length - 1; x++){
            if(attributes[x].equalsIgnoreCase(question)){
                break;
            }
            counter++;
        }
        
        if(counter < attributes.length - 1){ //Se o sintoma existe...
            if(dataList.get(counter).equalsIgnoreCase("t")){
                return sim;
            }else if(dataList.get(counter).equalsIgnoreCase("f")){
                return nao;
            }
        }else if(counter == attributes.length - 1){ //Se o sintoma não existe...
            return naoSei;
        }
        
        return "Function ask() has crashed.";
    }

    @Override
    public boolean finalAnswer(String answer) {
        // TODO Auto-generated method stub
        return false;
    }

}
