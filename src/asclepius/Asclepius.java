package asclepius;

import asclepius.DSC.DataSetComponent;
import asclepius.DSC.IDataSet;
        
public class Asclepius{
    public static void main(String[] args) {
        System.out.println("THE WORLD! Pare o tempo agora!");
        
        IDataSet DS = new DataSetComponent();
        DS.setDataSource("src\\data\\test-cases.csv");
        String attributes[] = DS.requestAttributes();
        String instances[][] = DS.requestInstances();
        
    }
}
