package classes;

import asclepius.TBC.ITree;
import asclepius.TBC.TreeBuilderComponent;
import asclepius.TBC.TreeNode;
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
            ITree DataTree = new TreeBuilderComponent("src\\data\\test-cases-minus.csv");
            ArrayList<ArrayList<String>> diag = DataTree.diagCheck();
            
        }
    }
}
