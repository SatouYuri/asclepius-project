package asclepius.TBC;

import asclepius.DSC.DataSetComponentPlus;
import asclepius.DSC.IDataSet;

public class TreeBuilderComponent implements ITree{
    private TreeNode insTree = null;
    private String[] attributes = null;
    private String[][] instances = null;
    
    public TreeBuilderComponent(String dataSource){ //"dataSource" é o caminho para o arquivo CSV que gerará a árvore.
        IDataSet DS = new DataSetComponentPlus();
        DS.setDataSource(dataSource);
        this.attributes = DS.requestAttributes();
        this.instances = DS.requestInstances();
        this.insTree = buildTree();
    }
    
    public TreeNode buildNode(int curAttIndex){ //Usado por buildTree() para construir um nó da árvore de busca. Comece executando com curAttIndex = 0.
        TreeNode newNode = new TreeNode(attributes[curAttIndex]);
        
        if(curAttIndex < (attributes.length - 2)){
            newNode.setL(buildNode(curAttIndex + 1));
            newNode.setR(buildNode(curAttIndex + 1));
        }
        
        return newNode;
    }
    
    public TreeNode buildTree(){ //Constrói a árvore e retorna o nó cabeça dela.
        TreeNode headNode = buildNode(0);
        
        for(int x = 0; x < instances.length; x++){
            TreeNode curNode = headNode;
            for(int y = 0; y < instances[x].length - 1; y++){
                if(y == ((instances[x].length - 1) - 1)){
                    if(instances[x][y].equalsIgnoreCase("f")){
                        curNode.addToListL(instances[x][instances[x].length - 1]);
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        curNode.addToListR(instances[x][instances[x].length - 1]);
                    }
                }else{
                    if(instances[x][y].equalsIgnoreCase("f")){
                        curNode = curNode.getL();
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        curNode = curNode.getR();
                    }else{
                        System.out.println("ERROR: the buildTree() method has crashed.");
                        break;
                    }   
                }
            }
        }
        return headNode;
    }
    
    public void TreePrint(TreeNode treeHead){
        if(treeHead != null){
            System.out.printf("%s ", treeHead.getStrValue());
            TreePrint(treeHead.getL());
            TreePrint(treeHead.getR());
        }else{
            System.out.printf(". "); //Árvore vazia ou fim da recursão.
        }
    }
    
    @Override
    public void TreePrint(){ //Imprime a árvore. Funcionalidade para testes.
        TreePrint(insTree);
    }
    
    @Override
    public TreeNode getTreeHead(){
        if(insTree != null){
            return insTree;
        }else{
            System.out.println("WARNING: empty/null tree.");
            return insTree;
        }
    }
}
