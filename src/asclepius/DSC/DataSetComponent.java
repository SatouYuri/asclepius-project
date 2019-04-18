package asclepius.DSC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSetComponent implements IDataSet {
    private String dataSource = null;
    private String[] attributes = null;
    private String[][] instances = null;
  
    public DataSetComponent(){
    }

    @Override
    public String getDataSource(){
        return dataSource;
    }

    @Override
    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
        if (dataSource == null) {
            attributes = null;
            instances = null;
        }else{
            try {
                readDS();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataSetComponent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DataSetComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
    @Override
    public String[] requestAttributes(){
        return attributes;
    }
  
    @Override
    public String[][] requestInstances(){
        return instances;
    }

    private void readDS() throws FileNotFoundException, IOException{
        ArrayList<String[]> instArray = new ArrayList<>();
        BufferedReader file = new BufferedReader(new FileReader(dataSource));
        String line = file.readLine();
        
        if(line != null){
            attributes = line.split(",");
            line = file.readLine();
            while(line != null){
                String[] instLine = line.split(",");
                instArray.add(instLine);
                line = file.readLine();
            }
            instances = instArray.toArray(new String[0][]);
        }
        
        file.close();
    }
}