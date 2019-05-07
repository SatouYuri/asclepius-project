package asclepius;
 
import asclepius.TBC.ITree;
import asclepius.TBC.TreeBuilderComponent;

//Este arquivo é usado para testar os módulos em desenvolvimento.

public class TestChamber{
    public static void main(String[] args) {
        
        //Testando a árvore (TreeBuilderComponent)
        ITree T1 = new TreeBuilderComponent("src\\data\\test-cases-minus.csv");
        T1.TreePrint();
        
    }
}
