package classes;

import interfaces.IEnquirer;
import interfaces.IResponder;
import interfaces.IResponderReceptacle;

public class Doutor implements IResponderReceptacle, IEnquirer{
	IResponder paciente;
	
	@Override  
	public void startInterview() {
		// TODO Auto-generated method stub
		//precisa do connect para inicar
		//no final chama o finalAnswer do paciente
	}

	@Override
	public void connect(IResponder responder) {
		// TODO Auto-generated method stub
		
	}

}
