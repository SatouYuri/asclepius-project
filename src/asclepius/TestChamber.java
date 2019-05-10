package asclepius;
 
import asclepius.TBC.ITree;
import asclepius.TBC.TreeBuilderComponent;
import classes.Doutor;
import classes.Paciente;
import interfaces.IResponder;

//Este arquivo é usado para testar os módulos em desenvolvimento.

public class TestChamber{
    public static void main(String[] args) {
        
        /*
        //Testando a árvore (TreeBuilderComponent)
        ITree T1 = new TreeBuilderComponent("src\\data\\test-cases-minus.csv");
        T1.TreePrint();
        */
        
        IResponder P1 = new Paciente();
        IResponder P2 = new Paciente();
        IResponder P3 = new Paciente();
        
        Doutor D1 = new Doutor(P1);
        D1.startInterview();
        
        
    }
}
