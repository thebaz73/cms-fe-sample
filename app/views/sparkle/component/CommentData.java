package views.sparkle.component;

import play.data.validation.Constraints.*;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CommentData
 * Created by bazzoni on 03/06/2015.
 */
public class CommentData {
    public Date timestamp = new Date();
    public String email;
    public String title;
    public String content;

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "No email was given."));
        }

        if (title == null || title.length() == 0) {
            errors.add(new ValidationError("title", "No title was given."));
        }

        if (content == null || content.length() == 0) {
            errors.add(new ValidationError("content", "No content was given."));
        }


        if(errors.size() > 0)
            return errors;

        return null;
    }
}
