package asclepius;

//import asclepius.DSC.DataSetComponent;
//import asclepius.DSC.IDataSet;
import classes.Paciente;
import interfaces.IResponder;
        
public class Asclepius{
    public static void main(String[] args) {
        //System.out.println("THE WORLD! Pare o tempo agora!");
        
        /*Capturando informação do acervo de dados .CSV*/
        //IDataSet DS = new DataSetComponent();
        //DS.setDataSource("src\\data\\test-cases.csv");
        //String attributes[] = DS.requestAttributes();
        //String instances[][] = DS.requestInstances();
        
        /*Testando o método ask() da classe Paciente*/
        IResponder P1 = new Paciente();
        IResponder P2 = new Paciente();
        
        System.out.println(P1.ask("paralysis"));
        System.out.println(P2.ask("paralysis"));
        
        System.out.println(P1.ask("gordice"));
        System.out.println(P1.ask("dor_na_alma"));
    }
}
