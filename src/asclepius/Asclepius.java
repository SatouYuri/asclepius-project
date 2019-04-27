package asclepius;

import classes.Doutor;
import asclepius.DSC.DataSetComponentPlus;
import asclepius.DSC.IDataSet;
import asclepius.DSC.TreeNode;
import classes.Paciente;
import interfaces.IEnquirer;
import interfaces.IResponder;
        
public class Asclepius{
    public static void main(String[] args) {
        //System.out.println("THE WORLD! Pare o tempo agora!");
        
        /*Capturando informação do acervo de dados .CSV*/
        IDataSet DS = new DataSetComponentPlus();
        DS.setDataSource("src\\data\\test-cases-minus.csv");
        String attributes[] = DS.requestAttributes();
        String instances[][] = DS.requestInstances();
        TreeNode insTree = DS.requestInsTree(); //Teste de print incluído.
        
        /*Testando o método ask() da classe Paciente*/
        /*IResponder P1 = new Paciente();
        IResponder P2 = new Paciente();
        
        System.out.println(P1.ask("paralysis"));
        System.out.println(P2.ask("paralysis"));
        System.out.println(P1.ask(attributes[3]));
        System.out.println();
        
        System.out.println(P1.ask("gordice"));
        System.out.println(P1.ask("dor_na_alma"));
        System.out.println();
        
        IEnquirer doc = new Doutor(P1);
        doc.startInterview();*/
        
    }
}
