package classes;

import asclepius.TBC.DataTree;
import asclepius.TBC.ITree;
import interfaces.IEnquirer;
import interfaces.IResponder;
import interfaces.IResponderReceptacle;
import java.util.ArrayList;

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
        if(curPacient != null){
            //Se a conexão foi efetuada com sucesso...
            ITree ivTree = DataTree.generate("src\\data\\test-cases-minus.csv");
            ArrayList<ArrayList<String>> diag = ivTree.diagCheck();
            System.out.println(diag); //Remover depois
            
            int diagStartSize;
            
            for(int x = 0; x < ivTree.requestAttributes().length - 1; x++){
                //Cortando metade do array "diag" (seguindo um dos dois caminhos do nó da árvore)
                if(curPacient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase("t")){
                    diagStartSize = diag.size();
                    
                    for(int y = 0; y < diagStartSize/2; y++){
                        diag.remove(0);
                    }
                    
                }else if(curPacient.ask(ivTree.requestAttributes()[x]).equalsIgnoreCase("f")){
                    diagStartSize = diag.size();
                    
                    for(int y = 0; y < diagStartSize/2; y++){
                        diag.remove(diagStartSize/2);
                    }
                    
                }else{
                    System.out.println("WARNING: 'unknown' answer from curPacient.");
                    break;
                }
                
                //Chegagem de condição de parada antes da reiteração (perguntas vão parar aqui se uma das condições for cumprida)
                if(diag.size() == 1){
                    break;
                }
                
                System.out.println(diag);
            }
            
        }else{
            //Se a conexão não foi efeutada com sucesso...
            System.out.println("WARING: curPacient is null at 'startInterview()' doctor's call.");
        }
    }
}
