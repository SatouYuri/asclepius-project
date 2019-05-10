package asclepius.TBC;

import java.util.ArrayList;

public interface ITreeDataRequest {
    public void TreePrint(); //Imprime a árvore. Funcionalidade para testes.
    public TreeNode getTreeHead(); //Retorna o nó cabeça da árvore.
    public ArrayList diagCheck(); //Retorna um array composto dos arrays de cada um dos nós do útimo andar da árvore (dinamicamente).
}
