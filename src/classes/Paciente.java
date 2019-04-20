package classes;

import asclepius.DSC.DataSetComponent;
import asclepius.DSC.IDataSet;
import interfaces.IResponder;
import java.util.ArrayList;
import java.util.Random;

public class Paciente implements IResponder{   
    /*Esse ArrayList vai guardar os 7 sintomas (yes/no), os dados do Paciente*/
    private ArrayList<String> dataList = new ArrayList<>();
    
    public Paciente(){
    /*Quando um Paciente for instanciado, ele obrigatória e aleatoriamente recebe um conjunto de dados diretamente da base .CSV*/
        IDataSet DS = new DataSetComponent();
        DS.setDataSource("src\\data\\test-cases.csv");
        String attributes[] = DS.requestAttributes();
        String instances[][] = DS.requestInstances();
        int randomData = new Random().nextInt(instances.length);
        for(int x = 0; x < 7; x++){    
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
        
        if(counter < 7){ //Se o sintoma existe...
            if(dataList.get(counter).equalsIgnoreCase("t")){
                return "yes";
            }else if(dataList.get(counter).equalsIgnoreCase("f")){
                return "no";
            }
        }else if(counter == 7){ //Se o sintoma não existe...
            return "unknown";
        }
        
        return "Function ask() has crashed.";
    }

    @Override
    public boolean finalAnswer(String answer) {
        // TODO Auto-generated method stub
        return false;
    }

}
