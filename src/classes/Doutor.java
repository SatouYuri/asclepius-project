package classes;

import java.util.ArrayList;

import asclepius.DSC.DataSetComponent;
import asclepius.DSC.IDataSet;
import interfaces.IEnquirer;
import interfaces.IResponder;
import interfaces.IResponderReceptacle;

public class Doutor implements IResponderReceptacle, IEnquirer{
    private IResponder curPacient;
    private String answer = "";
    
    public Doutor(IResponder responder) {
    	this.connect(responder);
    }
    
    @Override
    public void connect(IResponder responder) {
        // TODO Auto-generated method stub
        curPacient = responder;
    }

    @Override  
    public void startInterview() {
        // TODO Auto-generated method stub
    	
    	//Verifica ser foi efetuada a conexão
        if(curPacient != null) {
        	IDataSet DS = new DataSetComponent();
            DS.setDataSource("src\\data\\test-cases.csv");
            String attributes[] = DS.requestAttributes();
            String instances[][] = DS.requestInstances();
            ArrayList<String[]> insList = new ArrayList<>();
            
            for(int x = 0; x < instances.length; x++) {
            	insList.add(instances[x]);
            }            
            
            //Se sim, fazemos a entrevista
            for(int i = 0; i < attributes.length-1; i++) {
            	
            	//Fazendo as perguntas
            	System.out.print(attributes[i]+"? ");
            	System.out.println(curPacient.ask(attributes[i]));
            	int rFlag = 0;
            	
            	for(int j = 0; j < insList.size(); j++) {
            		if(rFlag == 1) {
            			j--;
            			rFlag = 0;
            		}
            		if(curPacient.ask(attributes[i]).equalsIgnoreCase(insList.get(j)[i]) == false) {
            			insList.remove(j);
            			rFlag = 1;
            		}
            	}
            	
            	if(insList.size()==1)
            		break;
            }
            System.out.println();
            System.out.println("Diagnóstico: ");
            for(int a = 0; a < insList.size(); a++) {
            	System.out.println(insList.get(a)[7]);
            }
            
            
            /*Printando o diagnóstico
            System.out.println();
            System.out.println("Diagnóstico: ");
            System.out.println(answer);
            
            //Confirmando se o diagnóstico está correto 
            curPacient.finalAnswer(answer);*/
        }
    }

}
