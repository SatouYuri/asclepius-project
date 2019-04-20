package classes;

import interfaces.IEnquirer;
import interfaces.IResponder;
import interfaces.IResponderReceptacle;

public class Doutor implements IResponderReceptacle, IEnquirer{
    IResponder iResponder;

    @Override  
    public void startInterview() {
        // TODO Auto-generated method stub
        //precisa do connect para inicar
        //no final chama o finalAnswer do paciente
        if(iResponder != null) {
                //c�digo da entrevista
        }
    }

    @Override
    public void connect(IResponder responder) {
        // TODO Auto-generated method stub
        iResponder = responder;
    }

}
