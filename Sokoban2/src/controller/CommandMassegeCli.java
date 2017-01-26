package controller;
import controller.server.SokobanClientHandler;
import view.iView;

public class CommandMassegeCli extends CommandA {

	private SokobanClientHandler theClientHandler;
	
	public CommandMassegeCli(iView view,SokobanClientHandler ClientHandler) {
		this.theClientHandler=ClientHandler;
	}
	
	@Override
	public void execute() {
		if (theClientHandler!=null)
			this.theClientHandler.insertToQueue(params);
	}

}
