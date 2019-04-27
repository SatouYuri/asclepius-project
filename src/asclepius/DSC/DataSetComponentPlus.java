package asclepius.DSC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSetComponentPlus implements IDataSet {
    private String dataSource = null;
    private String[] attributes = null;
    private String[][] instances = null;
    private TreeNode insTree = null;
    
    public DataSetComponentPlus(){
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
            insTree = null;
        }else{
            try {
                readDS();
                insTree = buildTree(); /*Constrói a árvore de sintomas e posiciona cada instância.*/
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataSetComponentPlus.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DataSetComponentPlus.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @Override
    public TreeNode requestInsTree(){
        return insTree;
    }

    private void readDS() throws FileNotFoundException, IOException{
        ArrayList<String[]> instArray = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(dataSource))) {
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
        }
    }
    
    private TreeNode buildNode(int curAttIndex){ //Comece executando com curAttIndex = 0
        TreeNode newNode = new TreeNode(attributes[curAttIndex]);
        
        if(curAttIndex < (attributes.length - 2)){
            newNode.setL(buildNode(curAttIndex + 1));
            newNode.setR(buildNode(curAttIndex + 1));
        }
        
        return newNode;
    }
    
    /*Uso para testes: esse método imprime a árvore. Chame-o de dentro de buildTree()*/
    public void TreePrint(TreeNode treeHead){
        if(treeHead != null){
            System.out.printf("%s ", treeHead.getStrValue());
            TreePrint(treeHead.getL());
            TreePrint(treeHead.getR());
        }else{
            System.out.printf(". "); //Árvore vazia ou fim da recursão.
        }
    }
    
    private TreeNode buildTree(){ /*Constrói a árvore e retorna o nó cabeça dela.*/
        TreeNode headNode = buildNode(0);
        
        for(int x = 0; x < instances.length; x++){
            TreeNode curNode = headNode;
            for(int y = 0; y < instances[x].length - 1; y++){
                if(y == ((instances[x].length - 1) - 1)){
                    if(instances[x][y].equalsIgnoreCase("f")){
                        /*EXCLUIR DEPOIS: curNode.diagListL.add(instances[x][instances[x].length - 1]);*/
                        curNode.addToListL(instances[x][instances[x].length - 1]);
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        /*EXCLUIR DEPOIS: curNode.diagListR.add(instances[x][instances[x].length - 1]);*/
                        curNode.addToListR(instances[x][instances[x].length - 1]);
                    }
                }else{
                    if(instances[x][y].equalsIgnoreCase("f")){
                        curNode = curNode.getL();
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        curNode = curNode.getR();
                    }else{
                        System.out.println("WARNING: the buildTree() has crashed.");
                        break;
                    }   
                }
            }
        }
        TreePrint(headNode);
        return headNode;
    }
}
    
    