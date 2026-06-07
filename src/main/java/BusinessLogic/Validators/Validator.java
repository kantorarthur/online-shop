

package BusinessLogic.Validators;

public interface Validator<T> {

    /**
     * This class it's used to validate the object of the class that we are sending as parameter.
     */
    public void validate(T t);
}
