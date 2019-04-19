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
        for(int i = 0; i < instances.length; i++){
            for(int a = 0; a < (attributes.length) - 1; a++){
                System.out.print(instances[i][a] + ", ");
            }
            System.out.println(instances[i][(attributes.length) - 1]);
        }
    }
}
