package by.tolkun.barbershop.action;

import exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class Action {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String inputName) {
        name = inputName;
    }

    abstract public Action.Forward execute(final HttpServletRequest request,
                                        final HttpServletResponse response);

    public static class Forward {
        private String forward;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        public Forward(final String forward, final boolean redirect) {
            this.forward = forward;
            this.redirect = redirect;
        }

        public Forward(final String forward){
            this(forward,true);
        }

        public String getForward() {
            return forward;
        }

        public void setForward(final String forward) {
            this.forward = forward;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(final boolean redirect) {
            this.redirect = redirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
