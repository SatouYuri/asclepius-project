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
        curPacient = responder;
    }

    @Override  
    public void startInterview() {
    	//Verifica ser foi efetuada a conexão
        if(curPacient != null) {
            /*Construindo matriz e lista de dados...*/
            IDataSet DS = new DataSetComponent();
            DS.setDataSource("src\\data\\test-cases.csv");
            String attributes[] = DS.requestAttributes();
            String instances[][] = DS.requestInstances();
            ArrayList<String[]> insList = new ArrayList<>();
            
            for(int x = 0; x < instances.length; x++) {
            	insList.add(instances[x]);
            }
            
            /*Construindo um ArrayList de todas doenças no arquivo .CSV...*/
            ArrayList<String> diseases = new ArrayList<>();
            for(int d = 0; d < insList.size(); d++){
                if(diseases.contains(insList.get(d)[attributes.length - 1]) == false){
                    diseases.add(insList.get(d)[attributes.length - 1]);
                }
            }
            
            int diagCounter = 0;
            
            /*Executando a entrevista...*/
            for(int i = 0; i < attributes.length - 1; i++) {
            	
            	//Fazendo as perguntas
            	System.out.print(attributes[i]+"? ");
            	System.out.println(curPacient.ask(attributes[i]));
            	int rFlag = 0;
            	
                /*Filtrando insList...*/
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
                
                /*Checando se só há 1 diagnóstico possível*/
            	diagCounter = 0;
                /*for(int h = 0; h < insList.size(); h++){
                    if((h + 1) < insList.size()){
                        if(insList.get(h)[attributes.length - 1].equalsIgnoreCase(insList.get(h + 1)[attributes.length - 1])){
                            break;
                        }else{
                            diagCounter++;
                        }
                    }
                }*/
                
                /*ATENÇÃO: HÁ UM ERRO NO ALGORITMO DO DIAGCOUNTER; ARRUMAR DEPOIS.*/
                
                /*Se o size() da insList for 1 OU se só houver 1 diagnóstico possível...*/
            	if(insList.size() == 1 /*|| diagCounter == (insList.size() - 1)*/){
                    System.out.println("BROKEN!");
                    break;
                }
            }
            
            /*Printando o diagnóstico*/
            System.out.println();
            System.out.println("Diagnóstico: ");
            for(int a = 0; a < insList.size(); a++) {
                if(diseases.contains(insList.get(a)[attributes.length - 1])){
                    System.out.println(insList.get(a)[attributes.length - 1]);
                    diseases.remove(diseases.indexOf(insList.get(a)[attributes.length - 1]));
                }
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
