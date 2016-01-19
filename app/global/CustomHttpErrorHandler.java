package global;

import play.Logger;
import play.http.HttpErrorHandler;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

public class CustomHttpErrorHandler implements HttpErrorHandler {

	@Override
	public Promise<Result> onClientError(RequestHeader request, int statusCode, String message) {
		Logger.error("Client error");
		if (statusCode == play.mvc.Http.Status.BAD_REQUEST) {
			return Promise.pure(Controller.internalServerError("Unexpected exception client error."));
		}
		return null;
	}

	@Override
	public Promise<Result> onServerError(RequestHeader request, Throwable exception) {
		Logger.error("Server error", exception);
		return Promise.pure(Controller.internalServerError("Unexpected exception server error."));
	}
}