package client.login;

import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import client.base.Controller;
import client.base.IAction;
import client.misc.IMessageView;
import client.presenter.IPresenter;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	private IPresenter presenter;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView, IPresenter presenter) {
		
		super(view);
		
		this.presenter = presenter;
		this.messageView = messageView;
	}
	
	public LoginController(ILoginView view, IMessageView messageView) {
		
		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// TODO: log in user
		//boolean validLogin;
		String password=this.getLoginView().getLoginPassword();
		String username=this.getLoginView().getLoginUsername();

		LoginUserResponse response=this.presenter.login(username, password);
		if(response.isSuccessful()) {
			//successful authentication
			getLoginView().closeModal();
			loginAction.execute();
		}else {
			//invalid credentials
			messageView.setMessage(response.getMessage());
			messageView.showModal();
		}
	}

	@Override
	public void register() {
		
		// TODO: register new user (which, if successful, also logs them in)
		String username=this.getLoginView().getRegisterUsername();
		String password1=this.getLoginView().getRegisterPassword();
		String password2=this.getLoginView().getRegisterPasswordRepeat();
		
		String usernameError = "The username must be between three and seven characters: letters, digits, _ , and -";
		String passwordError = "The password must be five or more characters: letters, digits, _ , and -";
		String validCharsRegex = "[0-9]*[A-z]*[_]*[-]*";
		
		if(username.length()<3||username.length()>7){
			//username isn't valid
			messageView.setMessage(usernameError);
			messageView.showModal();
		}  
		else if(!username.matches(validCharsRegex)) {
			messageView.setMessage(usernameError);
			messageView.showModal();
		}  
		else if (!password1.equals(password2)) {
			//passwords don't match
			messageView.setMessage("Passwords must match.");
			messageView.showModal();
		}  
		else if(!password1.matches(validCharsRegex)) {
			messageView.setMessage(passwordError);
			messageView.showModal();
		}
		else if(password1.length() < 5){
			//passwords length has to be greater than 4
			messageView.setMessage(passwordError);
			messageView.showModal();
		}
		else {
			RegisterUserResponse response=this.presenter.register(username, password1);
			if(response.isSuccessful()) {
				// If register succeeded
				getLoginView().closeModal();
				loginAction.execute();
			} else {
				//registration failed
				messageView.setMessage(response.getMessage());
				messageView.showModal();
			}
		}	
	}

}

