package classes;

import asclepius.DSC.DataSetComponent;
import asclepius.DSC.IDataSet;
import interfaces.IEnquirer;
import interfaces.IResponder;
import interfaces.IResponderReceptacle;

public class Doutor implements IResponderReceptacle, IEnquirer{
    private IResponder iResponder;
    private String answer = "";
    private String diagnostico[];
    
    public Doutor(IResponder responder) {
    	this.connect(responder);
    }
    
    @Override
    public void connect(IResponder responder) {
        // TODO Auto-generated method stub
        iResponder = responder;
    }

    @Override  
    public void startInterview() {
        // TODO Auto-generated method stub
    	
    	//Verifica ser foi efetuada a conexão
        if(iResponder != null) {
        	IDataSet DS = new DataSetComponent();
            DS.setDataSource("src\\data\\test-cases.csv");
            String attributes[] = DS.requestAttributes();
            String instances[][] = DS.requestInstances();
            diagnostico = new String[attributes.length-1];
            int k, aux = 0;
            
            //Se sim, fazemos a entrevista
            for(int i = 0; i < attributes.length-1; i++) {
            	
            	//Fazendo as perguntas
            	System.out.print(attributes[i]+"? ");
            	System.out.println(iResponder.ask(attributes[i]));
            	
            	//Guardando as respostas para dar o diagnóstico
            	if(iResponder.ask(attributes[i]).equalsIgnoreCase(Paciente.nao)) {
            		diagnostico[i] = "f";
            	}else if(iResponder.ask(attributes[i]).equalsIgnoreCase(Paciente.sim)) {
            		diagnostico[i] = "t";
            	}
            }
            
            //Aqui eu comparo instancia por instancia para saber o diagnóstico
            //essa é a parte que precisa ser otmizada
            for(int j = 0; j < instances.length; j++) {
            	aux = 0;
            	for(k = 0; k < diagnostico.length; k++) {
            		if(diagnostico[k].equals(instances[j][k]))
                        aux++;
            	}
            	
            	if(aux == diagnostico.length){
            		//aqui precisa fazer com que não se repita o mesmo diagnóstico
            		//na tabela tem diagnósticos repetidos
            		//coloquei como string porque no "finalAnswer" o parâmetro é necessariamente uma String
            		//não sei como a gente pode implemenar o "finalAnswer" depois porque a gente vai precisar abrir a string 
                    answer += instances[j][k]+"\n";
                }
            }
            
            //Printando o diagnóstico
            System.out.println();
            System.out.println("Diagnóstico: ");
            System.out.println(answer);
            
            //Confirmando se o diagnóstico está correto 
            iResponder.finalAnswer(answer);
        }
    }

}
