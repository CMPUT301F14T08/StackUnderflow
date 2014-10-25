/**
 * Answer class, extends Post class
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

public class Answer extends Post {
	
	private Question mParentQuestion;
	
	public Answer(String text, String signature) {
		this(text, signature, null);
	}
	
	public Answer(String text, String signature, String photo) {
		super(text, signature, photo);
		mParentQuestion = null;
	}
	
	public void setParentQuestion(Question parentQuestion){
		mParentQuestion = parentQuestion;
	}
	
	public Question getParentQuestion() {
		return mParentQuestion;
	}
	
}
