package aufgabe3;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

public class LabelTask extends Task<Boolean>{

	private final Label label;
	private final String styleValue;
	private final Polygon zug;
	private final Boolean visible;
	
	public LabelTask(Label label, String styleValue, Polygon zug, Boolean visible){
		this.label = label;
		this.styleValue = styleValue;
		this.zug = zug;
		this.visible = visible;
	}
	
	@Override
	protected Boolean call() throws Exception {
		
		updateLabel();
		return true;
	}

	private void updateLabel(){
		Platform.runLater(()->{
			label.setStyle(this.styleValue);
			zug.setVisible(this.visible);
		;});
	}
}
